package de.jottyfan.camporganizer.sales;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.CampGateway;
import de.jottyfan.camporganizer.db.SalesGateway;
import de.jottyfan.camporganizer.register.CampBean;

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

	@ManagedProperty(value = "#{salesBean}")
	private SalesBean bean;

	private List<CampBean> camps;

	public String toSales() {
		// TODO: load recipe type list
		try {
			camps = new CampGateway(facesContext).getAllCamps();
		} catch (DataAccessException e) {
			facesContext.addMessage("failure",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading camps", e.getMessage()));
		}
		return "/pages/sales.jsf";
	}

	public String doInsert() {
		try {
			LOGGER.debug("add to DB: {}", bean.toString());
			new SalesGateway(facesContext).insert(bean);
			return toProfile();
		} catch (DataAccessException e) {
			facesContext.addMessage("failure",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on adding entry", e.getMessage()));
			return toSales();
		}
	}

	public List<CampBean> getCamps() {
		return camps;
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
}
