package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_CAMPPROFILE;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSONDOCUMENT;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PROFILE;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_CAMP;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertValuesStep2;
import org.jooq.Record;
import org.jooq.Record10;
import org.jooq.Record11;
import org.jooq.Record22;
import org.jooq.Record4;
import org.jooq.SelectConditionStep;
import org.jooq.SelectHavingStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.LambdaResultWrapper;
import de.jottyfan.camporganizer.db.converter.EnumConverter;
import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.db.jooq.enums.EnumModule;
import de.jottyfan.camporganizer.db.jooq.enums.EnumSex;
import de.jottyfan.camporganizer.db.jooq.tables.TProfile;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersondocumentRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.modules.book.PersonBean;
import de.jottyfan.camporganizer.modules.registrator.RegistratorBean;
import de.jottyfan.camporganizer.modules.subscriber.PersondocumentBean;
import de.jottyfan.camporganizer.profile.ProfileBean;

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

	/**
	 * load all registrations from the db restricted by the user's privileges from t_campprofile
	 * 
	 * @param usnr the usnr to filter for
	 * @return the list of registrations, an empty one at least
	 * @throws DataAccessException on database errors
	 */
	public List<RegistratorBean> loadUsers(Integer usnr) throws DataAccessException {
		TProfile subscriber = T_PROFILE.as("s");
		TProfile registrator = T_PROFILE.as("r");
		
		SelectConditionStep<Record> sql = getJooq()
		// @formatter:off
			.select(T_PERSON.PK,
							T_PERSON.FORENAME,
					    T_PERSON.SURNAME,
					    T_PERSON.STREET,
					    T_PERSON.ZIP,
					    T_PERSON.CITY,
					    T_PERSON.PHONE,
					    T_PERSON.BIRTHDATE,
					    T_PERSON.SEX,
					    T_PERSON.EMAIL,
					    T_PERSON.CAMPROLE,
					    T_PERSON.ACCEPT,
					    T_PERSON.CREATED,
					    V_CAMP.NAME,
					    V_CAMP.MIN_AGE,
					    V_CAMP.MAX_AGE,
					    V_CAMP.ARRIVE,
					    V_CAMP.DEPART,
					    V_CAMP.YEAR,
					    V_CAMP.LOCATION_NAME,
					    subscriber.FORENAME,
					    subscriber.SURNAME,
					    subscriber.UUID,
					    registrator.FORENAME,
					    registrator.SURNAME)
			.from(T_PERSON)
			.leftJoin(V_CAMP).on(V_CAMP.PK.eq(T_PERSON.FK_CAMP))
			.leftJoin(subscriber).on(subscriber.PK.eq(T_PERSON.FK_PROFILE))
			.leftJoin(registrator).on(registrator.PK.eq(T_PERSON.FK_REGISTRATOR))
			.leftJoin(T_CAMPPROFILE).on(T_CAMPPROFILE.FK_CAMP.eq(T_PERSON.FK_CAMP))
			.where(T_CAMPPROFILE.FK_PROFILE.eq(usnr))
			.and(T_CAMPPROFILE.MODULE.eq(EnumModule.registration));
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
			bean.setSex(r.get(T_PERSON.SEX));
			bean.setEmail(r.get(T_PERSON.EMAIL));
			bean.setCreated(r.get(T_PERSON.CREATED));
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
			bean.setProfileForename(r.get(subscriber.FORENAME));
			bean.setProfileSurname(r.get(subscriber.SURNAME));
			bean.setProfileUUID(r.get(subscriber.UUID));
			bean.setRegistratorForename(r.get(registrator.FORENAME));
			bean.setRegistratorSurname(r.get(registrator.SURNAME));
			bean.getDocuments().addAll(getAllPersondocumentsOf(bean.getPk()));
			list.add(bean);
		}
		return list;
	}

	/**
	 * get all person documents of person with pk
	 * 
	 * @param pk
	 *          to be used as reference
	 * @return list of found person documents; empty list at least
	 * @throws DataAccessException
	 */
	private List<PersondocumentBean> getAllPersondocumentsOf(Integer pk) throws DataAccessException {
		SelectConditionStep<Record4<Integer, String, EnumFiletype, String>> sql = getJooq()
		// @formatter:off
			.select(T_PERSONDOCUMENT.PK,
					    T_PERSONDOCUMENT.NAME,
					    T_PERSONDOCUMENT.FILETYPE,
					    T_PERSONDOCUMENT.DOCUMENT)
			.from(T_PERSONDOCUMENT)
			.where(T_PERSONDOCUMENT.FK_PERSON.eq(pk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<PersondocumentBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			PersondocumentBean bean = new PersondocumentBean(r.get(T_PERSONDOCUMENT.PK));
			bean.setFkPerson(pk);
			bean.setName(r.get(T_PERSONDOCUMENT.NAME));
			bean.setFiletype(r.get(T_PERSONDOCUMENT.FILETYPE));
			bean.setDocument(r.get(T_PERSONDOCUMENT.DOCUMENT));
			list.add(bean);
		}
		return list;
	}

	/**
	 * set accept flag to true for person referenced by pk
	 * 
	 * @param bean
	 *          containing registration information
	 * @param profile
	 *          the profile bean of the current user
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer acceptUser(RegistratorBean bean, ProfileBean profile) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			UpdateConditionStep<TPersonRecord> sql = getJooq()
			// @formatter:off
				.update(T_PERSON)
				.set(T_PERSON.ACCEPT, true)
				.set(T_PERSON.FK_REGISTRATOR, profile.getPk())
				.where(T_PERSON.PK.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			lrw.setNumber(sql.execute());

			StringBuilder buf = new StringBuilder("Die Anmeldung von ");
			buf.append(bean.getForename());
			buf.append(" ");
			buf.append(bean.getSurname());
			buf.append(" zur Freizeit ");
			buf.append(bean.getCampname());
			buf.append(" wurde bestätigt. Melde Dich jetzt unter https://onkelwernerfreizeiten.de/camporganizer an,");
			buf.append(" um die Bestätigungen herunterzuladen.");

			InsertValuesStep2<TRssRecord, String, String> sql2 = getJooq()
			// @formatter:off
				.insertInto(T_RSS,
						        T_RSS.MSG,
						        T_RSS.RECIPIENT)
				.values(buf.toString(), bean.getProfileUUID());
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
		});
		return lrw.getNumber();
	}

	/**
	 * set accept flag to false in db
	 * 
	 * @param bean
	 *          to be used
	 * @param profile
	 *          the profile bean of the current user
	 * @throws DataAccessException
	 */
	public void rejectUser(RegistratorBean bean, ProfileBean profile) throws DataAccessException {
		getJooq().transaction(t -> {
			UpdateConditionStep<TPersonRecord> sql = getJooq()
			// @formatter:off
				.update(T_PERSON)
				.set(T_PERSON.ACCEPT, false)
				.set(T_PERSON.FK_REGISTRATOR, profile.getPk())
				.where(T_PERSON.PK.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			sql.execute();

			StringBuilder buf = new StringBuilder("Die Anmeldung von ");
			buf.append(bean.getForename());
			buf.append(" ");
			buf.append(bean.getSurname());
			buf.append(" zur Freizeit ");
			buf.append(bean.getCampname());
			buf.append(" wurde leider abgelehnt.");
			buf.append(" Möglicherweise ist sie schon ausgebucht? Deine Anmeldung befindet sich jetzt auf der Warteliste.");

			InsertValuesStep2<TRssRecord, String, String> sql2 = getJooq()
			// @formatter:off
				.insertInto(T_RSS,
						        T_RSS.MSG,
						        T_RSS.RECIPIENT)
				.values(buf.toString(), bean.getProfileUUID());
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
		});
	}

	/**
	 * remove entry in person table from db referenced by pk
	 * 
	 * @param pk
	 *          of t_person
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer deleteUser(Integer pk) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			DeleteConditionStep<TPersondocumentRecord> sql = DSL.using(t)
			// @formatter:off
			  .deleteFrom(T_PERSONDOCUMENT)
			  .where(T_PERSONDOCUMENT.FK_PERSON.eq(pk));
			// @formatter:off
			LOGGER.debug("{}", sql.toString());
			Integer affected = sql.execute();
			
			DeleteConditionStep<TPersonRecord> sql2 = DSL.using(t)
		  // @formatter:off
			  .deleteFrom(T_PERSON)
			  .where(T_PERSON.PK.eq(pk));
		  // @formatter:off
		  LOGGER.debug("{}", sql2.toString());
		  affected += sql2.execute();
		  lrw.setNumber(affected);
		});
		return lrw.getNumber();
	}

	/**
	 * update registration
	 * 
	 * @param bean to be used for update
	 * @param profile
	 *          the profile bean of the current user
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer update(RegistratorBean bean, ProfileBean profile) throws DataAccessException {
		Date birthDate = bean.getBirthdate() == null ? null : new Date(bean.getBirthdate().getTime());
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t->{
			UpdateConditionStep<TPersonRecord> sql = getJooq()
			// @formatter:off
				.update(T_PERSON)
				.set(T_PERSON.FORENAME, bean.getForename())
				.set(T_PERSON.SURNAME, bean.getSurname())
				.set(T_PERSON.STREET, bean.getStreet())
				.set(T_PERSON.ZIP, bean.getZip())
				.set(T_PERSON.CITY, bean.getCity())
				.set(T_PERSON.BIRTHDATE, birthDate)
				.set(T_PERSON.SEX, bean.getSex())
				.set(T_PERSON.PHONE, bean.getPhone())
				.set(T_PERSON.EMAIL, bean.getEmail())
				.where(T_PERSON.PK.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			lrw.setNumber(sql.execute());

			StringBuilder buf = new StringBuilder("Eine Anmeldung für ").append(bean.getCampname());
			buf.append(" wurde von ").append(profile.getFullname());
			buf.append(" korrigiert.");

			InsertValuesStep2<TRssRecord, String, String> sql2 = getJooq()
			// @formatter:off
		    .insertInto(T_RSS,
				            T_RSS.MSG,
				            T_RSS.RECIPIENT)
		    .values(buf.toString(), "registrator");
		  // @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
		});
		return lrw.getNumber();
	}

	/**
	 * load all entries with campPk from t_person
	 * 
	 * @param campPk
	 * @return list of all registrations
	 * @throws DataAccessException
	 */
	public List<PersonBean> getAllPersonsOfCamp(Integer campPk) throws DataAccessException {
		SelectConditionStep<Record11<Integer, String, String, String, String, String, String, String, Date, EnumSex, EnumCamprole>> sql = getJooq()
		// @formatter:off
			.select(T_PERSON.PK,
					    T_PERSON.FORENAME,
					    T_PERSON.SURNAME,
					    T_PERSON.STREET,
					    T_PERSON.ZIP,
					    T_PERSON.CITY,
					    T_PERSON.PHONE,
					    T_PERSON.EMAIL,
					    T_PERSON.BIRTHDATE,
					    T_PERSON.SEX,
					    T_PERSON.CAMPROLE)
			.from(T_PERSON)
			.where(T_PERSON.FK_CAMP.eq(campPk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<PersonBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			PersonBean bean = new PersonBean();
			bean.setPk(r.get(T_PERSON.PK));
			bean.setForename(r.get(T_PERSON.FORENAME));
			bean.setSurname(r.get(T_PERSON.SURNAME));
			bean.setStreet(r.get(T_PERSON.STREET));
			bean.setZip(r.get(T_PERSON.ZIP));
			bean.setCity(r.get(T_PERSON.CITY));
			bean.setPhone(r.get(T_PERSON.PHONE));
			bean.setEmail(r.get(T_PERSON.EMAIL));
			bean.setBirthdate(r.get(T_PERSON.BIRTHDATE));
			bean.setSex(new EnumConverter().getSexGerman(r.get(T_PERSON.SEX)));
			bean.setCamprole(new EnumConverter().getCamproleGerman(r.get(T_PERSON.CAMPROLE)));
			list.add(bean);
		}
		return list;
	}

	/**
	 * get all booking data of a login
	 * 
	 * @param pk
	 *          of the user that is logged in
	 * @return list of found bookings
	 * @throws DataAccessException
	 */
	public List<PersonBean> getPersons(Integer pk) throws DataAccessException {
		SelectHavingStep<Record11<Integer, String, String, String, String, String, String, String, Date, EnumSex, EnumCamprole>> sql = getJooq()
		// @formatter:off
			.selectDistinct(T_PERSON.PK,
					            T_PERSON.FORENAME,
					            T_PERSON.SURNAME,
					            T_PERSON.STREET,
					            T_PERSON.ZIP,
					            T_PERSON.CITY,
					            T_PERSON.PHONE,
					            T_PERSON.EMAIL,
					            T_PERSON.BIRTHDATE,
					            T_PERSON.SEX,
					            T_PERSON.CAMPROLE)
			.from(T_PERSON)
			.where(T_PERSON.FK_PROFILE.eq(pk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<PersonBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			PersonBean bean = new PersonBean();
			bean.setPk(r.get(T_PERSON.PK));
			bean.setForename(r.get(T_PERSON.FORENAME));
			bean.setSurname(r.get(T_PERSON.SURNAME));
			bean.setStreet(r.get(T_PERSON.STREET));
			bean.setZip(r.get(T_PERSON.ZIP));
			bean.setCity(r.get(T_PERSON.CITY));
			bean.setPhone(r.get(T_PERSON.PHONE));
			bean.setEmail(r.get(T_PERSON.EMAIL));
			bean.setBirthdate(r.get(T_PERSON.BIRTHDATE));
			EnumSex sex = r.get(T_PERSON.SEX);
			EnumCamprole camprole = r.get(T_PERSON.CAMPROLE);
			bean.setSex(sex == null ? null : sex.getLiteral());
			bean.setCamprole(camprole == null ? null : camprole.getLiteral());
			list.add(bean);
		}
		return list;
	}
}
