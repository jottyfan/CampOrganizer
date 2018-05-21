package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertReturningStep;
import org.jooq.InsertValuesStep10;
import org.jooq.InsertValuesStep3;
import org.jooq.InsertValuesStep9;
import org.jooq.Record;
import org.jooq.Record10;
import org.jooq.Record11;
import org.jooq.Record12;
import org.jooq.Record13;
import org.jooq.Record4;
import org.jooq.Record7;
import org.jooq.Record9;
import org.jooq.SelectHavingStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSeekStep1;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.LambdaResultWrapper;
import de.jottyfan.camporganizer.db.converter.EnumConverter;
import de.jottyfan.camporganizer.db.converter.ModuleTranslation;
import de.jottyfan.camporganizer.db.jooq.enums.EnumModule;
import de.jottyfan.camporganizer.db.jooq.tables.records.TCampRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TCampprofileRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.modules.campadmin.CampprofileBean;

/**
 * 
 * @author jotty
 *
 */
public class CampprofileGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(CampprofileGateway.class);

	public CampprofileGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * get all camp profiles for administration
	 * 
	 * @return the list of camp profiles, at least an empty list
	 * @throws DataAccessException
	 *           on db errors
	 */
	public List<CampprofileBean> getAll() throws DataAccessException {
		SelectJoinStep<Record10<Integer, Integer, Integer, EnumModule, String, String, Timestamp, String, String, Double>> sql = getJooq()
		// @formatter:off
			.select(T_CAMPPROFILE.PK,
					    T_CAMPPROFILE.FK_CAMP,
					    T_CAMPPROFILE.FK_PROFILE,
					    T_CAMPPROFILE.MODULE,
					    T_PROFILE.FORENAME,
					    T_PROFILE.SURNAME,
					    V_CAMP.ARRIVE,
					    V_CAMP.NAME,
					    V_CAMP.LOCATION_NAME,
					    V_CAMP.YEAR)
			.from(T_CAMPPROFILE)
			.leftJoin(T_PROFILE).on(T_PROFILE.PK.eq(T_CAMPPROFILE.FK_PROFILE))
			.leftJoin(V_CAMP).on(V_CAMP.PK.eq(T_CAMPPROFILE.FK_CAMP));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<CampprofileBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			CampprofileBean bean = new CampprofileBean();
			bean.setPk(r.get(T_CAMPPROFILE.PK));
			StringBuilder buf = new StringBuilder();
			buf.append(r.get(V_CAMP.NAME)).append(" ");
			Double year = r.get(V_CAMP.YEAR);
			buf.append(year == null ? "" : year.intValue()).append(" in ");
			buf.append(r.get(V_CAMP.LOCATION_NAME));
			bean.setCampname(buf.toString());
			bean.setForename(r.get(T_PROFILE.FORENAME));
			bean.setSurname(r.get(T_PROFILE.SURNAME));
			bean.setModule(new ModuleTranslation().get(r.get(T_CAMPPROFILE.MODULE)));
			bean.setStartdate(r.get(T_CAMP.ARRIVE)); // the camp start date
			list.add(bean);
		}
		return list;
	}

	/**
	 * delete camp profile entry
	 * 
	 * @param pk
	 *          for the reference
	 * @return number of affected database rows, should be 1
	 * @throws DataAccessException
	 *           on sql errors
	 */
	public Integer deleteCampprofile(Integer pk) throws DataAccessException {
		DeleteConditionStep<TCampprofileRecord> sql = getJooq()
		// @formatter:off
			.deleteFrom(T_CAMPPROFILE)
			.where(T_CAMPPROFILE.PK.eq(pk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}

	/**
	 * add a camp profile; if it still exists, ignore it
	 * 
	 * @param bean
	 *          the camp profile
	 * @return number of affected database rows, should be 1
	 * @throws DataAccessException
	 *           on sql errors
	 */
	public Integer add(CampprofileBean bean) throws DataAccessException {
		InsertReturningStep<TCampprofileRecord> sql = getJooq()
		// @formatter:off
			.insertInto(T_CAMPPROFILE,
					        T_CAMPPROFILE.FK_CAMP,
					        T_CAMPPROFILE.FK_PROFILE,
					        T_CAMPPROFILE.MODULE)
			.values(bean.getFkCamp(), bean.getFkProfile(), new EnumConverter().getEnumModule(bean.getModule()))
			.onConflict(T_CAMPPROFILE.FK_CAMP, T_CAMPPROFILE.FK_PROFILE, T_CAMPPROFILE.MODULE)
			.doNothing();
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}
}
