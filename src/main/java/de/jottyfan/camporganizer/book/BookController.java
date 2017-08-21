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
import de.jottyfan.camporganizer.db.BookGateway;
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

	@ManagedProperty(value = "#{bookBean}")
	private BookBean bean;

	public String toBook() {
		try {
			bean = bean == null ? new BookBean() : bean;
			bean.setCamps(new CampGateway(facesContext).getAllCamps(true));
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
		try {
			new BookGateway(facesContext).insert(bean);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "booking finished",
					"Die Anmeldung wurde übernommen. Sobald sie von uns bestätigt wurde, ist Dein Platz auf der Freizeit gesichert."));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on booking", e.getMessage()));
		}
		return toBook();
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
