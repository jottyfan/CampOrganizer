package de.jottyfan.camporganizer.profile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author henkej
 *
 */
@ManagedBean
@RequestScoped
public class ProfileController {
	private ProfileBean bean;

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	public String doLogin() {
		facesContext.addMessage("password check not yet implemented",
				new FacesMessage(FacesMessage.SEVERITY_WARN, "not yet implemented", "login is not yet ready"));
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
