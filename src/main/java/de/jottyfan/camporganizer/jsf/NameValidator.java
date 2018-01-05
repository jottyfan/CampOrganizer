package de.jottyfan.camporganizer.jsf;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
@FacesValidator(value = "de.jottyfan.camporganizer.jsf.NameValidator")
public class NameValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object object) throws ValidatorException {
		String name = new StringBuilder().append(object).toString();
		@SuppressWarnings("unchecked")
		List<String> listOfNames = (List<String>) component.getAttributes().get("listOfNames");
		ProfileBean profileBean = (ProfileBean) facesContext.getExternalContext().getSessionMap().get("profileBean");
		if (name.equals(profileBean.getUsername())) {
			// this is not critical, as there might not be a username change
		} else if (listOfNames.contains(name)) {
			StringBuilder msg = new StringBuilder(name);
			msg.append(" ist bereits vergeben, bitte einen anderen wählen.");
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on change", msg.toString());
			throw new ValidatorException(fm);
		}
	}
}
