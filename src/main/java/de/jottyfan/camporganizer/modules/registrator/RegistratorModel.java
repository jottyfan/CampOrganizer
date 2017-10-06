package de.jottyfan.camporganizer.modules.registrator;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.RegistratorGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class RegistratorModel {

	private List<RegistratorBean> list;
	private RegistratorBean bean;

	/**
	 * prepare everything for the page registrator
	 */
	public boolean loadRegistratorPageContent(FacesContext facesContext) {
		try {
			list = new RegistratorGateway(facesContext).loadUsers();
			return true;
		} catch (DataAccessException e) {
			list = new ArrayList<>();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading users", e.getMessage()));
			return false;
		}
	}

	/**
	 * accept user
	 * 
	 * @param facesContext
	 * @param pk
	 */
	public void acceptRegistration(FacesContext facesContext, RegistratorBean bean) {
		try {
			new RegistratorGateway(facesContext).acceptUser(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on accepting user", e.getMessage()));
		}
	}

	/**
	 * reject user 
	 * 
	 * @param facesContext
	 * @param bean2
	 */
	public void rejectRegistration(FacesContext facesContext, RegistratorBean bean) {
		try {
			new RegistratorGateway(facesContext).rejectUser(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on rejecting user", e.getMessage()));
		}
	}

	/**
	 * delete registration from database
	 * 
	 * @param facesContext
	 * @param pk
	 */
	public void deleteRegistration(FacesContext facesContext, Integer pk) {
		try {
			new RegistratorGateway(facesContext).deleteUser(pk);
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on deleting user", e.getMessage()));
		}
	}

	/**
	 * prepare edit page of current entry referenced by bean
	 * 
	 * @param bean
	 *          to be edited
	 */
	public void prepareEdit(RegistratorBean bean) {
		this.bean = bean;
	}

	/**
	 * update current bean in database
	 * 
	 * @param facesContext
	 *          to be used
	 */
	public void update(FacesContext facesContext) {
		try {
			Integer affectedRows = new RegistratorGateway(facesContext).update(bean);
			if (affectedRows != 1) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "mysterious behavior", "Es wurde eine seltsame Anzahl an Datensätzen geändert: " + affectedRows + " statt üblicherweise 1 Eintrag"));				
			}
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on updating registration", e.getMessage()));
		}
	}

	public List<RegistratorBean> getList() {
		return list;
	}

	public RegistratorBean getBean() {
		return bean;
	}

	public void setBean(RegistratorBean bean) {
		this.bean = bean;
	}
}
