package de.jottyfan.camporganizer.modules.campadmin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;

/**
 * 
 * @author jotty
 * 
 */
@ManagedBean
@SessionScoped
public class CampadminModel {

	private Integer activeIndex;

	private Integer activeIndexDocument;
	private List<EnumDocument> enumlistDoctype;
	private List<EnumFiletype> enumlistFiletype;
	private DocumentBean document;
	private List<DocumentBean> documents;

	private Integer activeIndexLocation;
	private LocationBean location;
	private List<LocationBean> locations;

	private Integer activeIndexCamp;
	private CampBean camp;
	private List<CampBean> camps;
	
	public List<String> getPossibleCountries() {
		List<String> list = new ArrayList<>();
		list.add("Bayern");
		list.add("Baden-Württemberg");
		list.add("Berlin");
		list.add("Brandenburg");
		list.add("Bremen");
		list.add("Hamburg");
		list.add("Hessen");
		list.add("Mecklenburg-Vorpommern");
		list.add("Niedersachsen");
		list.add("Nordrhein-Westfalen");
		list.add("Rheinland-Pfalz");
		list.add("Saarland");
		list.add("Sachsen");
		list.add("Sachsen-Anhalt");
		list.add("Schleswig-Holstein");
		list.add("Thüringen");
		list.add("Heilbronn und Umgebung");
		return list;
	}

	public void setLocationNameToCamp() {
		for (CampBean camp : getCamps()) {
			for (LocationBean location : getLocations()) {
				if (location.getPk().equals(camp.getFkLocation())) {
					camp.setLocationName(location.getName());
				}
			}
		}
	}

	public Integer getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(Integer activeIndex) {
		this.activeIndex = activeIndex;
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

	public LocationBean getLocation() {
		return location;
	}

	public void setLocation(LocationBean location) {
		this.location = location;
	}

	public List<LocationBean> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationBean> locations) {
		this.locations = locations;
	}

	public Integer getActiveIndexLocation() {
		return activeIndexLocation;
	}

	public void setActiveIndexLocation(Integer activeIndexLocation) {
		this.activeIndexLocation = activeIndexLocation;
	}

	public Integer getActiveIndexCamp() {
		return activeIndexCamp;
	}

	public void setActiveIndexCamp(Integer activeIndexCamp) {
		this.activeIndexCamp = activeIndexCamp;
	}

	public CampBean getCamp() {
		return camp;
	}

	public void setCamp(CampBean camp) {
		this.camp = camp;
	}

	public List<CampBean> getCamps() {
		return camps;
	}

	public void setCamps(List<CampBean> camps) {
		this.camps = camps;
	}
}
