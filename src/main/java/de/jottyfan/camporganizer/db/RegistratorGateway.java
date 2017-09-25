package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.*;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_CAMP;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.Record;
import org.jooq.Record18;
import org.jooq.Record20;
import org.jooq.SelectOnConditionStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.modules.registrator.RegistratorBean;

/**
 * 
 * @author jotty
 *
 */
public class RegistratorGateway extends JooqGateway {
	private static final Logger LOGGER = LogManager.getLogger(RegistratorGateway.class);

	public RegistratorGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	public List<RegistratorBean> loadUsers() throws DataAccessException {
		SelectOnConditionStep<Record20<Integer, String, String, String, String, String, String, Date, String, EnumCamprole, Boolean, String, Integer, Integer, Timestamp, Timestamp, Double, String, String, String>> sql = getJooq()
		// @formatter:off
			.select(T_PERSON.PK,
							T_PERSON.FORENAME,
					    T_PERSON.SURNAME,
					    T_PERSON.STREET,
					    T_PERSON.ZIP,
					    T_PERSON.CITY,
					    T_PERSON.PHONE,
					    T_PERSON.BIRTHDATE,
					    T_PERSON.EMAIL,
					    T_PERSON.CAMPROLE,
					    T_PERSON.ACCEPT,
					    V_CAMP.NAME,
					    V_CAMP.MIN_AGE,
					    V_CAMP.MAX_AGE,
					    V_CAMP.ARRIVE,
					    V_CAMP.DEPART,
					    V_CAMP.YEAR,
					    V_CAMP.LOCATION_NAME,
					    T_PROFILE.FORENAME,
					    T_PROFILE.SURNAME)
			.from(T_PERSON)
			.leftJoin(V_CAMP).on(V_CAMP.PK.eq(T_PERSON.FK_CAMP))
			.leftJoin(T_PROFILE).on(T_PROFILE.PK.eq(T_PERSON.FK_PROFILE));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<RegistratorBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			RegistratorBean bean = new RegistratorBean(r.get(T_PERSON.PK));
			bean.setForename(r.get(T_PERSON.FORENAME));
			bean.setSurname(r.get(T_PERSON.SURNAME));
			bean.setStreet(r.get(T_PERSON.STREET));
			bean.setZip(r.get(T_PERSON.ZIP));
			bean.setCity(r.get(T_PERSON.CITY));
			bean.setPhone(r.get(T_PERSON.PHONE));
			bean.setBirthdate(r.get(T_PERSON.BIRTHDATE));
			bean.setEmail(r.get(T_PERSON.EMAIL));
			bean.setCamprole(r.get(T_PERSON.CAMPROLE));
			StringBuilder campName = new StringBuilder();
			campName.append(r.get(V_CAMP.NAME)).append(" ");
			Double year = r.get(V_CAMP.YEAR);
			campName.append(year == null ? "" : year.intValue()).append(" in ");
			campName.append(r.get(V_CAMP.LOCATION_NAME));
			bean.setCampname(campName.toString());
			bean.setMinAge(r.get(V_CAMP.MIN_AGE));
			bean.setMaxAge(r.get(V_CAMP.MAX_AGE));
			bean.setArrive(r.get(V_CAMP.ARRIVE));
			bean.setDepart(r.get(V_CAMP.DEPART));
			bean.setAccept(r.get(T_PERSON.ACCEPT));
			bean.setProfileForename(r.get(T_PROFILE.FORENAME));
			bean.setProfileSurname(r.get(T_PROFILE.SURNAME));
			list.add(bean);
		}
		return list;
	}

	/**
	 * set accept flag to true for person referenced by pk
	 * 
	 * @param pk
	 *          of t_person
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer acceptUser(Integer pk) throws DataAccessException {
		UpdateConditionStep<TPersonRecord> sql = getJooq()
		// @formatter:off
			.update(T_PERSON)
			.set(T_PERSON.ACCEPT, true)
			.where(T_PERSON.PK.eq(pk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}

	/**
	 * remove entry in person table from db referenced by pk
	 * 
	 * @param pk
	 *          of t_person
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer rejectUser(Integer pk) throws DataAccessException {
		DeleteConditionStep<TPersonRecord> sql = getJooq()
		// @formatter:off
			.deleteFrom(T_PERSON)
			.where(T_PERSON.PK.eq(pk));
		// @formatter:off
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}

	/**
	 * update registration
	 * 
	 * @param bean to be used for update
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer update(RegistratorBean bean) throws DataAccessException {
		Date birthDate = bean.getBirthdate() == null ? null : new Date(bean.getBirthdate().getTime());
		UpdateConditionStep<TPersonRecord> sql = getJooq()
		// @formatter:off
			.update(T_PERSON)
			.set(T_PERSON.FORENAME, bean.getForename())
			.set(T_PERSON.SURNAME, bean.getSurname())
			.set(T_PERSON.STREET, bean.getStreet())
			.set(T_PERSON.ZIP, bean.getZip())
			.set(T_PERSON.CITY, bean.getCity())
			.set(T_PERSON.BIRTHDATE, birthDate)
			.set(T_PERSON.PHONE, bean.getPhone())
			.set(T_PERSON.EMAIL, bean.getEmail())
			.where(T_PERSON.PK.eq(bean.getPk()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}
}
