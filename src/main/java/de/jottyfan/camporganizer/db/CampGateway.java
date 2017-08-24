package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.V_CAMP;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record11;
import org.jooq.SelectSeekStep1;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.CampBean;

/**
 * 
 * @author jotty
 *
 */
public class CampGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(CampGateway.class);

	public CampGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * get all camps from db
	 * 
	 * @param futureOnly
	 *          if true, load only camps that have not yet started
	 * 
	 * @return list of camps
	 * @throws DataAccessException
	 */
	public List<CampBean> getAllCamps(boolean futureOnly) throws DataAccessException {
		Timestamp limitDate = new Timestamp(0);
		if (futureOnly)
		{
			limitDate.setTime(new Date().getTime());
		}
		SelectSeekStep1<Record11<Integer, String, String, Double, Integer, Integer, String, Timestamp, Timestamp, String, String>, Timestamp> sql = getJooq()
		// @formatter:off
			.select(V_CAMP.PK,
					    V_CAMP.NAME,
					    V_CAMP.LOCATION_NAME,
					    V_CAMP.YEAR,
					    V_CAMP.MIN_AGE,
					    V_CAMP.MAX_AGE,
					    V_CAMP.URL,
					    V_CAMP.ARRIVE,
					    V_CAMP.DEPART,
					    V_CAMP.PRICE,
					    V_CAMP.COUNTRIES)
			.from(V_CAMP)
			.where(V_CAMP.ARRIVE.greaterThan(limitDate))
			.orderBy(V_CAMP.ARRIVE);
	  // @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<CampBean> list = new ArrayList<>();
		for (Record11<Integer, String, String, Double, Integer, Integer, String, Timestamp, Timestamp, String, String> r : sql.fetch()) {
			CampBean bean = new CampBean();
			bean.setPk(r.get(V_CAMP.PK));
			bean.setName(r.get(V_CAMP.NAME));
			bean.setLocationName(r.get(V_CAMP.LOCATION_NAME));
			bean.setMinAge(r.get(V_CAMP.MIN_AGE));
			bean.setMaxAge(r.get(V_CAMP.MAX_AGE));
			bean.setUrl(r.get(V_CAMP.URL));
			bean.setArrive(r.get(V_CAMP.ARRIVE));
			bean.setDepart(r.get(V_CAMP.DEPART));
			bean.setPrice(r.get(V_CAMP.PRICE));
			bean.setCountries(r.get(V_CAMP.COUNTRIES));
			Double year = r.get(V_CAMP.YEAR);
			if (year != null) {
				bean.setYear(year.intValue());
			}
			list.add(bean);
		}
		return list;
	}
}
