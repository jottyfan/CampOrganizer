package de.jottyfan.camporganizer.modules.book;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.CampBean;
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
public class BookModel {

	private final static Logger LOGGER = LogManager.getLogger(BookModel.class);

	@ManagedProperty(value = "#{personBean}")
	private PersonBean bean;

	/**
	 * get all camps; as there is no toLogin method, all camps are read from db just here
	 * 
	 * @return list of found camp beans
	 */
	public List<CampBean> getCamps(FacesContext facesContext) {
		bean.setCamps(new CampGateway(facesContext).getAllCampsFromView(true));
		// do not catch DataAccessException, as there is no
		// facesMessage rendered here (jsf lifecycle is too late)
		return bean.getCamps();
	}

	public PersonBean getBean() {
		return bean;
	}

	public void setBean(PersonBean bean) {
		this.bean = bean;
	}

	public void toBook(FacesContext facesContext) {
		try {
			bean.setCamps(new CampGateway(facesContext).getAllCampsFromView(true));
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading camps", e.getMessage()));
			LOGGER.error("BookModel.toBook: ", e);
		}
	}

	public void doBook(FacesContext facesContext, ProfileBean profileBean) {
		try {
			new BookGateway(facesContext).doBooking(profileBean, bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on booking", e.getMessage()));
		}
	}
}
