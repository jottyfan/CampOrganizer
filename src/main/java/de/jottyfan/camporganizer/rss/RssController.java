package de.jottyfan.camporganizer.rss;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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

	public String toRss() {
		List<RssBean> beans = new ArrayList<>();
		try {
			beans = new RssGateway(facesContext).getRss();
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on reading rss from db", e.getMessage()));
		}
		SyndFeed feed = new RssModel().getRss(beans);
		String rssContent = "TODO";
		ExternalContext ec = facesContext.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("xml");
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

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
}
