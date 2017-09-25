package de.jottyfan.camporganizer.modules.subscriber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.jottyfan.camporganizer.modules.admin.DocumentBean;

/**
 * 
 * @author jotty
 *
 */
public class SubscriberBean {
	private final Integer pkPerson;
	private final String fullname;
	private String locationname;
	private String campname;
	private Date arrive;
	private Date depart;
	private Boolean accept;
	private String url;
	private List<DocumentBean> documents;

	public SubscriberBean(Integer pkPerson, String fullname) {
		this.pkPerson = pkPerson;
		this.fullname = fullname;
	}
	
	public String getCamptime() {
		StringBuilder buf = new StringBuilder();
		buf.append(new SimpleDateFormat("dd.MM").format(arrive).toString());
		buf.append(" - ");
		buf.append(new SimpleDateFormat("dd.MM.yyyy").format(depart).toString());
		return buf.toString();
	}
	
	public String getCampname() {
		return campname;
	}

	public void setCampname(String campname) {
		this.campname = campname;
	}

	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	public Date getArrive() {
		return arrive;
	}

	public void setArrive(Date arrive) {
		this.arrive = arrive;
	}

	public Date getDepart() {
		return depart;
	}

	public void setDepart(Date depart) {
		this.depart = depart;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<DocumentBean> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentBean> documents) {
		this.documents = documents;
	}

	public Integer getPkPerson() {
		return pkPerson;
	}

	public String getFullname() {
		return fullname;
	}
}
