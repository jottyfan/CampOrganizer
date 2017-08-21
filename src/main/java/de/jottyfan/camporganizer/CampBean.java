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
	private String locationName;
	private Integer year;
	private Integer minAge;
	private Integer maxAge;
	private String url;
	private Date arrive;
	private Date depart;
	private String price;
	private String countries;
	
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
}