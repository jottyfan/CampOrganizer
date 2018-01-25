package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;

import java.sql.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.InsertReturningStep;
import org.jooq.InsertValuesStep2;
import org.jooq.Record;
import org.jooq.Record9;
import org.jooq.SelectConditionStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.db.converter.EnumConverter;
import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.modules.book.PersonBean;
import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
public class BookGateway extends ProfileGateway {

	private static final Logger LOGGER = LogManager.getLogger(BookGateway.class);

	public BookGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * add bean to db (also register a new entry in t_rss)
	 * 
	 * @param bean
	 *          to be stored
	 * @throws DataAccessException
	 */
	public void upsert(DSLContext jooq, PersonBean bean) throws DataAccessException {
		Date birthDate = bean.getBirthdate() == null ? null : new Date(bean.getBirthdate().getTime());
		InsertReturningStep<TPersonRecord> sql = jooq
		// @formatter:off
			  .insertInto(T_PERSON,
			  		        T_PERSON.FORENAME,
			  		        T_PERSON.SURNAME,
			  		        T_PERSON.STREET,
			  		        T_PERSON.ZIP,
			  		        T_PERSON.CITY,
			  		        T_PERSON.PHONE,
			  		        T_PERSON.BIRTHDATE,
			  		        T_PERSON.SEX,
			  		        T_PERSON.CAMPROLE,
			  		        T_PERSON.EMAIL,
			  		        T_PERSON.FK_CAMP,
			  		        T_PERSON.FK_PROFILE)
			  .values(bean.getForename(), bean.getSurname(), bean.getStreet(), bean.getZip(), bean.getCity(), bean.getPhone(), birthDate, 
			  		      bean.getSexEnum(), bean.getCamproleEnum(), bean.getEmail(), bean.getFkCamp(), bean.getFkProfile())
			  .onConflict(T_PERSON.FORENAME, T_PERSON.SURNAME, T_PERSON.BIRTHDATE, T_PERSON.FK_CAMP)
			  .doNothing();
			// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();

		StringBuilder buf = new StringBuilder("Eine neue Anmeldung von ");
		buf.append(bean.getForename()).append(" ").append(bean.getSurname());
		buf.append(" zur Freizeit ").append(bean.getFkCamp()).append(" ist soeben eingetroffen.");

		InsertValuesStep2<TRssRecord, String, String> sql2 = jooq
		// @formatter:off
				.insertInto(T_RSS,
						        T_RSS.MSG,
						        T_RSS.RECIPIENT)
				.values(buf.toString(), "registrator");
			// @formatter:on
		LOGGER.debug("{}", sql2.toString());
		sql2.execute();
	}

	/**
	 * load person entry from db
	 * 
	 * @param pk
	 *          to be used as reference
	 * @return loaded bean
	 * @throws DataAccessException
	 */
	public PersonBean getPerson(Integer pk) throws DataAccessException {
		SelectConditionStep<Record9<String, String, String, String, String, Date, String, String, EnumCamprole>> sql = getJooq()
		// @formatter:off
			.select(T_PERSON.FORENAME,
					    T_PERSON.SURNAME,
					    T_PERSON.STREET,
					    T_PERSON.ZIP,
					    T_PERSON.CITY,
					    T_PERSON.BIRTHDATE,
					    T_PERSON.PHONE,
					    T_PERSON.EMAIL,
					    T_PERSON.CAMPROLE)
			.from(T_PERSON)
			.where(T_PERSON.PK.eq(pk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		Record r = sql.fetchOne();
		PersonBean bean = new PersonBean();
		bean.setPk(pk);
		bean.setForename(r.get(T_PERSON.FORENAME));
		bean.setSurname(r.get(T_PERSON.SURNAME));
		bean.setStreet(r.get(T_PERSON.STREET));
		bean.setZip(r.get(T_PERSON.ZIP));
		bean.setCity(r.get(T_PERSON.CITY));
		bean.setBirthdate(r.get(T_PERSON.BIRTHDATE));
		bean.setPhone(r.get(T_PERSON.PHONE));
		bean.setEmail(r.get(T_PERSON.EMAIL));
		bean.setCamprole(r.get(T_PERSON.CAMPROLE).getLiteral());
		return bean;
	}

	/**
	 * update subscriber relevant fields in db
	 * 
	 * @param bean
	 *          to be updated
	 * @throws DataAccessException
	 */
	public void update(PersonBean bean) throws DataAccessException {
		Date birthDate = bean.getBirthdate() == null ? null : new Date(bean.getBirthdate().getTime());
		getJooq().transaction(t -> {
			UpdateConditionStep<TPersonRecord> sql = DSL.using(t)
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
				.set(T_PERSON.CAMPROLE, new EnumConverter().getEnumCamprole(bean.getCamprole()))
				.where(T_PERSON.PK.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			sql.execute();

			InsertValuesStep2<TRssRecord, String, String> sql2 = DSL.using(t)
			// @formatter:off
				.insertInto(T_RSS,
						        T_RSS.MSG,
						        T_RSS.RECIPIENT)
				.values(new StringBuilder("Es gab eine Adressänderung für Freizeit ").append(bean.getFkCamp()).toString(), "registrator");
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
		});
	}

	/**
	 * do the booking db stuff
	 * 
	 * @param profileBean
	 * @param bean
	 * @throws DataAccessException
	 */
	public void doBooking(ProfileBean profileBean, PersonBean bean) throws DataAccessException {
		getJooq().transaction(t -> {
			if (profileBean.getUsername() != null && !profileBean.getUsername().isEmpty()) {
				if (profileBean.getIsEmpty()) {
					profileBean.setForename(bean.getForename());
					profileBean.setSurname(bean.getSurname());
					if (checkUsernameExists(DSL.using(t), profileBean)) {
						throw new DataAccessException("Der Name ist leider schon vergeben, bitte wähle einen anderen.");
					} else if (profileBean.getPassword().equals(profileBean.getPasswordAgain())) {
						Integer pk = register(DSL.using(t), profileBean);
						bean.setFkProfile(pk);
					} else {
						throw new DataAccessException("Die eingegebenen Passwörter sind nicht gleich.");
					}
				} else {
					bean.setFkProfile(profileBean.getPk());
					ensureSubscriberRole(DSL.using(t), profileBean);
				}
			}
			upsert(DSL.using(t), bean);
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "booking finished",
					"Die Anmeldung wurde übernommen. Sobald sie von uns bestätigt wurde, ist Dein Platz auf der Freizeit gesichert."));
			profileBean.clear();
		});
	}
}
