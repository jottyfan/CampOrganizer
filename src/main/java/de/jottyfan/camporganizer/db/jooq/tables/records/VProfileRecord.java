/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.db.jooq.tables.VProfile;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.TableRecordImpl;


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
public class VProfileRecord extends TableRecordImpl<VProfileRecord> implements Record7<Integer, String, String, String, String, String, EnumRole[]> {

    private static final long serialVersionUID = 79968606;

    /**
     * Setter for <code>camp.v_profile.pk</code>.
     */
    public void setPk(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.v_profile.pk</code>.
     */
    public Integer getPk() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.v_profile.forename</code>.
     */
    public void setForename(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.v_profile.forename</code>.
     */
    public String getForename() {
        return (String) get(1);
    }

    /**
     * Setter for <code>camp.v_profile.surname</code>.
     */
    public void setSurname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>camp.v_profile.surname</code>.
     */
    public String getSurname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>camp.v_profile.username</code>.
     */
    public void setUsername(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>camp.v_profile.username</code>.
     */
    public String getUsername() {
        return (String) get(3);
    }

    /**
     * Setter for <code>camp.v_profile.password</code>.
     */
    public void setPassword(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>camp.v_profile.password</code>.
     */
    public String getPassword() {
        return (String) get(4);
    }

    /**
     * Setter for <code>camp.v_profile.uuid</code>.
     */
    public void setUuid(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>camp.v_profile.uuid</code>.
     */
    public String getUuid() {
        return (String) get(5);
    }

    /**
     * Setter for <code>camp.v_profile.roles</code>.
     */
    public void setRoles(EnumRole... value) {
        set(6, value);
    }

    /**
     * Getter for <code>camp.v_profile.roles</code>.
     */
    public EnumRole[] getRoles() {
        return (EnumRole[]) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, String, String, EnumRole[]> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, String, String, EnumRole[]> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return VProfile.V_PROFILE.PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return VProfile.V_PROFILE.FORENAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return VProfile.V_PROFILE.SURNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return VProfile.V_PROFILE.USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return VProfile.V_PROFILE.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return VProfile.V_PROFILE.UUID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<EnumRole[]> field7() {
        return VProfile.V_PROFILE.ROLES;
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
    public String value6() {
        return getUuid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumRole[] value7() {
        return getRoles();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord value1(Integer value) {
        setPk(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord value2(String value) {
        setForename(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord value3(String value) {
        setSurname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord value4(String value) {
        setUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord value5(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord value6(String value) {
        setUuid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord value7(EnumRole... value) {
        setRoles(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VProfileRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, EnumRole[] value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VProfileRecord
     */
    public VProfileRecord() {
        super(VProfile.V_PROFILE);
    }

    /**
     * Create a detached, initialised VProfileRecord
     */
    public VProfileRecord(Integer pk, String forename, String surname, String username, String password, String uuid, EnumRole[] roles) {
        super(VProfile.V_PROFILE);

        set(0, pk);
        set(1, forename);
        set(2, surname);
        set(3, username);
        set(4, password);
        set(5, uuid);
        set(6, roles);
    }
}
