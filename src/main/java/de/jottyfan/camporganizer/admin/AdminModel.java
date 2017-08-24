package de.jottyfan.camporganizer.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
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
	private Integer activeIndexDocument;
	private ProfileRoleBean profileRole;
	private List<ProfileRoleBean> profileRoles;
	private List<ProfileBean> users;
	private List<String> roles;
	private DocumentBean document;
	private List<DocumentBean> documents;
	private List<EnumDocument> enumlistDoctype;
	private List<EnumFiletype> enumlistFiletype;

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

	public void setDocuments(List<DocumentBean> documents) {
		this.documents = documents;
	}

	public List<DocumentBean> getDocuments() {
		return documents;
	}

	public List<EnumDocument> getEnumlistDoctype() {
		return enumlistDoctype;
	}

	public void setEnumlistDoctype(List<EnumDocument> enumlistDoctype) {
		this.enumlistDoctype = enumlistDoctype;
	}

	public void setEnumlistFiletype(List<EnumFiletype> enumlistFiletype) {
		this.enumlistFiletype = enumlistFiletype;
	}

	public List<EnumFiletype> getEnumlistFiletype() {
		return enumlistFiletype;
	}

	public Integer getActiveIndexDocument() {
		return activeIndexDocument;
	}

	public void setActiveIndexDocument(Integer activeIndexDocument) {
		this.activeIndexDocument = activeIndexDocument;
	}

	public DocumentBean getDocument() {
		return document;
	}

	public void setDocument(DocumentBean document) {
		this.document = document;
	}
}
