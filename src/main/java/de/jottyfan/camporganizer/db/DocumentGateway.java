package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_CAMP;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_DOCUMENT;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_LOCATION;
import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;

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
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.modules.campadmin.DocumentBean;

/**
 * 
 * @author henkej
 *
 */
public class DocumentGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(DocumentGateway.class);

	public DocumentGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * delete entry from t_document where pk = ?
	 * 
	 * @param pk
	 *          to be used as reference
	 * @return number of affected database lines
	 * @throws DataAccessException
	 */
	public Integer deleteDocument(Integer pk) throws DataAccessException {
		LambdaResultWrapper lrw = new LambdaResultWrapper();
		getJooq().transaction(t -> {
			Integer affectedLines = 0;

			UpdateConditionStep<TCampRecord> sql = DSL.using(t)
			// @formatter:off
			  .update(T_CAMP)
			  .set(T_CAMP.FK_DOCUMENT, (Integer) null)
			  .where(T_CAMP.FK_DOCUMENT.eq(pk));
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			affectedLines += sql.execute();

			UpdateConditionStep<TLocationRecord> sql1 = DSL.using(t)
			// @formatter:off
			  .update(T_LOCATION)
			  .set(T_LOCATION.FK_DOCUMENT, (Integer) null)
			  .where(T_LOCATION.FK_DOCUMENT.eq(pk));
		  // @formatter:on
			LOGGER.debug("{}", sql1.toString());
			affectedLines += sql1.execute();

			DeleteConditionStep<TDocumentRecord> sql2 = DSL.using(t)
			// @formatter:off
				.deleteFrom(T_DOCUMENT)
				.where(T_DOCUMENT.PK.eq(pk));
			// @formatter:on
			LOGGER.debug("{}", sql2.toString());
			affectedLines += sql2.execute();

			lrw.setNumber(affectedLines);
		});
		return lrw.getNumber();
	}

	/**
	 * upsert document in t_document
	 * 
	 * @param document
	 * @throws DataAccessException
	 */
	public Integer upsert(DocumentBean bean) throws DataAccessException {
		if (bean.getPk() != null) {
			UpdateConditionStep<TDocumentRecord> sql = getJooq()
			// @formatter:off
				.update(T_DOCUMENT)
				.set(T_DOCUMENT.NAME, bean.getName())
				.set(T_DOCUMENT.DOCTYPE, bean.getDoctype())
				.set(T_DOCUMENT.DOCUMENT, bean.getDocument())
				.set(T_DOCUMENT.FILETYPE, bean.getFiletype())
				.where(T_DOCUMENT.PK.eq(bean.getPk()));
		  // @formatter:on
			LOGGER.debug("{}", sql.toString());
			return sql.execute();
		} else {
			InsertValuesStep4<TDocumentRecord, String, EnumDocument, String, EnumFiletype> sql = getJooq()
  		// @formatter:off
			  .insertInto(T_DOCUMENT,
			  		        T_DOCUMENT.NAME,
			  		        T_DOCUMENT.DOCTYPE,
			  		        T_DOCUMENT.DOCUMENT,
			  		        T_DOCUMENT.FILETYPE)
			  .values(bean.getName(), bean.getDoctype(), bean.getDocument(), bean.getFiletype());
	  	// @formatter:on
			LOGGER.debug("{}", sql.toString());
			return sql.execute();
		}
	}

	/**
	 * delete document from t_persondocument
	 * @param pk
	 * @throws DataAccessException
	 */
	public void deletePersonDocument(Integer pk) throws DataAccessException {
		getJooq().transaction(t -> {
//			DeleteConditionStep<TPersondocumentRecord> sql = DSL.using(t)
//			// @formatter:off
//				.deleteFrom(T_PERSONDOCUMENT)
//				.where(T_PERSONDOCUMENT.PK.eq(pk));
//			// @formatter:on
//			LOGGER.debug("{}", sql.toString());
//			sql.execute();

			StringBuilder buf = new StringBuilder("Dokument ");
			// TODO: name document
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
	}
}
