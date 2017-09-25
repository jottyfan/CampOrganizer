package de.jottyfan.camporganizer.subscriber;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.admin.DocumentBean;
import de.jottyfan.camporganizer.db.SubscriberGateway;
import de.jottyfan.camporganizer.profile.ProfileBean;
import de.jottyfan.camporganizer.sales.SalesController;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class SubscriberController extends Controller {

	@ManagedProperty(value = "#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value = "#{subscriberModel}")
	private SubscriberModel model;

	@ManagedProperty(value = "#{profileBean}")
	private ProfileBean profileBean;

	public String toSubscriber() {
		model.load(facesContext, profileBean.getPk());
		return "/pages/subscriber.jsf";
	}
	
	public String doDelete(SubscriberBean bean) {
		model.doDelete(facesContext, profileBean, bean);
	  return toSubscriber();
	}
	
	public String doDownloadDocument(DocumentBean d) {
		super.doDownloadBase64(facesContext, d.getDocument(), d.getName(), d.getFiletype().getLiteral());
		return toSubscriber();
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void setModel(SubscriberModel model) {
		this.model = model;
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}
}
