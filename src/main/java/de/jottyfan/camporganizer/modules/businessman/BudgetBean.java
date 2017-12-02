package de.jottyfan.camporganizer.modules.businessman;

import java.math.BigDecimal;

/**
 * F
 * 
 * @author jotty
 *
 */
public class BudgetBean {
	private final String camp;
	private final Float budget;

	public BudgetBean(String camp, BigDecimal budget) {
		super();
		this.camp = camp;
		this.budget = budget == null ? 0.0f : budget.floatValue();
	}

	public String getCamp() {
		return camp;
	}

	public Float getBudget() {
		return budget;
	}
}
