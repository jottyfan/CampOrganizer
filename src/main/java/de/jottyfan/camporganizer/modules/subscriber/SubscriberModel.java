package de.jottyfan.camporganizer.modules.subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.BookGateway;
import de.jottyfan.camporganizer.db.DocumentGateway;
import de.jottyfan.camporganizer.db.PersondocumentGateway;
import de.jottyfan.camporganizer.db.SubscriberGateway;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.modules.admin.DocumentBean;
import de.jottyfan.camporganizer.modules.book.PersonBean;
import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class SubscriberModel {
	private final static Logger LOGGER = LogManager.getLogger(SubscriberModel.class);

	private List<SubscriberBean> camps;
	private PersonBean bean;
	private PersondocumentBean persondocument;

	public List<SubscriberBean> getCamps() {
		return camps;
	}

	public void setCamps(List<SubscriberBean> camps) {
		this.camps = camps;
	}

	public void doDelete(FacesContext facesContext, ProfileBean profileBean, SubscriberBean bean) {
		try {
			new SubscriberGateway(facesContext).delete(profileBean, bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.doDelete", e);
		}
	}

	public void load(FacesContext facesContext, Integer pk) {
		try {
			setCamps(new SubscriberGateway(facesContext).getCamps(pk));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.load", e);
		}
	}

	public PersonBean getBean() {
		return bean;
	}

	/**
	 * load person from db
	 * 
	 * @param facesContext
	 *          current faces context
	 * @param pk
	 *          primary key of person to be loaded
	 */
	public void loadPerson(FacesContext facesContext, Integer pk) {
		try {
			bean = new BookGateway(facesContext).getPerson(pk);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.loadPerson", e);
		}
	}

	/**
	 * update person in db
	 * 
	 * @param facesContext
	 *          current faces context
	 */
	public void doUpdate(FacesContext facesContext) {
		try {
			new BookGateway(facesContext).update(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.doUpdate", e);
		}
	}

	/**
	 * delete document uploaded by user from database
	 * 
	 * @param facesContext
	 * @param bean
	 */
	public void doDeleteUserDoc(FacesContext facesContext, PersondocumentBean bean) {
		try {
			new PersondocumentGateway(facesContext).deletePersondocument(bean);
			StringBuilder buf = new StringBuilder("Das Dokument ");
			buf.append(bean.getName());
			buf.append(" wurde aus der Datenbank gelöscht.");
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "document removed", buf.toString()));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.doDeleteUserDoc", e);
		}
	}

	/**
	 * add document uploaded by user to database
	 * 
	 * @param facesContext
	 * @param bean
	 */
	public void doAddUserDoc(FacesContext facesContext, SubscriberBean bean) {
		persondocument.setFkPerson(bean.getPkPerson());
		try {
			new PersondocumentGateway(facesContext).addPersondocument(persondocument);
			StringBuilder buf = new StringBuilder("Das Dokument ");
			buf.append(persondocument.getName());
			buf.append(" wurde in die Datenbank gelegt.");
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "document added", buf.toString()));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.doAddUserDoc", e);
		}
	}

	public List<EnumFiletype> getEnumlistFiletype() {
		List<EnumFiletype> list = new ArrayList<>();
		for (EnumFiletype e : EnumFiletype.values()) {
			list.add(e);
		}
		return list;
	}

	public PersondocumentBean getPersondocument() {
		return persondocument;
	}

	public void setPersondocument(PersondocumentBean persondocument) {
		this.persondocument = persondocument;
	}
}
