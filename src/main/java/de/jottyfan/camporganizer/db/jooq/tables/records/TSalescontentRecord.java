/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.tables.TSalescontent;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TSalescontentRecord extends TableRecordImpl<TSalescontentRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 214546646;

    /**
     * Setter for <code>camp.t_salescontent.fk_sales</code>.
     */
    public void setFkSales(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_salescontent.fk_sales</code>.
     */
    public Integer getFkSales() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.t_salescontent.fk_salescontenttype</code>.
     */
    public void setFkSalescontenttype(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_salescontent.fk_salescontenttype</code>.
     */
    public String getFkSalescontenttype() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TSalescontent.T_SALESCONTENT.FK_SALES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TSalescontent.T_SALESCONTENT.FK_SALESCONTENTTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getFkSales();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getFkSalescontenttype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getFkSales();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getFkSalescontenttype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalescontentRecord value1(Integer value) {
        setFkSales(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalescontentRecord value2(String value) {
        setFkSalescontenttype(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalescontentRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TSalescontentRecord
     */
    public TSalescontentRecord() {
        super(TSalescontent.T_SALESCONTENT);
    }

    /**
     * Create a detached, initialised TSalescontentRecord
     */
    public TSalescontentRecord(Integer fkSales, String fkSalescontenttype) {
        super(TSalescontent.T_SALESCONTENT);

        set(0, fkSales);
        set(1, fkSalescontenttype);
    }
}
