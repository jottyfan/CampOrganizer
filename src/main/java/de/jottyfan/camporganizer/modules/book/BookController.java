package de.jottyfan.camporganizer.modules.book;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.BookGateway;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.ProfileGateway;
import de.jottyfan.camporganizer.profile.ProfileBean;

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

	@ManagedProperty(value = "#{personBean}")
	private PersonBean bean;

	@ManagedProperty(value = "#{profileBean}")
	private ProfileBean profileBean;
	
	@ManagedProperty(value = "#{bookModel}")
	private BookModel model;

	public String toBook() {
		model.toBook(facesContext);
		return "/pages/book.jsf";
	}

	public String toBook(Integer campPk) {
		bean.setFkCamp(campPk);
		return toBook();
	}

	public String doBook() {
		model.doBook(facesContext, profileBean);
		if (profileBean.getIsEmpty()) {
			profileBean.setForename(null);
			profileBean.setSurname(null);
			profileBean.setUsername(null);
		}
		return toBook();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public PersonBean getBean() {
		return bean;
	}

	public void setBean(PersonBean bean) {
		this.bean = bean;
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}

	public void setModel(BookModel model) {
		this.model = model;
	}
}
