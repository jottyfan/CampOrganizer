/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.tables.TRss;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;


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
public class TRssRecord extends TableRecordImpl<TRssRecord> implements Record4<String, Timestamp, String, Integer> {

    private static final long serialVersionUID = 1094406901;

    /**
     * Setter for <code>camp.t_rss.msg</code>.
     */
    public void setMsg(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_rss.msg</code>.
     */
    public String getMsg() {
        return (String) get(0);
    }

    /**
     * Setter for <code>camp.t_rss.regdate</code>.
     */
    public void setRegdate(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_rss.regdate</code>.
     */
    public Timestamp getRegdate() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>camp.t_rss.recipient</code>.
     */
    public void setRecipient(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>camp.t_rss.recipient</code>.
     */
    public String getRecipient() {
        return (String) get(2);
    }

    /**
     * Setter for <code>camp.t_rss.pk</code>.
     */
    public void setPk(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>camp.t_rss.pk</code>.
     */
    public Integer getPk() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, Timestamp, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, Timestamp, String, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TRss.T_RSS.MSG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return TRss.T_RSS.REGDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TRss.T_RSS.RECIPIENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return TRss.T_RSS.PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getMsg();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component2() {
        return getRegdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getRecipient();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getPk();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getMsg();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value2() {
        return getRegdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getRecipient();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getPk();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TRssRecord value1(String value) {
        setMsg(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TRssRecord value2(Timestamp value) {
        setRegdate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TRssRecord value3(String value) {
        setRecipient(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TRssRecord value4(Integer value) {
        setPk(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TRssRecord values(String value1, Timestamp value2, String value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TRssRecord
     */
    public TRssRecord() {
        super(TRss.T_RSS);
    }

    /**
     * Create a detached, initialised TRssRecord
     */
    public TRssRecord(String msg, Timestamp regdate, String recipient, Integer pk) {
        super(TRss.T_RSS);

        set(0, msg);
        set(1, regdate);
        set(2, recipient);
        set(3, pk);
    }
}
