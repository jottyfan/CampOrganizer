/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.tables.TLocation;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class TLocationRecord extends UpdatableRecordImpl<TLocationRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = -803532410;

    /**
     * Setter for <code>camp.t_location.pk</code>.
     */
    public void setPk(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_location.pk</code>.
     */
    public Integer getPk() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.t_location.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_location.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>camp.t_location.url</code>.
     */
    public void setUrl(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>camp.t_location.url</code>.
     */
    public String getUrl() {
        return (String) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TLocation.T_LOCATION.PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TLocation.T_LOCATION.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TLocation.T_LOCATION.URL;
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
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TLocationRecord value1(Integer value) {
        setPk(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TLocationRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TLocationRecord value3(String value) {
        setUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TLocationRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TLocationRecord
     */
    public TLocationRecord() {
        super(TLocation.T_LOCATION);
    }

    /**
     * Create a detached, initialised TLocationRecord
     */
    public TLocationRecord(Integer pk, String name, String url) {
        super(TLocation.T_LOCATION);

        set(0, pk);
        set(1, name);
        set(2, url);
    }
}