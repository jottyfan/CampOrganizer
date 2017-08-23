package de.jottyfan.camporganizer.registrator;

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
	
	public String toRegistrator() {
		model.loadRegistratorPageContent(facesContext);
		return "/pages/registrator.jsf";
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
