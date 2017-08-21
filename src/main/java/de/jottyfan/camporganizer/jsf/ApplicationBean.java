package de.jottyfan.camporganizer.jsf;

import java.io.IOException;
import java.util.Properties;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@ApplicationScoped
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
			return new StringBuilder("Version ").append(prop.getProperty("Implementation-Version")).toString();
		} catch (final IOException e) {
			return e.getMessage();
		}
	}
}
