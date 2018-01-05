package de.jottyfan.camporganizer.ical;

import java.io.IOException;
import java.io.Writer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

import biweekly.Biweekly;
import biweekly.ICalendar;
import de.jottyfan.camporganizer.db.IcalGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class IcalController {
	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	public String toIcal() {
		ICalendar ical = null;
		try {
			ical = new IcalGateway(facesContext).getIcal();
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on reading ical from db", e.getMessage()));
		}
		ExternalContext ec = facesContext.getExternalContext();
		ec.responseReset();
		ec.setResponseCharacterEncoding("UTF-8");
		ec.setResponseContentType("text/calendar");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"onkelwernerfreizeiten.de.ical\"");
		Writer writer;
		try {
			writer = ec.getResponseOutputWriter();
			writer.write(Biweekly.write(ical).go());
			writer.flush();
			facesContext.responseComplete();
		} catch (IOException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "writing ical failed", e.getMessage()));
		}
		return "";
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
}
