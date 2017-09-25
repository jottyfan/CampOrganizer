package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSON;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;

import java.sql.Date;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.InsertValuesStep1;
import org.jooq.InsertValuesStep11;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.db.converter.EnumConverter;
import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersonRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.modules.book.BookBean;

/**
 * 
 * @author jotty
 *
 */
public class BookGateway extends JooqGateway {

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
	public void insert(BookBean bean) throws DataAccessException {
		getJooq().transaction(t -> {
			Date birthDate = bean.getBirthdate() == null ? null : new Date(bean.getBirthdate().getTime());
			InsertValuesStep11<TPersonRecord, String, String, String, String, String, String, Date, EnumCamprole, String, Integer, Integer> sql = DSL
					.using(t)
					// @formatter:off
			  .insertInto(T_PERSON,
			  		        T_PERSON.FORENAME,
			  		        T_PERSON.SURNAME,
			  		        T_PERSON.STREET,
			  		        T_PERSON.ZIP,
			  		        T_PERSON.CITY,
			  		        T_PERSON.PHONE,
			  		        T_PERSON.BIRTHDATE,
			  		        T_PERSON.CAMPROLE,
			  		        T_PERSON.EMAIL,
			  		        T_PERSON.FK_CAMP,
			  		        T_PERSON.FK_PROFILE)
			  .values(bean.getForename(), bean.getSurname(), bean.getStreet(), bean.getZip(), bean.getCity(),bean.getPhone(), birthDate, new EnumConverter().getEnumCamprole(bean.getCamprole()), bean.getEmail(), bean.getFkCamp(), bean.getFkProfile());
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			sql.execute();

			StringBuilder buf = new StringBuilder("Eine neue Anmeldung von ");
			buf.append(bean.getForename()).append(" ").append(bean.getSurname());
			buf.append(" zur Freizeit ").append(bean.getFkCamp()).append(" ist soeben eingetroffen.");

			InsertValuesStep1<TRssRecord, String> sql2 = DSL.using(t)
			// @formatter:off
				.insertInto(T_RSS,
						        T_RSS.MSG)
				.values(buf.toString());
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
		});
	}
}
