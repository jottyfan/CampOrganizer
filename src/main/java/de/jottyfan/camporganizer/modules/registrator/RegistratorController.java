package de.jottyfan.camporganizer.modules.registrator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.jottyfan.camporganizer.Controller;

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
	
	public String toMain() {
		model.loadRegistratorPageContent(facesContext);
		return "/pages/registrator/main.jsf";
	}
	
	public String toEdit(RegistratorBean bean) {
		model.prepareEdit(bean);
		return "/pages/registrator/edit.jsf";
	}
	
	public String doUpdate() {
		model.update(facesContext);
		return toMain();
	}
	
	public String doAccept(RegistratorBean bean) {
		model.acceptRegistration(facesContext, bean);
		return toMain();
	}
	
	public String doReject(Integer pk) {
		model.rejectRegistration(facesContext, pk);
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
}
