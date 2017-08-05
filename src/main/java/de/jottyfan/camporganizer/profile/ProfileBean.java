package de.jottyfan.camporganizer.profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author henkej
 *
 */
@ManagedBean
@SessionScoped
public class ProfileBean {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean checkPassword(String comparable){
		return comparable.equals(password);
	}

	public String getPassword() {
		return null;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
