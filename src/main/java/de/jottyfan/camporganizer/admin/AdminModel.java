package de.jottyfan.camporganizer.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jottyfan.camporganizer.profile.ProfileBean;

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
}
