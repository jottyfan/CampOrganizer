package de.jottyfan.camporganizer.modules.registrator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.jottyfan.camporganizer.Controller;
import de.jottyfan.camporganizer.modules.subscriber.PersondocumentBean;
import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class RegistratorController extends Controller {
	
	@ManagedProperty(value="#{facesContext}")
	private FacesContext facesContext;

	@ManagedProperty(value="#{registratorModel}")
	private RegistratorModel model;
	
	@ManagedProperty(value="#{profileBean}")
	private ProfileBean profileBean;
	
	public String toMain() {
		model.loadRegistratorPageContent(facesContext, profileBean);
		return "/pages/registrator/main.jsf";
	}
	
	public String toEdit(RegistratorBean bean) {
		model.prepareEdit(bean);
		return "/pages/registrator/edit.jsf";
	}
	
	public String doUpdate() {
		model.update(facesContext, profileBean);
		return toMain();
	}
	
	public String doAccept(RegistratorBean bean) {
		model.acceptRegistration(facesContext, bean, profileBean);
		return toMain();
	}
	
	public String doDelete(Integer pk) {
		model.deleteRegistration(facesContext, pk);
		return toMain();
	}
	
	public String doReject(RegistratorBean bean) {
		model.rejectRegistration(facesContext, bean, profileBean);
		return toMain();
	}
	
	public String doDownloadDocument(PersondocumentBean d) {
		super.doDownloadBase64(facesContext, d.getDocument(), d.getName(), d.getFiletype().getLiteral());
		return toMain();
	}
	
	public String doDownloadCampAsCsv() {
		model.doDownloadCsv(facesContext);
		return toMain();
	}
	
	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public RegistratorModel getModel() {
		return model;
	}

	public void setModel(RegistratorModel model) {
		this.model = model;
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}
}
