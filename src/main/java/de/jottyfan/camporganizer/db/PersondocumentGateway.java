package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_CAMP;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_DOCUMENT;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_LOCATION;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_PERSONDOCUMENT;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.InsertValuesStep2;
import org.jooq.InsertValuesStep4;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import de.jottyfan.camporganizer.LambdaResultWrapper;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.db.jooq.tables.records.TCampRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TDocumentRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TLocationRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersondocumentRecord;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.modules.admin.DocumentBean;
import de.jottyfan.camporganizer.modules.subscriber.PersondocumentBean;

/**
 * 
 * @author henkej
 *
 */
public class PersondocumentGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(PersondocumentGateway.class);

	public PersondocumentGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * delete entry from t_persondocument where pk = ?
	 * 
	 * @param pk
	 *          to be used as reference
	 * @return number of affected database lines
	 * @throws DataAccessException
	 */
	public Integer deletePersondocument(PersondocumentBean bean) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {

			DeleteConditionStep<TPersondocumentRecord> sql = DSL.using(t)
			// @formatter:off
				.deleteFrom(T_PERSONDOCUMENT)
				.where(T_PERSONDOCUMENT.PK.eq(bean.getPk()));
			// @formatter:on
			LOGGER.debug("{}", sql.toString());
			lrw.setNumber(sql.execute());

			StringBuilder buf = new StringBuilder("Dokument ");
			buf.append(bean.getName());
			buf.append(" wurde wieder gelöscht.");
			InsertValuesStep2<TRssRecord, String, String> sql2 = DSL.using(t)
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
	 * add document to database
	 * 
	 * @param bean
	 * @throws DataAccessException
	 */
	public void addPersondocument(PersondocumentBean bean) throws DataAccessException {
		getJooq().transaction(t -> {

			InsertValuesStep4<TPersondocumentRecord, String, EnumFiletype, Integer, String> sql = DSL.using(t)
		  // @formatter:off
		    	.insertInto(T_PERSONDOCUMENT,
		    			       T_PERSONDOCUMENT.NAME,
		    			       T_PERSONDOCUMENT.FILETYPE,
		    			       T_PERSONDOCUMENT.FK_PERSON,
		    			       T_PERSONDOCUMENT.DOCUMENT
		    			       )
		    	.values(bean.getName(), bean.getFiletype(), bean.getFkPerson(), bean.getDocument());
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			sql.execute();

			StringBuilder buf = new StringBuilder("Dokument ");
			buf.append(bean.getName());
			buf.append(" wurde angelegt.");
			InsertValuesStep2<TRssRecord, String, String> sql2 = DSL.using(t)
		  // @formatter:off
	      .insertInto(T_RSS,
	      		         T_RSS.MSG,
	      		         T_RSS.RECIPIENT)
	      .values(buf.toString(), "registrator");
	    // @formatter:on
			LOGGER.debug("{}", sql2.toString());
			sql2.execute();
		});
	}
}
