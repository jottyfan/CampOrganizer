package de.jottyfan.camporganizer.book;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.db.CampGateway;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@RequestScoped
public class BookModel {

	/**
	 * get all camps; as there is no toLogin method, all camps are read from db just here
	 * 
	 * @return list of found camp beans
	 */
	public List<CampBean> getCamps(FacesContext facesContext) {
		return new CampGateway(facesContext).getAllCamps(true); 
		// do not catch DataAccessException, as there is no
		// facesMessage rendered here (jsf lifecycle is too late)
	}
}
