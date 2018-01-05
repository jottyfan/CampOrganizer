package de.jottyfan.camporganizer.db;

import static de.jottyfan.camporganizer.db.jooq.Tables.T_SALES;
import static de.jottyfan.camporganizer.db.jooq.Tables.V_BUDGET;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DeleteConditionStep;
import org.jooq.Query;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Record5;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSeekStep1;
import org.jooq.exception.DataAccessException;

import de.jottyfan.camporganizer.db.jooq.tables.records.TSalesRecord;
import de.jottyfan.camporganizer.modules.businessman.BudgetBean;
import de.jottyfan.camporganizer.modules.businessman.SalesBean;

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
	 * upsert content of bean in database table
	 * 
	 * @param bean
	 *          to be used for upsert
	 * @return amount of affected lines
	 * @throws DataAccessException
	 */
	public Integer upsert(SalesBean bean) throws DataAccessException {
		boolean isNew = bean.getPk() == null;
		Timestamp buydate = bean.getBuydate() == null ? null : new Timestamp(bean.getBuydate().getTime());
		@SuppressWarnings("resource")
		Query sql = isNew ? getJooq()
		// @formatter:off
			.insertInto(T_SALES,
					        T_SALES.TRADER,
					        T_SALES.FK_CAMP,
					        T_SALES.PROVIDER,
					        T_SALES.INCREDIENTS,
					        T_SALES.CASH,
					        T_SALES.BUYDATE,
					        T_SALES.RECIPENUMBER,
					        T_SALES.RECIPESHOT,
					        T_SALES.RECIPENOTE)
			.values(bean.getTrader(), bean.getFkCamp(), bean.getProvider(), bean.getIncredients(), bean.getCashBigDecimal(), buydate, bean.getRecipeNumber(), bean.getRecipeshot(), bean.getRecipeNote())
			: getJooq()
			.update(T_SALES)
			.set(T_SALES.TRADER, bean.getTrader())
			.set(T_SALES.FK_CAMP, bean.getFkCamp())
			.set(T_SALES.PROVIDER, bean.getProvider())
			.set(T_SALES.INCREDIENTS, bean.getIncredients())
			.set(T_SALES.CASH, bean.getCashBigDecimal())
			.set(T_SALES.BUYDATE, buydate)
			.set(T_SALES.RECIPENUMBER, bean.getRecipeNumber())
			.set(T_SALES.RECIPESHOT, bean.getRecipeshot())
			.set(T_SALES.RECIPENOTE, bean.getRecipeNote())		
			.where(T_SALES.PK.eq(bean.getPk()));
	  // @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}

	/**
	 * get all sales from db
	 * 
	 * @return list of sales
	 * @throws DataAccessException
	 */
	public List<SalesBean> getAllSales() throws DataAccessException {
		SelectJoinStep<Record10<Integer, String, Integer, String, String, BigDecimal, Timestamp, String, byte[], String>> sql = getJooq()
		// @formatter:off
			.select(T_SALES.PK,
							T_SALES.TRADER,
					    T_SALES.FK_CAMP,
					    T_SALES.PROVIDER,
					    T_SALES.INCREDIENTS,
					    T_SALES.CASH,
					    T_SALES.BUYDATE,
					    T_SALES.RECIPENUMBER,
					    T_SALES.RECIPESHOT,
					    T_SALES.RECIPENOTE)
			.from(T_SALES);
		// @formatter.on
		LOGGER.debug("{}", sql.toString());
		List<SalesBean> list = new ArrayList<>();
		for (Record10<Integer, String, Integer, String, String, BigDecimal, Timestamp, String, byte[], String> r : sql.fetch()) {
			SalesBean bean = new SalesBean();
			BigDecimal cash = r.get(T_SALES.CASH);			
			bean.setPk(r.get(T_SALES.PK));
			bean.setTrader(r.get(T_SALES.TRADER));
			bean.setFkCamp(r.get(T_SALES.FK_CAMP));
			bean.setProvider(r.get(T_SALES.PROVIDER));
			bean.setIncredients(r.get(T_SALES.INCREDIENTS));
			bean.setCash(cash == null ? null : cash.floatValue());
			bean.setBuydate(r.get(T_SALES.BUYDATE));
			bean.setRecipeNumber(r.get(T_SALES.RECIPENUMBER));
			bean.setRecipeNote(r.get(T_SALES.RECIPENOTE));
			bean.setRecipeshot(r.get(T_SALES.RECIPESHOT));
			list.add(bean);
		}
		return list;
	}

	/**
	 * delete tupel from db referenced by its pk
	 * 
	 * @param bean to be removed from db
	 * @return affected rows
	 * @throws DataAccessException
	 */
	public int delete(SalesBean bean) throws DataAccessException {
		DeleteConditionStep<TSalesRecord> sql = getJooq()
		// @formatter:off
			.deleteFrom(T_SALES)
			.where(T_SALES.PK.eq(bean.getPk()));
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		return sql.execute();
	}

	/**
	 * get all traders from db
	 * 
	 * @return string of traders, comma separated
	 */
	public List<String> getAllTraders() throws DataAccessException {
		SelectSeekStep1<Record1<String>, String> sql = getJooq()
		// @formatter:off
			.selectDistinct(T_SALES.TRADER)
			.from(T_SALES)
			.orderBy(T_SALES.TRADER);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<String> list = new ArrayList<>();
		for (Record1<String> r : sql.fetch()) {
			list.add(r.get(T_SALES.TRADER));
		}
		return list;
	}

	/**
	 * get all providers from db
	 * 
	 * @return string of providers, comma separated
	 */
	public List<String> getAllProviders() throws DataAccessException {
		SelectSeekStep1<Record1<String>, String> sql = getJooq()
		// @formatter:off
			.selectDistinct(T_SALES.PROVIDER)
			.from(T_SALES)
			.orderBy(T_SALES.PROVIDER);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<String> list = new ArrayList<>();
		for (Record1<String> r : sql.fetch()) {
			list.add(r.get(T_SALES.PROVIDER));
		}
		return list;
	}

	/**
	 * get budget list from db
	 * 
	 * @return list of budget beans
	 */
	public List<BudgetBean> getBudget() throws DataAccessException {
		SelectJoinStep<Record5<BigDecimal, Integer, String, String, Double>> sql = getJooq()
		// @formatter:off
			.select(V_BUDGET.BUDGET,
					    V_BUDGET.FK_CAMP,
					    V_BUDGET.LOCATION,
					    V_BUDGET.NAME,
					    V_BUDGET.YEAR)
			.from(V_BUDGET);
		// @formatter:on
		LOGGER.debug("{}", sql.toString());
		List<BudgetBean> list = new ArrayList<>();
		for (Record5<BigDecimal, Integer, String, String, Double> r : sql.fetch()) {
			Double year = r.get(V_BUDGET.YEAR);
			StringBuilder buf = new StringBuilder();
			buf.append(r.get(V_BUDGET.NAME)).append(" in ");
			buf.append(r.get(V_BUDGET.LOCATION)).append(" ");
			buf.append(year == null ? "" : year.intValue());
			BigDecimal budget = r.get(V_BUDGET.BUDGET);
			list.add(new BudgetBean(buf.toString(), budget == null ? null : budget.multiply(new BigDecimal(-1))));
		}
		return list;
	}
}
