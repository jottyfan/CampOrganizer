package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_CAMP;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record;
import org.jooq.Record15;
import org.jooq.Record16;
import org.jooq.SelectOnConditionStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.registrator.RegistratorBean;

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
		SelectOnConditionStep<Record16<Integer, String, String, String, String, String, String, Date, String, EnumCamprole, Boolean, String, Integer, Integer, Timestamp, Timestamp>> sql = getJooq()
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
					    V_CAMP.DEPART)
			.from(T_PERSON)
			.leftJoin(V_CAMP).on(V_CAMP.PK.eq(T_PERSON.FK_CAMP));
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
			bean.setCampname(r.get(V_CAMP.NAME));
			bean.setMinAge(r.get(V_CAMP.MIN_AGE));
			bean.setMaxAge(r.get(V_CAMP.MAX_AGE));
			bean.setArrive(r.get(V_CAMP.ARRIVE));
			bean.setDepart(r.get(V_CAMP.DEPART));
			bean.setAccept(r.get(T_PERSON.ACCEPT));
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
}
