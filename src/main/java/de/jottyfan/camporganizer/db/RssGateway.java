package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.Record3;
import org.jooq.Record4;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.UpdateConditionStep;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;
import de.jottyfan.camporganizer.rss.RssBean;

/**
 * 
 * @author jotty
 *
 */
public class RssGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(RssGateway.class);

	public RssGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	public List<RssBean> getRss(String recipientCode) throws DataAccessException {
		SelectConditionStep<Record3<Integer, String, Timestamp>> sql = getJooq()
		// @formatter:off
  		  .select(T_RSS.PK,
  		  		      T_RSS.MSG,
			  	      T_RSS.REGDATE)
		  .from(T_RSS)
		  .where(T_RSS.RECIPIENT.eq(recipientCode));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<RssBean> list = new ArrayList<>();
		for (Record3<Integer, String, Timestamp> r : sql.fetch()) {
			RssBean bean = new RssBean(r.get(T_RSS.PK));
			bean.setRecipient(recipientCode);
			bean.setMessage(r.get(T_RSS.MSG));
			bean.setPubdate(r.get(T_RSS.REGDATE));
			list.add(bean);
		}
		return list;
	}

	public List<RssBean> getAllRss() throws DataAccessException {
		SelectJoinStep<Record4<Integer, String, String, Timestamp>> sql = getJooq()
		// @formatter:off
  		  .select(T_RSS.PK,
  		  		      T_RSS.RECIPIENT,
  		  		      T_RSS.MSG,
			  	      T_RSS.REGDATE)
		  .from(T_RSS);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<RssBean> list = new ArrayList<>();
		for (Record4<Integer, String, String, Timestamp> r : sql.fetch()) {
			RssBean bean = new RssBean(r.get(T_RSS.PK));
			bean.setRecipient(r.get(T_RSS.RECIPIENT));
			bean.setMessage(r.get(T_RSS.MSG));
			bean.setPubdate(r.get(T_RSS.REGDATE));
			list.add(bean);
		}
		return list;
	}

	public void deleteRss(RssBean bean) throws DataAccessException {
		DeleteConditionStep<TRssRecord> sql = getJooq()
		// @formatter:off
			.deleteFrom(T_RSS)
			.where(T_RSS.PK.eq(bean.getPk()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();
	}

	public void update(RssBean bean) throws DataAccessException {
		UpdateConditionStep<TRssRecord> sql = getJooq()
		// @formatter:off
			.update(T_RSS)
			.set(T_RSS.MSG, bean.getMessage())
			.where(T_RSS.PK.eq(bean.getPk()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		sql.execute();
	}	
}
