package de.jottyfan.camporganizer.book;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.myfaces.config.impl.digester.elements.FacesConfig;
import org.apache.myfaces.context.FacesContextFactoryImpl;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.SalesGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class BookController extends Controller {

	private final static Logger LOGGER = LogManager.getLogger(BookController.class);

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{BookBean}")
	private BookBean bean;

	private List<CampBean> camps;

	public String toBook() {
		try {
			camps = new CampGateway(facesContext).getAllCamps(true);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "not yet implemented", "Die Online-Anmeldung ist noch nicht fertig entwickelt. Bitte geben Sie den Inhalt der angegegebenen Felder vollständig bei einer Anmeldung per E-Mail an onkel-werner-freizeit@web.de oder per Post an unten stehende Adresse an."));
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading camps", e.getMessage()));
		}
		return "/pages/book.jsf";
	}

	public String toBook(Integer campPk) {
		bean = new BookBean();
		bean.setFkCamp(campPk);
		return toBook();
	}

	public String doBook() {
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "not yet implemented",
				"Die Anmeldung wurde noch nicht entwickelt."));
		LOGGER.info("someone wanted to book a camp");
		// TODO: create book table and add data to it
		return toBook();
	}

	public List<CampBean> getCamps() {
		return camps;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public BookBean getBean() {
		return bean;
	}

	public void setBean(BookBean bean) {
		this.bean = bean;
	}
}
