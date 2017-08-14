package de.jottyfan.camporganizer.sales;

import java.util.ArrayList;
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

	private List<SalesBean> list;

	public String toSales() {
		try {
			bean.setCamps(new CampGateway(facesContext).getAllCamps(false));
			list = new SalesGateway(facesContext).getAllSales();
		} catch (DataAccessException e) {
			bean.setCamps(new ArrayList<>());
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on loading camps", e.getMessage()));
		}
		return "/pages/sales.jsf";
	}

	public String doInsert() {
		try {
			SalesGateway gw = new SalesGateway(facesContext);
			gw.insert(bean);
			list = new SalesGateway(facesContext).getAllSales();
			return toProfile();
		} catch (DataAccessException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on adding entry", e.getMessage()));
			return toSales();
		}
	}

	public String doDownload() {
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "not yet implemented", "noch nicht entwickelt"));
		return toSales();
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

	public List<SalesBean> getList() {
		return list;
	}
}
