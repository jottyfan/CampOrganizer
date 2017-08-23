package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_CAMP;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.CampBean;

/**
 * 
 * @author jotty
 *
 */
public class SubscriberGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(BookGateway.class);

	public SubscriberGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * get all camps the user is registered for
	 * 
	 * @param pk
	 *          of user
	 * 
	 * @return found camps
	 */
	public List<CampBean> getCamps(Integer pk) {
		SelectConditionStep<Record1<String>> sql = getJooq()
		// @formatter:off
		  .select(T_CAMP.NAME)
		  .from(T_PERSON)
		  .leftJoin(T_CAMP).on(T_CAMP.PK.eq(T_PERSON.FK_CAMP))
		  .where(T_PERSON.FK_PROFILE.eq(pk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<CampBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			CampBean bean = new CampBean();
			bean.setName(r.get(T_CAMP.NAME));
			list.add(bean);
		}
		return list;
	}

}
