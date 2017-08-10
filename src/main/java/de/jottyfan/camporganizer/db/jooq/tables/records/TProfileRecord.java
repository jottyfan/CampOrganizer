/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.tables.TProfile;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class TProfileRecord extends UpdatableRecordImpl<TProfileRecord> implements Record6<Integer, String, String, String, String, Timestamp> {

    private static final long serialVersionUID = 535265495;

    /**
     * Setter for <code>camp.t_profile.pk</code>.
     */
    public void setPk(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_profile.pk</code>.
     */
    public Integer getPk() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.t_profile.forename</code>.
     */
    public void setForename(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_profile.forename</code>.
     */
    public String getForename() {
        return (String) get(1);
    }

    /**
     * Setter for <code>camp.t_profile.surname</code>.
     */
    public void setSurname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>camp.t_profile.surname</code>.
     */
    public String getSurname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>camp.t_profile.username</code>.
     */
    public void setUsername(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>camp.t_profile.username</code>.
     */
    public String getUsername() {
        return (String) get(3);
    }

    /**
     * Setter for <code>camp.t_profile.password</code>.
     */
    public void setPassword(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>camp.t_profile.password</code>.
     */
    public String getPassword() {
        return (String) get(4);
    }

    /**
     * Setter for <code>camp.t_profile.duedate</code>.
     */
    public void setDuedate(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>camp.t_profile.duedate</code>.
     */
    public Timestamp getDuedate() {
        return (Timestamp) get(5);
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
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, String, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, String, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TProfile.T_PROFILE.PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TProfile.T_PROFILE.FORENAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TProfile.T_PROFILE.SURNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TProfile.T_PROFILE.USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return TProfile.T_PROFILE.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return TProfile.T_PROFILE.DUEDATE;
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
        return getForename();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSurname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getDuedate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileRecord value1(Integer value) {
        setPk(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileRecord value2(String value) {
        setForename(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileRecord value3(String value) {
        setSurname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileRecord value4(String value) {
        setUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileRecord value5(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileRecord value6(Timestamp value) {
        setDuedate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileRecord values(Integer value1, String value2, String value3, String value4, String value5, Timestamp value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TProfileRecord
     */
    public TProfileRecord() {
        super(TProfile.T_PROFILE);
    }

    /**
     * Create a detached, initialised TProfileRecord
     */
    public TProfileRecord(Integer pk, String forename, String surname, String username, String password, Timestamp duedate) {
        super(TProfile.T_PROFILE);

        set(0, pk);
        set(1, forename);
        set(2, surname);
        set(3, username);
        set(4, password);
        set(5, duedate);
    }
}
