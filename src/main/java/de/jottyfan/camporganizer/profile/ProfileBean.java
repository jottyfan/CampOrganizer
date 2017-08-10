package de.jottyfan.camporganizer.profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jooq.exception.DataAccessException;

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
	private String passwordAgain;
	private String encryptedPassword;
	private String roles;

	public boolean checkPasswordAndForgetPlainOne() throws DataAccessException {
		boolean result = false;
		try {
			result = new StrongPasswordEncryptor().checkPassword(password, encryptedPassword);
		} catch (EncryptionOperationNotPossibleException e) {
			return false; // password is not decryptable
		}
		if (result) {
			password = null;
			return result;
		} else {
			return false;
		}
	}
	
	public boolean hasRole(String role) {
		return roles.contains(role);
	}

	public String getFullname() {
		return new StringBuilder(forename == null ? "" : forename).append(" ").append(surname == null ? "" : surname)
				.toString();
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

	public void setPasswordButKeepEncrypted(String password) {
		this.password = password;
	}
	
	/**
	 * use this from the web pages
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
		this.encryptedPassword = new StrongPasswordEncryptor().encryptPassword(password);
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * use this in the gateway for database content only
	 * 
	 * @param encryptedPassword
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
