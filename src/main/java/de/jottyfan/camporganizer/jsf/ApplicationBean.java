package de.jottyfan.camporganizer.jsf;

import java.io.IOException;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class ApplicationBean {

	/**
	 * get version from maven build
	 * 
	 * @param facesContext
	 * @return
	 */
	public String getVersion(FacesContext facesContext) {
		final Properties prop = new Properties();
		try {
			prop.load(facesContext.getExternalContext().getResourceAsStream("/META-INF/MANIFEST.MF"));
			String version = prop.getProperty("Implementation-Version");
			return new StringBuilder("Version ").append(version == null ? prop.toString() : version).toString();
		} catch (final IOException e) {
			return e.getMessage();
		}
	}
}
