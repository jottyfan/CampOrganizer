package de.jottyfan.camporganizer.modules.admin;

import java.io.IOException;
import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.DocumentGateway;
import de.jottyfan.camporganizer.db.LocationGateway;
import de.jottyfan.camporganizer.db.ProfileGateway;
import de.jottyfan.camporganizer.db.RssGateway;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.modules.campadmin.DocumentBean;
import de.jottyfan.camporganizer.modules.campadmin.LocationBean;
import de.jottyfan.camporganizer.profile.ProfileBean;
import de.jottyfan.camporganizer.rss.RssBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class AdminController extends Controller {

	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

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

			if (model.getRss() == null) {
				model.setRss(new RssBean(null));
			}
			model.setRssList(new RssGateway(facesContext).getAllRss());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.toAdministrate: ", e);
		}
		return "/pages/administrate.jsf";
	}

	public String doResetUserPassword(ProfileBean bean) {
		model.setActiveIndex(1);
		try {
			String newPassword = new ProfileGateway(facesContext).resetPassword(bean);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "password reset",
					"Das neue Passwort von " + bean.getFullname() + " lautet: " + newPassword));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doResetUserPassword: ", e);
		}
		return toAdministrate();
	}

	public String doDeleteUser(ProfileBean bean) {
		model.setActiveIndex(1);
		try {
			new ProfileGateway(facesContext).deleteProfile(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteUser: ", e);
		}
		return toAdministrate();
	}

	public String doDelete(ProfileRoleBean bean) {
		model.setActiveIndex(0);
		try {
			new ProfileGateway(facesContext).deleteProfileRole(bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDelete: ", e);
		}
		return toAdministrate();
	}

	public String doInsert() {
		model.setActiveIndex(0);
		try {
			new ProfileGateway(facesContext).insertProfileRole(model.getProfileRole());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doInsert: ", e);
		}
		return toAdministrate();
	}

	public String toEditRss(Integer pk) {
		model.setActiveIndex(5);
		for (RssBean bean : model.getRssList()) {
			if (bean.getPk().equals(pk)) {
				model.setRss(bean);
				model.setActiveIndexRss(1); // element tab
			}
		}
		return toAdministrate();
	}

	public String doDeleteRss() {
		model.setActiveIndex(5);
		try {
			new RssGateway(facesContext).deleteRss(model.getRss());
			model.setActiveIndexRss(0);
			model.setRss(new RssBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteRss: ", e);
		}
		return toAdministrate();
	}

	public String doUpdateRss() {
		model.setActiveIndex(5);
		try {
			if (model.getRss().getPk() == null) {
				throw new DataAccessException("Anlegen von RSS-Feeds wird mit diesem Formular nicht unterstützt.");
			}
			new RssGateway(facesContext).update(model.getRss());
			model.setActiveIndexRss(0);
			model.setRss(new RssBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doUpsertRss: ", e);
		}
		return toAdministrate();
	}

	public String doResetRss() {
		model.setActiveIndex(5);
		model.setActiveIndexRss(1);
		model.setRss(new RssBean(null));
		return toAdministrate();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void setModel(AdminModel model) {
		this.model = model;
	}
}
