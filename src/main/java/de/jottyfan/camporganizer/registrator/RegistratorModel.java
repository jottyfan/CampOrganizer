package de.jottyfan.camporganizer.registrator;

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
	 * set accept flat to true
	 * 
	 * @param pk
	 */
	public void acceptRegistration(FacesContext facesContext, Integer pk) {
		try {
			new RegistratorGateway(facesContext).acceptUser(pk);
		}catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on accepting user", e.getMessage()));
		}
	}

	public List<RegistratorBean> getList() {
		return list;
	}
}
