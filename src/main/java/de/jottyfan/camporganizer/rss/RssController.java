package de.jottyfan.camporganizer.rss;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import de.jottyfan.camporganizer.db.RssGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class RssController {
	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{param.recipientCode}")
	private String recipientCode;

	public String toRss() {
		List<RssBean> beans = new ArrayList<>();
		if (recipientCode != null) {
			try {
				beans = new RssGateway(facesContext).getRss(recipientCode);
			} catch (DataAccessException e) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on reading rss from db", e.getMessage()));
			}
		} else {
			RssBean bean = new RssBean();
			bean.setPubdate(new Date());
			bean.setMessage("Dieser Feed ist nicht mehr aktuell. Bitte gib einen recipientCode an.");
			beans.add(bean);
		}
		SyndFeed feed = new RssModel().getRss(beans);
		ExternalContext ec = facesContext.getExternalContext();
		ec.responseReset();
		ec.setResponseCharacterEncoding("UTF-8");
		ec.setResponseContentType("application/rss+xml");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"onkelwernerfreizeiten.de.xml\"");
		Writer writer;
		try {
			writer = ec.getResponseOutputWriter();
			SyndFeedOutput output = new SyndFeedOutput();
			output.output(feed, writer);
			facesContext.responseComplete();
		} catch (IOException | FeedException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "writing rss failed", e.getMessage()));
		}
		return "";
	}

	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
}
