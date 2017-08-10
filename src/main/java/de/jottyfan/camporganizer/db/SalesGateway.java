package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_SALES;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.InsertValuesStep7;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.tables.records.TSalesRecord;
import de.jottyfan.camporganizer.sales.SalesBean;

/**
 * 
 * @author jotty
 *
 */
public class SalesGateway extends JooqGateway {

	private static final Logger LOGGER = LogManager.getLogger(ProfileGateway.class);

	public SalesGateway(FacesContext facesContext) throws DataAccessException {
		super(facesContext);
	}

	/**
	 * insert content of bean into database table
	 * 
	 * @param bean
	 *          to be used as insert
	 * @return amount of affected lines
	 * @throws DataAccessException
	 */
	public Integer insert(SalesBean bean) throws DataAccessException {
		InsertValuesStep7<TSalesRecord, String, Integer, String, BigDecimal, Timestamp, String, String> sql = getJooq()
		// @formatter:off
			.insertInto(T_SALES,
					        T_SALES.TRADER,
					        T_SALES.FK_CAMP,
					        T_SALES.PROVIDER,
					        T_SALES.CASH,
					        T_SALES.BUYDATE,
					        T_SALES.RECIPENUMBER,
					        T_SALES.RECIPENOTE)
			.values(bean.getTrader(), bean.getFkCamp(), bean.getProvider(), bean.getCashBigDecimal(), bean.getBuydate() == null ? null : new Timestamp(bean.getBuydate().getTime()), bean.getRecipeNumber(), bean.getRecipeNote());
	  // @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}
}
