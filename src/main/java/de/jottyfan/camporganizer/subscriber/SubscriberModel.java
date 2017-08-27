package de.jottyfan.camporganizer.subscriber;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jottyfan.camporganizer.CampBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class SubscriberModel {
	private List<SubscriberBean> camps;

	public List<SubscriberBean> getCamps() {
		return camps;
	}

	public void setCamps(List<SubscriberBean> camps) {
		this.camps = camps;
	}
}
