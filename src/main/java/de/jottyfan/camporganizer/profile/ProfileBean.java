package de.jottyfan.camporganizer.profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * 
 * @author henkej
 *
 */
@ManagedBean
@SessionScoped
public class ProfileBean {
	private String forename;
	private String surname;
	private String username;
	private String password;

	public String getFullname() {
		return new StringBuilder(forename).append(" ").append(surname).toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new StrongPasswordEncryptor().encryptPassword(password);
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

	public boolean checkPassword(String plainPassword) {
		return new StrongPasswordEncryptor().checkPassword(plainPassword, password);
	}
}
