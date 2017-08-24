package de.jottyfan.camporganizer.rss;

import java.util.ArrayList;
import java.util.List;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;

/**
 * 
 * @author jotty
 *
 */
public class RssModel {
	public SyndFeed getRss(List<RssBean> beans) {
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("Onkel Werner Freizeiten e.V. Anmeldungsnotifier");
		feed.setLink("https://onkelwernerfreizeiten.de/camporganizer/rss.jsf");
		feed.setDescription("In diesem Feed werden neue Anmeldungen gesammelt.");
		List<SyndEntry> entries = new ArrayList<>();
		for (RssBean bean : beans) {
			SyndEntry entry = new SyndEntryImpl();
			entry.setTitle("neue Anmeldung");
			entry.setLink(feed.getLink());
			entry.setPublishedDate(bean.getPubdate());			
			SyndContent description = new SyndContentImpl();
			description.setType("text/plain");
			description.setValue(bean.getMessage());
			entry.setDescription(description);
			entries.add(entry);
		}
		feed.setEntries(entries);
		return feed;
	}
}
