package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.V_CAMP;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record3;
import org.jooq.SelectSeekStep1;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.register.CampBean;

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
		SelectSeekStep1<Record3<Integer, String, Double>, Timestamp> sql = getJooq()
		// @formatter:off
			.select(V_CAMP.PK,
					    V_CAMP.NAME,
					    V_CAMP.YEAR)
			.from(V_CAMP)
			.where(V_CAMP.IS_OVER.notIn(true, futureOnly))
			.orderBy(V_CAMP.ARRIVE);
	  // @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<CampBean> list = new ArrayList<>();
		for (Record3<Integer, String, Double> r : sql.fetch()) {
			CampBean bean = new CampBean();
			bean.setPk(r.get(V_CAMP.PK));
			bean.setName(r.get(V_CAMP.NAME));
			Double year = r.get(V_CAMP.YEAR);
			if (year != null) {
				bean.setYear(year.intValue());
			}
			list.add(bean);
		}
		return list;
	}
}
