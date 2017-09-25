package de.jottyfan.camporganizer.modules.businessman;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.SalesGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class SalesController extends Controller {

	private final static Logger LOGGER = LogManager.getLogger(SalesController.class);

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{salesModel}")
	private SalesModel model;

	@ManagedProperty(value = "#{salesBean}")
	private SalesBean bean;

	private String toSales(Integer activeIndex) {
		try {
			SalesGateway gw = new SalesGateway(facesContext);
			bean.setCamps(new CampGateway(facesContext).getAllCampsFromView(false));
			bean.setTraders(gw.getAllTraders());
			bean.setProviders(gw.getAllProviders());
			model.setList(gw.getAllSales());
		} catch (DataAccessException e) {
			bean.setCamps(new ArrayList<>());
			bean.setTraders(new ArrayList<>());
			bean.setProviders(new ArrayList<>());
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading camps", e.getMessage()));
		}
		model.setActiveIndex(activeIndex);
		return "/pages/sales.jsf";
	}

	public String toSales() {
		return toSales(0);
	}

	public String toEdit(SalesBean selection) {
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "not yet implemented",
				"Das Bearbeiten wurde noch nicht implementiert."));
		return toSales(1);
	}
	
	public String doDelete(SalesBean selection) {
		try {
			SalesGateway gw = new SalesGateway(facesContext);
			gw.delete(selection);
			model.setList(gw.getAllSales());
			return toSales(1);
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on deleting entry", e.getMessage()));
			return toSales(0);
		}
	}

	public String doInsert() {
		try {
			SalesGateway gw = new SalesGateway(facesContext);
			bean.uploadFile();
			gw.insert(bean);
			model.setList(gw.getAllSales());
			return toSales(1);
		} catch (DataAccessException | IOException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on adding entry", e.getMessage()));
			return toSales(0);
		}
	}

	public String doDownload(SalesBean selection) {
		try {
			if (selection.getRecipeshot() != null) {
				ExternalContext ec = facesContext.getExternalContext();
				ec.responseReset();
				ec.setResponseContentType("image/jpeg"); // most likely, photos are saved as jpegs
				ec.setResponseContentLength(selection.getRecipeshot().length);
				ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + selection.getRecipeNumber() + ".jpg\"");
				OutputStream out = ec.getResponseOutputStream();
				out.write(selection.getRecipeshot());
				out.flush();
				facesContext.responseComplete();
			} else {
				LOGGER.warn("image contains not data (is null)");
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error on downloading file",
						"Kassenzettel enthält keinen Inhalt"));
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on downloading file", e.getMessage()));
		}
		return toSales(1);
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public SalesBean getBean() {
		return bean;
	}

	public void setBean(SalesBean bean) {
		this.bean = bean;
	}

	public void setModel(SalesModel model) {
		this.model = model;
	}
}
