/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.tables.records.VBudgetRecord;

import java.math.BigDecimal;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VBudget extends TableImpl<VBudgetRecord> {

    private static final long serialVersionUID = -480499971;

    /**
     * The reference instance of <code>camp.v_budget</code>
     */
    public static final VBudget V_BUDGET = new VBudget();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VBudgetRecord> getRecordType() {
        return VBudgetRecord.class;
    }

    /**
     * The column <code>camp.v_budget.budget</code>.
     */
    public final TableField<VBudgetRecord, BigDecimal> BUDGET = createField("budget", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>camp.v_budget.fk_camp</code>.
     */
    public final TableField<VBudgetRecord, Integer> FK_CAMP = createField("fk_camp", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>camp.v_budget.name</code>.
     */
    public final TableField<VBudgetRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.v_budget.location</code>.
     */
    public final TableField<VBudgetRecord, String> LOCATION = createField("location", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.v_budget.year</code>.
     */
    public final TableField<VBudgetRecord, Double> YEAR = createField("year", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>camp.v_budget</code> table reference
     */
    public VBudget() {
        this(DSL.name("v_budget"), null);
    }

    /**
     * Create an aliased <code>camp.v_budget</code> table reference
     */
    public VBudget(String alias) {
        this(DSL.name(alias), V_BUDGET);
    }

    /**
     * Create an aliased <code>camp.v_budget</code> table reference
     */
    public VBudget(Name alias) {
        this(alias, V_BUDGET);
    }

    private VBudget(Name alias, Table<VBudgetRecord> aliased) {
        this(alias, aliased, null);
    }

    private VBudget(Name alias, Table<VBudgetRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Camp.CAMP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VBudget as(String alias) {
        return new VBudget(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VBudget as(Name alias) {
        return new VBudget(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VBudget rename(String name) {
        return new VBudget(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VBudget rename(Name name) {
        return new VBudget(name, null);
    }
}
