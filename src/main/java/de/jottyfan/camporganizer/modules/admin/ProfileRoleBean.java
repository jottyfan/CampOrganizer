package de.jottyfan.camporganizer.modules.admin;

import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
public class ProfileRoleBean {
	private ProfileBean user;
	private String role;

	public ProfileBean getUser() {
		return user;
	}

	public void setUser(ProfileBean user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
