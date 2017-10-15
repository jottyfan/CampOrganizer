package de.jottyfan.camporganizer.modules.admin;

/**
 * 
 * @author jotty
 *
 */
public class LocationBean {
	private final Integer pk;
	private String name;
	private String url;
	private Integer fkDocument;

	public LocationBean(Integer pk) {
		super();
		this.pk = pk;
	}

	public String showUrl(int charAmount) {
		if (url == null) {
			return "";
		} else {
			int endIndex = charAmount > url.length() ? url.length() - 1 : charAmount;
			return url == null ? "" : url.substring(0, endIndex) + (endIndex < url.length() ? "…" : "");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFkDocument() {
		return fkDocument;
	}

	public void setFkDocument(Integer fkDocument) {
		this.fkDocument = fkDocument;
	}

	public Integer getPk() {
		return pk;
	}
}
