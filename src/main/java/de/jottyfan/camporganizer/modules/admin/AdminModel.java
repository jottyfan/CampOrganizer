package de.jottyfan.camporganizer.modules.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.modules.campadmin.DocumentBean;
import de.jottyfan.camporganizer.modules.campadmin.LocationBean;
import de.jottyfan.camporganizer.profile.ProfileBean;
import de.jottyfan.camporganizer.rss.RssBean;

/**
 * 
 * @author jotty
 * 
 */
@ManagedBean
@SessionScoped
public class AdminModel {

	private Integer activeIndex;
	private ProfileRoleBean profileRole;
	private List<ProfileRoleBean> profileRoles;
	private List<ProfileBean> users;
	private List<String> roles;
	
	private Integer activeIndexRss;
	private RssBean rss;
	private List<RssBean> rssList;

	public Integer getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(Integer activeIndex) {
		this.activeIndex = activeIndex;
	}

	public ProfileRoleBean getProfileRole() {
		return profileRole;
	}

	public void setProfileRole(ProfileRoleBean profileRole) {
		this.profileRole = profileRole;
	}

	public List<ProfileRoleBean> getProfileRoles() {
		return profileRoles;
	}

	public void setProfileRoles(List<ProfileRoleBean> profileRoles) {
		this.profileRoles = profileRoles;
	}

	public List<ProfileBean> getUsers() {
		return users;
	}

	public void setUsers(List<ProfileBean> users) {
		this.users = users;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public RssBean getRss() {
		return rss;
	}

	public void setRss(RssBean rss) {
		this.rss = rss;
	}

	public List<RssBean> getRssList() {
		return rssList;
	}

	public Integer getActiveIndexRss() {
		return activeIndexRss;
	}

	public void setActiveIndexRss(Integer activeIndexRss) {
		this.activeIndexRss = activeIndexRss;
	}

	public void setRssList(List<RssBean> rssList) {
		this.rssList = rssList;
	}
}
