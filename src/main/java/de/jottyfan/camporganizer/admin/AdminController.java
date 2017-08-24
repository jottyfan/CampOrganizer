package de.jottyfan.camporganizer.admin;

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

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.DocumentGateway;
import de.jottyfan.camporganizer.db.ProfileGateway;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;

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
			if (model.getDocument() == null) {
				model.setDocument(new DocumentBean(null));
			}
			model.setDocuments(gw.getAllDocuments());
			model.setEnumlistDoctype(Arrays.asList(EnumDocument.values()));
			model.setEnumlistFiletype(Arrays.asList(EnumFiletype.values()));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.toAdministrate: ", e);
		}
		return "/pages/administrate.jsf";
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

	public String doDownloadDocument(DocumentBean bean) {
		model.setActiveIndex(4);
		model.setActiveIndexDocument(0);
		return super.doDownloadBase64(facesContext, bean.getDocument(), bean.getName(), bean.getFiletype().getLiteral());
	}
	
	public String doResetDocument() {
		model.setActiveIndex(4);
		model.setActiveIndexDocument(1);
		model.setDocument(new DocumentBean(null));
		return toAdministrate();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void setModel(AdminModel model) {
		this.model = model;
	}
}
