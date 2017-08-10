package de.jottyfan.camporganizer.profile;

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
public class ProfileController extends Controller {

	@ManagedProperty(value = "#{profileBean}")
	private ProfileBean bean;

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	public String doLogin() {
		try {
			new ProfileGateway(facesContext).getProfile(bean);
			facesContext.addMessage("login",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "welcome back, ", bean.getFullname()));
			return toProfile();
		} catch (DataAccessException e) {
			facesContext.addMessage("login failed",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "wrong login", e.getMessage()));
			bean.setForename(null);
			bean.setSurname(null);
			return toLogin();
		}
	}

	public String doLogout() {
		facesContext.getExternalContext().getSessionMap().clear();
		return toLogin();
	}

	public String doRegister() {
		try {
			new ProfileGateway(facesContext).register(bean);
			return toProfile();
		} catch (DataAccessException e) {
			facesContext.addMessage("registering failed",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "error on registering", e.getMessage()));
			bean.setForename(null);
			bean.setSurname(null);
			return toRegister();
		}
	}

	public String doRemoveLogin() {
		try {
			new ProfileGateway(facesContext).removeLogin(bean);
			return doLogout();
		} catch (DataAccessException e) {
			facesContext.addMessage("remove login failed",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "error on removing", e.getMessage()));
			return "";
		}
	}

	public String doChangePassword() {
		if (bean.getPassword().equals(bean.getPasswordAgain())) {
			bean.setPasswordButKeepEncrypted(null);
			bean.setPasswordAgain(null);
			try {
				new ProfileGateway(facesContext).changePasswords(bean);
				facesContext.addMessage("erfolgreich",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "passwords changed", "Das Passwort wurde aktualisiert."));
				return toProfile();
			} catch (DataAccessException e) {
				facesContext.addMessage("failure",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
				return toProfile();
			}
		} else {
			facesContext.addMessage("passwords not equal", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"passwords do not match", "Die eingegebenen Passwörter sind nicht identisch."));
			bean.setPassword(null);
			bean.setPasswordAgain(null);
			return toProfile();
		}
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
