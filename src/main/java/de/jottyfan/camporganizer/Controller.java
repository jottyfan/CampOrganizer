package de.jottyfan.camporganizer;

/**
 * 
 * @author jotty
 *
 */
public abstract class Controller {
	public String toLogin() {
		return "/pages/login.jsf";
	}

	public String toProfile() {
		return "/pages/profile.jsf";
	}

	public String toRegister() {
		return "/pages/register.jsf";
	}
}
