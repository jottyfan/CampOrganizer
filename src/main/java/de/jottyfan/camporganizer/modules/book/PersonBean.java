package de.jottyfan.camporganizer.modules.book;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import de.jottyfan.camporganizer.CampBean;

/**
 * 
 * @author jotty
 *
 */
@ManagedBean
@SessionScoped
public class PersonBean {

	@CsvBindByPosition(position = 0)
	@CsvBindByName(column = "Vorname")
	private String forename;

	@CsvBindByPosition(position = 1)
	@CsvBindByName(column = "Nachname")
	private String surname;

	@CsvBindByPosition(position = 2)
	@CsvBindByName(column = "Straße")
	private String street;

	@CsvBindByPosition(position = 3)
	@CsvBindByName(column = "PLZ")
	private String zip;

	@CsvBindByPosition(position = 4)
	@CsvBindByName(column = "Ort")
	private String city;

	@CsvBindByPosition(position = 5)
	@CsvBindByName(column = "Telefon")
	private String phone;

	@CsvBindByPosition(position = 6)
	@CsvBindByName(column = "E-Mail")
	private String email;

	@CsvBindByPosition(position = 7)
	@CsvBindByName(column = "Geburtstag")
	@CsvDate("dd.MM.yyyy")
	private Date birthdate;

	@CsvBindByPosition(position = 8)
	@CsvBindByName(column = "Status")
	private String camprole;

	private Integer fkCamp;
	private Integer fkProfile;

	private List<CampBean> camps;

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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCamprole() {
		return camprole;
	}

	public void setCamprole(String camprole) {
		this.camprole = camprole;
	}

	public Integer getFkCamp() {
		return fkCamp;
	}

	public void setFkCamp(Integer fkCamp) {
		this.fkCamp = fkCamp;
	}

	public List<CampBean> getCamps() {
		return camps;
	}

	public void setCamps(List<CampBean> camps) {
		this.camps = camps;
	}

	public Integer getFkProfile() {
		return fkProfile;
	}

	public void setFkProfile(Integer fkProfile) {
		this.fkProfile = fkProfile;
	}
}
