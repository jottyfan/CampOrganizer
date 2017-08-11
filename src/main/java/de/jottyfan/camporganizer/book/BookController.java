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

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.SalesGateway;
import de.jottyfan.camporganizer.register.CampBean;

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
		} catch (DataAccessException e) {
			facesContext.addMessage("failure",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading camps", e.getMessage()));
		}
		return "/pages/book.jsf";
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
