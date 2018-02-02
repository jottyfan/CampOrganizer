package de.jottyfan.camporganizer.modules.campadmin;

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
import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.profile.ProfileBean;
import de.jottyfan.camporganizer.rss.RssBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class CampadminController extends Controller {

	private static final Logger LOGGER = LogManager.getLogger(CampadminController.class);

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{campadminModel}")
	private CampadminModel model;

	public String toCampadmin() {
		try {
			ProfileGateway gw = new ProfileGateway(facesContext);
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
			
			model.setProfiles(new ProfileGateway(facesContext).getAllUsersWithRole(EnumRole.registrator));
			model.orderProfiles();

		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.toAdministrate: ", e);
		}
		return "/pages/campadmin/edit.jsf";
	}

	public String toEditDocument(Integer pk) {
		model.setActiveIndex(2);
		for (DocumentBean bean : model.getDocuments()) {
			if (bean.getPk().equals(pk)) {
				model.setDocument(bean);
				model.setActiveIndexDocument(1); // element tab
			}
		}
		return toCampadmin();
	}

	public String toEditLocation(Integer pk) {
		model.setActiveIndex(0);
		for (LocationBean bean : model.getLocations()) {
			if (bean.getPk().equals(pk)) {
				model.setLocation(bean);
				model.setActiveIndexLocation(1);
			}
		}
		return toCampadmin();
	}

	public String toEditCamp(Integer pk) {
		model.setActiveIndex(1);
		for (CampBean bean : model.getCamps()) {
			if (bean.getPk().equals(pk)) {
				model.setCamp(bean);
				model.setActiveIndexCamp(1);
			}
		}
		return toCampadmin();
	}

	public String doLockSales() {
		boolean result = model.lockSales(facesContext);
		if (result) {
			model.setCamps(new CampGateway(facesContext).getAllCampsFromTable());
		}
		return toEditCamp(model.getCamp().getPk());
	}
	
	public String doDeleteDocument() {
		model.setActiveIndex(2);
		try {
			new DocumentGateway(facesContext).deleteDocument(model.getDocument().getPk());
			model.setActiveIndexDocument(0);
			model.setDocument(new DocumentBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteDocument: ", e);
		}
		return toCampadmin();
	}

	public String doDeleteLocation() {
		model.setActiveIndex(0);
		try {
			new LocationGateway(facesContext).deleteLocation(model.getLocation().getPk());
			model.setActiveIndexLocation(0);
			model.setLocation(new LocationBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteLocation: ", e);
		}
		return toCampadmin();
	}

	public String doDeleteCamp() {
		model.setActiveIndex(1);
		try {
			new CampGateway(facesContext).deleteCamp(facesContext, model.getCamp().getPk());
			model.setActiveIndexCamp(0);
			model.setCamp(new CampBean());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doDeleteCamp: ", e);
		}
		return toCampadmin();
	}

	public String doUpsertDocument() {
		model.setActiveIndex(2);
		try {
			model.getDocument().encodeUpload();
			new DocumentGateway(facesContext).upsert(model.getDocument());
			model.setActiveIndexDocument(0);
			model.setDocument(new DocumentBean(null));
		} catch (DataAccessException | IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doUpsertDocument: ", e);
		}
		return toCampadmin();
	}

	public String doUpsertLocation() {
		model.setActiveIndex(0);
		try {
			new LocationGateway(facesContext).upsert(model.getLocation());
			model.setActiveIndexLocation(0);
			model.setLocation(new LocationBean(null));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doUpsertLocation: ", e);
		}
		return toCampadmin();
	}

	public String doUpsertCamp() {
		model.setActiveIndex(1);
		try {
			new CampGateway(facesContext).upsert(model.getCamp());
			model.setActiveIndexCamp(0);
			model.setCamp(new CampBean());
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("AdminController.doUpsertCamp: ", e);
		}
		return toCampadmin();
	}

	public String doDownloadDocument(DocumentBean bean) {
		model.setActiveIndex(2);
		model.setActiveIndexDocument(0);
		return super.doDownloadBase64(facesContext, bean.getDocument(), bean.getName(), bean.getFiletype().getLiteral());
	}

	public String doDownloadDocument(CampBean bean) {
		model.setActiveIndex(1);
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
		model.setActiveIndex(0);
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
		model.setActiveIndex(2);
		model.setActiveIndexDocument(1);
		model.setDocument(new DocumentBean(null));
		return toCampadmin();
	}

	public String doResetLocation() {
		model.setActiveIndex(0);
		model.setActiveIndexLocation(1);
		model.setLocation(new LocationBean(null));
		return toCampadmin();
	}

	public String doResetCamp() {
		model.setActiveIndex(1);
		model.setActiveIndexCamp(1);
		model.setCamp(new CampBean());
		return toCampadmin();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void setModel(CampadminModel model) {
		this.model = model;
	}
}
