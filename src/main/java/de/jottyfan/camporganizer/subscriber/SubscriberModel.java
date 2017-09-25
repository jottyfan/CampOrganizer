package de.jottyfan.camporganizer.subscriber;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.db.SubscriberGateway;
import de.jottyfan.camporganizer.profile.ProfileBean;
import de.jottyfan.camporganizer.sales.SalesController;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class SubscriberModel {
	private final static Logger LOGGER = LogManager.getLogger(SubscriberModel.class);

	private List<SubscriberBean> camps;

	public List<SubscriberBean> getCamps() {
		return camps;
	}

	public void setCamps(List<SubscriberBean> camps) {
		this.camps = camps;
	}

	public void doDelete(FacesContext facesContext, ProfileBean profileBean, SubscriberBean bean) {
		try {
			new SubscriberGateway(facesContext).delete(profileBean, bean);
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.doDelete", e);
		}
	}

	public void load(FacesContext facesContext, Integer pk) {
		try {
			setCamps(new SubscriberGateway(facesContext).getCamps(pk));
		} catch (DataAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "database error", e.getMessage()));
			LOGGER.error("SubscriberModel.load", e);
		}
	}
}
