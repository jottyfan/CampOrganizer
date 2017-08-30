package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record4;
import org.jooq.SelectOnConditionStep;
import org.jooq.exception.DataAccessException;

import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.io.TimezoneAssignment;
import biweekly.property.Summary;
import biweekly.property.Timezone;
import biweekly.util.Duration;
import biweekly.util.Recurrence;

/**
 * 
 * @author jotty
 *
 */
public class IcalGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(RssGateway.class);

	public IcalGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * load all camp dates from db and generate an ical from it
	 * 
	 * @return ical containing all (at least none) of the camp dates
	 * @throws DataAccessException
	 */
	public ICalendar getIcal() throws DataAccessException {
		SelectOnConditionStep<Record4<String, Timestamp, Timestamp, String>> sql = getJooq()
		// @formatter:off
			.select(T_CAMP.NAME,
					    T_CAMP.ARRIVE,
					    T_CAMP.DEPART,
					    T_LOCATION.NAME)
			.from(T_CAMP)
		  .leftJoin(T_LOCATION).on(T_LOCATION.PK.eq(T_CAMP.FK_LOCATION));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		ICalendar ical = new ICalendar();
		ical.getTimezoneInfo().setDefaultTimezone(TimezoneAssignment.download(TimeZone.getTimeZone("Europe/Berlin"), false));
		for (Record4<String, Timestamp, Timestamp, String> r : sql.fetch()) {
			VEvent event = new VEvent();
			Summary summary = event.setSummary(r.get(T_CAMP.NAME));
			summary.setLanguage("de");
			event.setLocation(r.get(T_LOCATION.NAME));
			event.setDateStart(r.get(T_CAMP.ARRIVE), false);
			event.setDateEnd(r.get(T_CAMP.DEPART), false);
			ical.addEvent(event);
		}
		return ical;
	}
}
