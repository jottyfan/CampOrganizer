package de.jottyfan.camporganizer.profile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.ProfileGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class ProfileController {

	@ManagedProperty(value = "#{profileBean}")
	private ProfileBean bean;

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	public String doLogin() {
		try {
			new ProfileGateway(facesContext).getProfile(bean);
			facesContext.addMessage("login",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "result", bean.getFullname()));

		} catch (DataAccessException e) {
			facesContext.addMessage("login failed",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "wrong login", e.getMessage()));
		}
		return "/pages/login.jsf";
	}

	public ProfileBean getBean() {
		return bean;
	}

	public void setBean(ProfileBean bean) {
		this.bean = bean;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
}
