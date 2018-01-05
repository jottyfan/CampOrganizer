package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_CAMP;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_CAMP;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertValuesStep9;
import org.jooq.Record11;
import org.jooq.Record12;
import org.jooq.SelectHavingStep;
import org.jooq.SelectSeekStep1;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.LambdaResultWrapper;
import de.jottyfan.camporganizer.db.jooq.tables.records.TCampRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;

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
	public List<CampBean> getAllCampsFromView(boolean futureOnly) throws DataAccessException {
		Timestamp limitDate = new Timestamp(0);
		if (futureOnly) {
			limitDate.setTime(new Date().getTime());
		}
		SelectSeekStep1<Record12<Integer, String, String, Double, Integer, Integer, String, Timestamp, Timestamp, String, String, Integer>, Timestamp> sql = getJooq()
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
					    V_CAMP.COUNTRIES,
					    V_CAMP.FK_DOCUMENT)
			.from(V_CAMP)
			.where(V_CAMP.ARRIVE.greaterThan(limitDate))
			.orderBy(V_CAMP.ARRIVE);
	  // @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<CampBean> list = new ArrayList<>();
		for (Record12<Integer, String, String, Double, Integer, Integer, String, Timestamp, Timestamp, String, String, Integer> r : sql
				.fetch()) {
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
			bean.setFkDocument(r.get(V_CAMP.FK_DOCUMENT));
			list.add(bean);
		}
		return list;
	}

	/**
	 * delete camp with pk from database
	 * 
	 * @param facesContext
	 *          current context for messages
	 * @param pk
	 *          as reference
	 * @return number of affected lines
	 * @throws DataAccessException
	 */
	public Integer deleteCamp(FacesContext facesContext, Integer pk) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			DeleteConditionStep<TPersonRecord> sql = getJooq()
			// @formatter:off
		  	  .deleteFrom(T_PERSON)
		  	  .where(T_PERSON.FK_CAMP.eq(pk));
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			Integer lostRegistrations = sql.execute();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "lost registrations", "Es wurden mit der Freizeit auch " + lostRegistrations + " Anmeldungen gelöscht."));
			DeleteConditionStep<TCampRecord> sql2 = getJooq()
			// @formatter:off
				.deleteFrom(T_CAMP)
				.where(T_CAMP.PK.eq(pk));
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			lrw.setNumber(sql2.execute());
		});
		return lrw.getNumber();
	}

	/**
	 * upsert bean referenced by camp.pk
	 * 
	 * @param bean
	 *          to be upserted
	 * @return number of affected lines
	 * @throws DataAccessException
	 */
	public Integer upsert(CampBean bean) throws DataAccessException {
		Timestamp arrive = bean.getArrive() == null ? null : new Timestamp(bean.getArrive().getTime());
		Timestamp depart = bean.getDepart() == null ? null : new Timestamp(bean.getDepart().getTime());
		if (bean.getPk() == null) {
			InsertValuesStep9<TCampRecord, String, Timestamp, Timestamp, Integer, Integer, Integer, String, String, Integer> sql = getJooq()
			// @formatter:off
		    	.insertInto(T_CAMP,
		    			        T_CAMP.NAME,
		    			        T_CAMP.ARRIVE,
		    			        T_CAMP.DEPART,
		    			        T_CAMP.FK_LOCATION,
		    			        T_CAMP.MIN_AGE,
		    			        T_CAMP.MAX_AGE,
		    			        T_CAMP.PRICE,
		    			        T_CAMP.COUNTRIES,
		    			        T_CAMP.FK_DOCUMENT)
		    	.values(bean.getName(), arrive, depart, bean.getFkLocation(), bean.getMinAge(), bean.getMaxAge(), bean.getPrice(), bean.getCountries(),bean.getFkDocument());
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			return sql.execute();
		} else {
			UpdateConditionStep<TCampRecord> sql = getJooq()
			// @formatter:off
				.update(T_CAMP)
				.set(T_CAMP.NAME, bean.getName())
				.set(T_CAMP.ARRIVE, arrive)
				.set(T_CAMP.DEPART, depart)
				.set(T_CAMP.FK_LOCATION, bean.getFkLocation())
				.set(T_CAMP.MIN_AGE, bean.getMinAge())
				.set(T_CAMP.MAX_AGE, bean.getMaxAge())
				.set(T_CAMP.PRICE, bean.getPrice())
				.set(T_CAMP.COUNTRIES, bean.getCountries())
				.set(T_CAMP.FK_DOCUMENT, bean.getFkDocument())
				.where(T_CAMP.PK.eq(bean.getPk()));
			// @formatter:off
			LOGGER.debug("{}", sql.toString());
			return sql.execute();
		}
	}

	/**
	 * get all camps from t_camp
	 * 
	 * @return found camps
	 * @throws DataAccessException
	 */
	public List<CampBean> getAllCampsFromTable() throws DataAccessException {
		StringBuilder buf = new StringBuilder();
		buf.append("\"");
		buf.append(T_PERSON.getSchema().getName());
		buf.append("\".\"");
		buf.append(T_PERSON.getName());
		buf.append("\".\"");
		buf.append(T_PERSON.PK.getName());
		buf.append("\"");
		
		String cnt = buf.toString();
		SelectHavingStep<Record11<Integer, String, Timestamp, Timestamp, Integer, Integer, Integer, String, String, Integer, Integer>> sql = getJooq()
		// @formatter:off
			.select(T_CAMP.PK,
					    T_CAMP.NAME,
			        T_CAMP.ARRIVE,
			        T_CAMP.DEPART,
			        T_CAMP.FK_LOCATION,
			        T_CAMP.MIN_AGE,
			        T_CAMP.MAX_AGE,
			        T_CAMP.PRICE,
			        T_CAMP.COUNTRIES,
			        T_CAMP.FK_DOCUMENT,
			        DSL.field("count(" + cnt + ")", Integer.class).as("bookings")
			        )
			.from(T_CAMP)
			.leftJoin(T_PERSON).on(T_PERSON.FK_CAMP.eq(T_CAMP.PK))
			.groupBy(T_CAMP.PK,
					    T_CAMP.NAME,
			        T_CAMP.ARRIVE,
			        T_CAMP.DEPART,
			        T_CAMP.FK_LOCATION,
			        T_CAMP.MIN_AGE,
			        T_CAMP.MAX_AGE,
			        T_CAMP.PRICE,
			        T_CAMP.COUNTRIES,
			        T_CAMP.FK_DOCUMENT);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<CampBean> list = new ArrayList<>();
		for (Record11<Integer, String, Timestamp, Timestamp, Integer, Integer, Integer, String, String, Integer, Integer> r : sql
				.fetch()) {
			CampBean bean = new CampBean();
			bean.setPk(r.get(T_CAMP.PK));
			bean.setName(r.get(T_CAMP.NAME));
			bean.setArrive(r.get(T_CAMP.ARRIVE));
			bean.setDepart(r.get(T_CAMP.DEPART));
			bean.setFkLocation(r.get(T_CAMP.FK_LOCATION));
			bean.setMinAge(r.get(T_CAMP.MIN_AGE));
			bean.setMaxAge(r.get(T_CAMP.MAX_AGE));
			bean.setPrice(r.get(T_CAMP.PRICE));
			bean.setCountries(r.get(T_CAMP.COUNTRIES));
			bean.setFkDocument(r.get(T_CAMP.FK_DOCUMENT));
			bean.setBookings(r.get("bookings", Integer.class));
			list.add(bean);
		}
		return list;
	}
}
