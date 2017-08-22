package de.jottyfan.camporganizer.admin;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.ProfileGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class AdminController extends Controller {

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{adminModel}")
	private AdminModel model;

	public String toAdministrate() {
		model.setProfileRole(new ProfileRoleBean());
		try {
			ProfileGateway gw = new ProfileGateway(facesContext);
			model.setProfileRoles(gw.getAllProfileRoles());
			model.setRoles(gw.getAllRoles());
			model.setUsers(gw.getAllUsers());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
		}
		return "/pages/administrate.jsf";
	}

	public String doDelete(ProfileRoleBean bean) {
		try {
			new ProfileGateway(facesContext).deleteProfileRole(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
		}
		return toAdministrate();
	}

	public String doInsert() {
		try {
			new ProfileGateway(facesContext).insertProfileRole(model.getProfileRole());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
		}
		return toAdministrate();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void setModel(AdminModel model) {
		this.model = model;
	}
}
