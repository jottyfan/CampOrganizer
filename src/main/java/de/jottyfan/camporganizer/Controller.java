package de.jottyfan.camporganizer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * 
 * @author jotty
 *
 */
public abstract class Controller {
	public String toLogin() {
		return "/pages/login.jsf";
	}

	public String toProfile() {
		return "/pages/profile.jsf";
	}

	public String toRegister() {
		return "/pages/register.jsf";
	}

	/**
	 * download file encoded in base64
	 * 
	 * @param contentBase64
	 * @param filetype
	 * @return empty string for an error
	 */
	public String doDownloadBase64(FacesContext facesContext, String contentBase64, String name, String filetype) {
		if (contentBase64 == null || contentBase64.isEmpty()) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on downloading",
					"Das Dokument ist leer und enthält kein Daten."));
		} else {
			byte[] decodedContent = Base64.getDecoder().decode(contentBase64);
			ExternalContext ec = facesContext.getExternalContext();
			ec.responseReset();
			ec.setResponseContentType(filetype);
			ec.setResponseContentLength(decodedContent.length);
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + name + "." + filetype + "\"");
			try {
				OutputStream out = ec.getResponseOutputStream();
				out.write(decodedContent);
				out.flush();
				facesContext.responseComplete();
			} catch (IOException e) {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "error on downloading", e.getMessage()));
			}
		}
		return "";
	}
}
