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
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.profile.ProfileBean;

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

			model.setEnumlistDoctype(Arrays.asList(EnumDocument.values()));
			model.setEnumlistFiletype(Arrays.asList(EnumFiletype.values()));
			if (model.getDocument() == null) {
				model.setDocument(new DocumentBean(null));
			}
			model.setDocuments(gw.getAllDocuments());

			if (model.getLocation() == null) {
				model.setLocation(new LocationBean(null));
			}
			model.setLocations(gw.getAllLocations());
			
			if (model.getCamp() == null) {
				model.setCamp(new CampBean());
			}
			model.setCamps(new CampGateway(facesContext).getAllCampsFromTable());
			model.setLocationNameToCamp();
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
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "password reset", "Das neue Passwort von " + bean.getFullname() + " lautet: " + newPassword));
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

	public String toEditDocument(Integer pk) {
		model.setActiveIndex(4);
		for (DocumentBean bean : model.getDocuments()) {
			if (bean.getPk().equals(pk)) {
				model.setDocument(bean);
				model.setActiveIndexDocument(1); // element tab
			}
		}
		return toAdministrate();
	}

	public String toEditLocation(Integer pk) {
		model.setActiveIndex(2);
		for (LocationBean bean : model.getLocations()) {
			if (bean.getPk().equals(pk)) {
				model.setLocation(bean);
				model.setActiveIndexLocation(1);
			}
		}
		return toAdministrate();
	}
	
	public String toEditCamp(Integer pk) {
		model.setActiveIndex(3);
		for (CampBean bean : model.getCamps()) {
			if (bean.getPk().equals(pk)) {
				model.setCamp(bean);
				model.setActiveIndexCamp(1);
			}
		}
		return toAdministrate();
	}

	public String doDeleteDocument() {
		model.setActiveIndex(4);
		try {
			new DocumentGateway(facesContext).deleteDocument(model.getDocument().getPk());
			model.setActiveIndexDocument(0);
			model.setDocument(new DocumentBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteDocument: ", e);
		}
		return toAdministrate();
	}

	public String doDeleteLocation() {
		model.setActiveIndex(2);
		try {
			new LocationGateway(facesContext).deleteLocation(model.getLocation().getPk());
			model.setActiveIndexLocation(0);
			model.setLocation(new LocationBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteLocation: ", e);
		}
		return toAdministrate();
	}
	
	public String doDeleteCamp() {
		model.setActiveIndex(3);
		try {
			new CampGateway(facesContext).deleteCamp(facesContext, model.getCamp().getPk());
			model.setActiveIndexCamp(0);
			model.setCamp(new CampBean());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteCamp: ", e);
		}
		return toAdministrate();
	}

	public String doUpsertDocument() {
		model.setActiveIndex(4);
		try {
			model.getDocument().encodeUpload();
			new DocumentGateway(facesContext).upsert(model.getDocument());
			model.setActiveIndexDocument(0);
			model.setDocument(new DocumentBean(null));
		} catch (DataAccessException | IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doUpsertDocument: ", e);
		}
		return toAdministrate();
	}

	public String doUpsertLocation() {
		model.setActiveIndex(2);
		try {
			new LocationGateway(facesContext).upsert(model.getLocation());
			model.setActiveIndexLocation(0);
			model.setLocation(new LocationBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doUpsertLocation: ", e);
		}
		return toAdministrate();
	}

	public String doUpsertCamp() {
		model.setActiveIndex(3);
		try {
			new CampGateway(facesContext).upsert(model.getCamp());
			model.setActiveIndexCamp(0);
			model.setCamp(new CampBean());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doUpsertCamp: ", e);
		}
		return toAdministrate();
	}

	public String doDownloadDocument(DocumentBean bean) {
		model.setActiveIndex(4);
		model.setActiveIndexDocument(0);
		return super.doDownloadBase64(facesContext, bean.getDocument(), bean.getName(), bean.getFiletype().getLiteral());
	}
	
	public String doDownloadDocument(CampBean bean) {
		model.setActiveIndex(3);
		model.setActiveIndexCamp(0);
		DocumentBean docBean = null;
		for (DocumentBean b : model.getDocuments()) {
			if (b.getPk().equals(bean.getFkDocument())) {
				docBean = b;
			}
		}
		if (docBean != null) {
			return super.doDownloadBase64(facesContext, docBean.getDocument(), docBean.getName(),
					docBean.getFiletype().getLiteral());
		} else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error download document",
					"Das gewünschte Dokument wurde nicht gefunden. Möglicherweise wurde es gerade eben gelöscht."));
			return "";
		}
	}

	public String doDownloadDocument(LocationBean bean) {
		model.setActiveIndex(2);
		model.setActiveIndexLocation(0);
		DocumentBean docBean = null;
		for (DocumentBean b : model.getDocuments()) {
			if (b.getPk().equals(bean.getFkDocument())) {
				docBean = b;
			}
		}
		if (docBean != null) {
			return super.doDownloadBase64(facesContext, docBean.getDocument(), docBean.getName(),
					docBean.getFiletype().getLiteral());
		} else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error download document",
					"Das gewünschte Dokument wurde nicht gefunden. Möglicherweise wurde es gerade eben gelöscht."));
			return "";
		}
	}

	public String doResetDocument() {
		model.setActiveIndex(4);
		model.setActiveIndexDocument(1);
		model.setDocument(new DocumentBean(null));
		return toAdministrate();
	}

	public String doResetLocation() {
		model.setActiveIndex(2);
		model.setActiveIndexLocation(1);
		model.setLocation(new LocationBean(null));
		return toAdministrate();
	}
	
	public String doResetCamp() {
		model.setActiveIndex(3);
		model.setActiveIndexCamp(1);
		model.setCamp(new CampBean());
		return toAdministrate();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void setModel(AdminModel model) {
		this.model = model;
	}
}
