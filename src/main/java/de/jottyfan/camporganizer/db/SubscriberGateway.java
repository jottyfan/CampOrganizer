package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.*;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertValuesStep1;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Record5;
import org.jooq.Record6;
import org.jooq.Record7;
import org.jooq.Record8;
import org.jooq.Record9;
import org.jooq.SelectConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.CampBean;
import de.jottyfan.camporganizer.LambdaResultWrapper;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.modules.admin.DocumentBean;
import de.jottyfan.camporganizer.modules.subscriber.SubscriberBean;
import de.jottyfan.camporganizer.profile.ProfileBean;

/**
 * 
 * @author jotty
 *
 */
public class SubscriberGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(BookGateway.class);

	private FacesContext facesContext;

	public SubscriberGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
		this.facesContext = facesContext;
	}

	/**
	 * get all camps the user is registered for
	 * 
	 * @param pk
	 *          of user
	 * 
	 * @return found camps
	 * @throws DataAccessException
	 */
	public List<SubscriberBean> getCamps(Integer pk) throws DataAccessException {
		SelectConditionStep<Record11<Integer, String, String, Integer, String, Timestamp, Timestamp, Integer, String, String, Boolean>> sql = getJooq()
		// @formatter:off
		  .select(T_PERSON.PK,
		  		      T_PERSON.FORENAME,
		  		      T_PERSON.SURNAME,
		  		      T_CAMP.FK_DOCUMENT,
		  				  T_CAMP.NAME,
		  		      T_CAMP.ARRIVE,
		  		      T_CAMP.DEPART,
		  		      T_LOCATION.FK_DOCUMENT,
		  		      T_LOCATION.NAME,
		  		      T_LOCATION.URL,
		  		      T_PERSON.ACCEPT)
		  .from(T_PERSON)
		  .leftJoin(T_CAMP).on(T_CAMP.PK.eq(T_PERSON.FK_CAMP))
		  .leftJoin(T_LOCATION).on(T_LOCATION.PK.eq(T_CAMP.FK_LOCATION))
		  .where(T_PERSON.FK_PROFILE.eq(pk));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<SubscriberBean> list = new ArrayList<>();
		for (Record r : sql.fetch()) {
			StringBuilder fullname = new StringBuilder();
			fullname.append(r.get(T_PERSON.FORENAME));
			fullname.append(" ");
			fullname.append(r.get(T_PERSON.SURNAME));
			SubscriberBean bean = new SubscriberBean(r.get(T_PERSON.PK), fullname.toString());
			bean.setArrive(r.get(T_CAMP.ARRIVE));
			bean.setDepart(r.get(T_CAMP.DEPART));
			bean.setCampname(r.get(T_CAMP.NAME));
			bean.setLocationname(r.get(T_LOCATION.NAME));
			bean.setAccept(r.get(T_PERSON.ACCEPT));
			bean.setUrl(r.get(T_LOCATION.URL));
			bean.setDocuments(new ArrayList<>());

			Integer locationDoc = r.get(T_LOCATION.FK_DOCUMENT);
			Integer campDoc = r.get(T_CAMP.FK_DOCUMENT);

			SelectConditionStep<Record4<String, EnumDocument, EnumFiletype, String>> sql2 = getJooq()
			// @formatter:off
				.select(T_DOCUMENT.NAME,
						    T_DOCUMENT.DOCTYPE,
						    T_DOCUMENT.FILETYPE,
						    T_DOCUMENT.DOCUMENT)
				.from(T_DOCUMENT)
				.where(T_DOCUMENT.PK.in(locationDoc, campDoc)
				.or(T_DOCUMENT.DOCTYPE.eq(EnumDocument.camppass))
				);
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			for (Record rec : sql2.fetch()) {
				DocumentBean doc = new DocumentBean(null);
				doc.setDoctype(rec.get(T_DOCUMENT.DOCTYPE));
				doc.setDocument(rec.get(T_DOCUMENT.DOCUMENT));
				doc.setFiletype(rec.get(T_DOCUMENT.FILETYPE));
				doc.setName(rec.get(T_DOCUMENT.NAME));
				bean.getDocuments().add(doc);
			}

			list.add(bean);
		}
		return list;
	}

	/**
	 * delete entry from t_person
	 * 
	 * @param profileBean
	 *          of current user
	 * @param bean
	 *          to be used as reference
	 * @return number of affected rows
	 * @throws DataAccessException
	 */
	public Integer delete(ProfileBean profileBean, SubscriberBean bean) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			DeleteConditionStep<TPersonRecord> sql = DSL.using(t)
			// @formatter:off
				.deleteFrom(T_PERSON)
				.where(T_PERSON.PK.eq(bean.getPkPerson()));
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			lrw.setNumber(sql.execute());

			InsertValuesStep1<TRssRecord, String> sql2 = DSL.using(t)
			// @formatter:off
				.insertInto(T_RSS,
                    T_RSS.MSG)
				.values(profileBean.getFullname() + " hat ihre/seine Buchung an " + bean.getCampname() + " storniert.");
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
		});
		return lrw.getNumber();
	}
}
