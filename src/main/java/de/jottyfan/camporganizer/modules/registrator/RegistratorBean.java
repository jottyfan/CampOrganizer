package de.jottyfan.camporganizer.modules.registrator;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.jottyfan.camporganizer.db.converter.EnumConverter;
import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.enums.EnumSex;
import de.jottyfan.camporganizer.modules.subscriber.PersondocumentBean;

/**
 * 
 * @author jotty
 *
 */
public class RegistratorBean {
	private final Integer pk;
	private String forename;
	private String surname;
	private String street;
	private String zip;
	private String city;
	private String phone;
	private Date birthdate;
	private EnumSex sex;
	private String email;
	private Date created;
	private String campname;
	private Integer minAge;
	private Integer maxAge;
	private Date arrive;
	private Date depart;
	private EnumCamprole camprole;
	private Boolean accept;
	private String profileForename;
	private String profileSurname;
	private String profileUUID;
	private String registratorForename;
	private String registratorSurname;
	private List<PersondocumentBean> documents;

	public RegistratorBean(Integer pk) {
		super();
		this.pk = pk;
		this.documents = new ArrayList<>();
	}

	public String getNote() {
		StringBuilder buf = new StringBuilder();
		if (accept == null) {
			if (profileForename == null && profileSurname == null) {
				buf.append("! bitte per Post bestätigen\n");
			} else {
				buf.append("* angemeldet von ");
				buf.append(profileForename).append(" ").append(profileSurname).append("\n");
			}
		} else if (accept) {
			buf.append("* bestätigt von ");
			buf.append(registratorForename == null ? "unbekannt" : registratorForename);
			buf.append(" ");
			buf.append(registratorSurname == null ? "" : registratorSurname);
			buf.append("\n");
		} else {
			buf.append("* abgelehnt von ");
			buf.append(registratorForename == null ? "unbekannt" : registratorForename);
			buf.append(" ");
			buf.append(registratorSurname == null ? "" : registratorSurname);
			buf.append("\n");
		}
		if (birthdate != null) {
			if (arrive != null && depart != null) {
				// check birthdate in camp
				Calendar aDate = new GregorianCalendar();
				Calendar dDate = new GregorianCalendar();
				Calendar bDate = new GregorianCalendar();
				aDate.setTime(arrive);
				dDate.setTime(depart);
				bDate.setTime(birthdate);
				bDate.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
				if (aDate.before(bDate) && dDate.after(bDate)) {
					buf.append("! hat während der Freizeit Geburtstag\n");
				}
			}
			if (minAge != null && maxAge != null) {
				Calendar aCal = new GregorianCalendar();
				Calendar bCal = new GregorianCalendar();
				aCal.setTime(arrive);
				bCal.setTime(birthdate);
				LocalDate aDate = LocalDate.of(aCal.get(Calendar.YEAR), aCal.get(Calendar.MONTH) + 1, aCal.get(Calendar.DATE));
				LocalDate bDate = LocalDate.of(bCal.get(Calendar.YEAR), bCal.get(Calendar.MONTH) + 1, bCal.get(Calendar.DATE));
				Integer years = Period.between(bDate, aDate).getYears();
				if (years < minAge || years > maxAge) {
					// ignore helpers and kitchen
					if (EnumCamprole.student.equals(camprole)) {
						buf.append("! passt vom Alter her nicht in die Freizeit\n");
					}
				}
			}
		}
		return buf.toString();
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

	public String getCampname() {
		return campname;
	}

	public void setCampname(String campname) {
		this.campname = campname;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public Date getArrive() {
		return arrive;
	}

	public void setArrive(Date arrive) {
		this.arrive = arrive;
	}

	public Date getDepart() {
		return depart;
	}

	public void setDepart(Date depart) {
		this.depart = depart;
	}

	public Integer getPk() {
		return pk;
	}

	public String getCamproleGerman() {
		return new EnumConverter().getCamproleGerman(camprole);
	}

	public String getSexGerman() {
		return new EnumConverter().getSexGerman(sex);
	}

	public EnumCamprole getCamprole() {
		return camprole;
	}

	public void setCamprole(EnumCamprole camprole) {
		this.camprole = camprole;
	}

	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	public String getProfileForename() {
		return profileForename;
	}

	public void setProfileForename(String profileForename) {
		this.profileForename = profileForename;
	}

	public String getProfileSurname() {
		return profileSurname;
	}

	public void setProfileSurname(String profileSurname) {
		this.profileSurname = profileSurname;
	}

	public String getProfileUUID() {
		return profileUUID;
	}

	public void setProfileUUID(String profileUUID) {
		this.profileUUID = profileUUID;
	}

	public List<PersondocumentBean> getDocuments() {
		return documents;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public EnumSex getSex() {
		return sex;
	}

	public void setSex(EnumSex sex) {
		this.sex = sex;
	}

	public String getRegistratorForename() {
		return registratorForename;
	}

	public void setRegistratorForename(String registratorForename) {
		this.registratorForename = registratorForename;
	}

	public String getRegistratorSurname() {
		return registratorSurname;
	}

	public void setRegistratorSurname(String registratorSurname) {
		this.registratorSurname = registratorSurname;
	}
}
