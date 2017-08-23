package de.jottyfan.camporganizer.registrator;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.jottyfan.camporganizer.db.converter.EnumConverter;
import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;

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
	private String email;
	private String campname;
	private Integer minAge;
	private Integer maxAge;
	private Date arrive;
	private Date depart;
	private EnumCamprole camprole;

	public RegistratorBean(Integer pk) {
		super();
		this.pk = pk;
	}

	public String getNote() {
		StringBuilder buf = new StringBuilder();
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
					buf.append("hat während der Freizeit Geburtstag");
				}
			}
			if (minAge != null && maxAge != null) {
				LocalDate now = LocalDate.now();
				LocalDate bDate = LocalDate.of(birthdate.getYear(), birthdate.getMonth(), birthdate.getDate());
				Integer years = Period.between(bDate, now).getYears();
				if (minAge < years || maxAge > years) {
					// ignore helpers and kitchen
					if (EnumCamprole.boy.equals(camprole) || EnumCamprole.girl.equals(camprole)) {
						buf.append("passt vom Alter her nicht in die Freizeit");
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

	public String getCamproleAsString() {
		return new EnumConverter().getStringCamprole(camprole);
	}

	public EnumCamprole getCamprole() {
		return camprole;
	}

	public void setCamprole(EnumCamprole camprole) {
		this.camprole = camprole;
	}
}
