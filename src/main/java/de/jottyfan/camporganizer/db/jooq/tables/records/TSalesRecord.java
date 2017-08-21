/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.tables.TSales;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TSalesRecord extends UpdatableRecordImpl<TSalesRecord> implements Record10<Integer, String, Integer, String, BigDecimal, String, Timestamp, String, byte[], String> {

    private static final long serialVersionUID = -1509742575;

    /**
     * Setter for <code>camp.t_sales.pk</code>.
     */
    public void setPk(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_sales.pk</code>.
     */
    public Integer getPk() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.t_sales.trader</code>.
     */
    public void setTrader(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_sales.trader</code>.
     */
    public String getTrader() {
        return (String) get(1);
    }

    /**
     * Setter for <code>camp.t_sales.fk_camp</code>.
     */
    public void setFkCamp(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>camp.t_sales.fk_camp</code>.
     */
    public Integer getFkCamp() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>camp.t_sales.provider</code>.
     */
    public void setProvider(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>camp.t_sales.provider</code>.
     */
    public String getProvider() {
        return (String) get(3);
    }

    /**
     * Setter for <code>camp.t_sales.cash</code>.
     */
    public void setCash(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>camp.t_sales.cash</code>.
     */
    public BigDecimal getCash() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>camp.t_sales.incredients</code>.
     */
    public void setIncredients(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>camp.t_sales.incredients</code>.
     */
    public String getIncredients() {
        return (String) get(5);
    }

    /**
     * Setter for <code>camp.t_sales.buydate</code>.
     */
    public void setBuydate(Timestamp value) {
        set(6, value);
    }

    /**
     * Getter for <code>camp.t_sales.buydate</code>.
     */
    public Timestamp getBuydate() {
        return (Timestamp) get(6);
    }

    /**
     * Setter for <code>camp.t_sales.recipenumber</code>.
     */
    public void setRecipenumber(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>camp.t_sales.recipenumber</code>.
     */
    public String getRecipenumber() {
        return (String) get(7);
    }

    /**
     * Setter for <code>camp.t_sales.recipeshot</code>.
     */
    public void setRecipeshot(byte... value) {
        set(8, value);
    }

    /**
     * Getter for <code>camp.t_sales.recipeshot</code>.
     */
    public byte[] getRecipeshot() {
        return (byte[]) get(8);
    }

    /**
     * Setter for <code>camp.t_sales.recipenote</code>.
     */
    public void setRecipenote(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>camp.t_sales.recipenote</code>.
     */
    public String getRecipenote() {
        return (String) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, String, Integer, String, BigDecimal, String, Timestamp, String, byte[], String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, String, Integer, String, BigDecimal, String, Timestamp, String, byte[], String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TSales.T_SALES.PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TSales.T_SALES.TRADER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return TSales.T_SALES.FK_CAMP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TSales.T_SALES.PROVIDER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field5() {
        return TSales.T_SALES.CASH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TSales.T_SALES.INCREDIENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field7() {
        return TSales.T_SALES.BUYDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return TSales.T_SALES.RECIPENUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<byte[]> field9() {
        return TSales.T_SALES.RECIPESHOT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return TSales.T_SALES.RECIPENOTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getPk();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTrader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getFkCamp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getProvider();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value5() {
        return getCash();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getIncredients();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value7() {
        return getBuydate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getRecipenumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] value9() {
        return getRecipeshot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getRecipenote();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value1(Integer value) {
        setPk(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value2(String value) {
        setTrader(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value3(Integer value) {
        setFkCamp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value4(String value) {
        setProvider(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value5(BigDecimal value) {
        setCash(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value6(String value) {
        setIncredients(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value7(Timestamp value) {
        setBuydate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value8(String value) {
        setRecipenumber(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value9(byte... value) {
        setRecipeshot(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord value10(String value) {
        setRecipenote(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesRecord values(Integer value1, String value2, Integer value3, String value4, BigDecimal value5, String value6, Timestamp value7, String value8, byte[] value9, String value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TSalesRecord
     */
    public TSalesRecord() {
        super(TSales.T_SALES);
    }

    /**
     * Create a detached, initialised TSalesRecord
     */
    public TSalesRecord(Integer pk, String trader, Integer fkCamp, String provider, BigDecimal cash, String incredients, Timestamp buydate, String recipenumber, byte[] recipeshot, String recipenote) {
        super(TSales.T_SALES);

        set(0, pk);
        set(1, trader);
        set(2, fkCamp);
        set(3, provider);
        set(4, cash);
        set(5, incredients);
        set(6, buydate);
        set(7, recipenumber);
        set(8, recipeshot);
        set(9, recipenote);
    }
}
