package de.jottyfan.camporganizer.rss;

import java.util.Date;

/**
 * 
 * @author jotty
 *
 */
public class RssBean {
	public String message;
	public Date pubdate;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
}
