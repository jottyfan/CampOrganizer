package de.jottyfan.camporganizer.modules.subscriber;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.db.SubscriberGateway;
import de.jottyfan.camporganizer.modules.admin.DocumentBean;
import de.jottyfan.camporganizer.modules.businessman.SalesController;
import de.jottyfan.camporganizer.profile.ProfileBean;

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

	public String toMain() {
		model.load(facesContext, profileBean.getPk());
		return "/pages/subscriber/main.jsf";
	}
	
	public String doDelete(SubscriberBean bean) {
		model.doDelete(facesContext, profileBean, bean);
	  return toMain();
	}
	
	public String doDownloadDocument(DocumentBean d) {
		super.doDownloadBase64(facesContext, d.getDocument(), d.getName(), d.getFiletype().getLiteral());
		return toMain();
	}
	
	public String toEdit(SubscriberBean bean) {
		model.loadPerson(facesContext, bean.getPkPerson());
		return "/pages/subscriber/edit.jsf";
	}
	
	public String doUpdate() {
		model.doUpdate(facesContext);
		return toMain();
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
