package de.jottyfan.camporganizer.modules.campadmin;

import java.util.Date;

/**
 * 
 * @author jotty
 *
 */
public class CampprofileBean {

	private Integer pk;
	private String forename;
	private String surname;
	private String campname;
	private Date startdate;
	private String module;
	private Integer fkProfile;
	private Integer fkCamp;

	/**
	 * get the full name of the person
	 * 
	 * @return forname + space + surname
	 */
	public String getFullname() {
		return new StringBuilder("").append(forename).append(" ").append(surname).toString();
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCampname() {
		return campname;
	}

	public void setCampname(String campname) {
		this.campname = campname;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public Integer getFkProfile() {
		return fkProfile;
	}

	public void setFkProfile(Integer fkProfile) {
		this.fkProfile = fkProfile;
	}

	public Integer getFkCamp() {
		return fkCamp;
	}

	public void setFkCamp(Integer fkCamp) {
		this.fkCamp = fkCamp;
	}
}
