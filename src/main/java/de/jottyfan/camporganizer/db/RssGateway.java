package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_RSS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.exception.DataAccessException;

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

	public List<RssBean> getRss(String recipientCode) {
		SelectConditionStep<Record2<String, Timestamp>> sql = getJooq()
		// @formatter:off
  		  .select(T_RSS.MSG,
			  	      T_RSS.REGDATE)
		  .from(T_RSS)
		  .where(T_RSS.RECIPIENT.eq(recipientCode));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<RssBean> list = new ArrayList<>();
		for (Record2<String, Timestamp> r : sql.fetch()) {
			RssBean bean = new RssBean();
			bean.setRecipient(recipientCode);
			bean.setMessage(r.get(T_RSS.MSG));
			bean.setPubdate(r.get(T_RSS.REGDATE));
			list.add(bean);
		}
		return list;
	}
}
