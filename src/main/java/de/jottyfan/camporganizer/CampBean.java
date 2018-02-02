package de.jottyfan.camporganizer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author jotty
 *
 */
public class CampBean {
	private Integer pk;
	private String name;
	private Integer fkLocation;
	private String locationName;
	private Integer year;
	private Integer minAge;
	private Integer maxAge;
	private String url;
	private Date arrive;
	private Date depart;
	private String price;
	private String countries;
	private Integer fkDocument;
	private Integer bookings;
	private Boolean lockSales;
	private Integer fkProfile;

	public String getFullCampname() {
		StringBuilder buf = new StringBuilder();
		buf.append(name).append(" ");
		buf.append(year).append(" in ");
		buf.append(locationName);
		return buf.toString();
	}

	public String getCampTime() {
		StringBuilder buf = new StringBuilder();
		buf.append(new SimpleDateFormat("dd.MM.").format(arrive));
		buf.append(" - ");
		buf.append(new SimpleDateFormat("dd.MM.yyyy").format(depart));
		return buf.toString();
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public Integer getFkDocument() {
		return fkDocument;
	}

	public void setFkDocument(Integer fkDocument) {
		this.fkDocument = fkDocument;
	}

	public Integer getFkLocation() {
		return fkLocation;
	}

	public void setFkLocation(Integer fkLocation) {
		this.fkLocation = fkLocation;
	}

	public Integer getBookings() {
		return bookings;
	}

	public void setBookings(Integer bookings) {
		this.bookings = bookings;
	}

	public Boolean getLockSales() {
		return lockSales;
	}

	public void setLockSales(Boolean lockSales) {
		this.lockSales = lockSales;
	}

	public Integer getFkProfile() {
		return fkProfile;
	}

	public void setFkProfile(Integer fkProfile) {
		this.fkProfile = fkProfile;
	}
}
