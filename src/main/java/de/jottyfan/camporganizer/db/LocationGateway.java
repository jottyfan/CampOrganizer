package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_CAMP;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_LOCATION;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertValuesStep3;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.LambdaResultWrapper;
import de.jottyfan.camporganizer.admin.LocationBean;
import de.jottyfan.camporganizer.db.jooq.tables.records.TCampRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TLocationRecord;

/**
 * 
 * @author jotty
 *
 */
public class LocationGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(LocationGateway.class);

	public LocationGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * delete entry from t_location with pk = ?; also remove references before
	 * 
	 * @param pk
	 *          reference of dataset
	 * @return number of affected rows
	 */
	public Integer deleteLocation(Integer pk) {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			Integer affectedLines = 0;

			UpdateConditionStep<TCampRecord> sql = getJooq()
			// @formatter:off
			  .update(T_CAMP)
			  .set(T_CAMP.FK_LOCATION, (Integer) null)
			  .where(T_CAMP.FK_LOCATION.eq(pk));
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			affectedLines += sql.execute();

			DeleteConditionStep<TLocationRecord> sql2 = getJooq()
			// @formatter:off
				.deleteFrom(T_LOCATION)
				.where(T_LOCATION.PK.eq(pk));
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			affectedLines += sql2.execute();

			lrw.setNumber(affectedLines);
		});
		return lrw.getNumber();
	}

	/**
	 * upsert t_location with bean
	 * 
	 * @param location
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer upsert(LocationBean bean) throws DataAccessException {
		if (bean.getPk() != null) {
			UpdateConditionStep<TLocationRecord> sql = getJooq()
			// @formatter:off
				.update(T_LOCATION)
				.set(T_LOCATION.NAME, bean.getName())
				.set(T_LOCATION.URL, bean.getUrl())
				.set(T_LOCATION.FK_DOCUMENT, bean.getFkDocument())
				.where(T_LOCATION.PK.eq(bean.getPk()));
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			return sql.execute();
		} else {
			InsertValuesStep3<TLocationRecord, String, String, Integer> sql = getJooq()
			// @formatter:off
			  .insertInto(T_LOCATION,
			  		        T_LOCATION.NAME,
			  		        T_LOCATION.URL,
			  		        T_LOCATION.FK_DOCUMENT)
			  .values(bean.getName(), bean.getUrl(), bean.getFkDocument());
	  	// @formatter:on
			LOGGER.debug("{}", sql.toString());
			return sql.execute();
		}
	}

}
