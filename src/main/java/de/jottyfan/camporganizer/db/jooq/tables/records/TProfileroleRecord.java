/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.db.jooq.tables.TProfilerole;

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
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TProfileroleRecord extends TableRecordImpl<TProfileroleRecord> implements Record2<Integer, EnumRole> {

    private static final long serialVersionUID = -678107890;

    /**
     * Setter for <code>camp.t_profilerole.fk_profile</code>.
     */
    public void setFkProfile(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_profilerole.fk_profile</code>.
     */
    public Integer getFkProfile() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.t_profilerole.role</code>.
     */
    public void setRole(EnumRole value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_profilerole.role</code>.
     */
    public EnumRole getRole() {
        return (EnumRole) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, EnumRole> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, EnumRole> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TProfilerole.T_PROFILEROLE.FK_PROFILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<EnumRole> field2() {
        return TProfilerole.T_PROFILEROLE.ROLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getFkProfile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumRole value2() {
        return getRole();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileroleRecord value1(Integer value) {
        setFkProfile(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileroleRecord value2(EnumRole value) {
        setRole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfileroleRecord values(Integer value1, EnumRole value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TProfileroleRecord
     */
    public TProfileroleRecord() {
        super(TProfilerole.T_PROFILEROLE);
    }

    /**
     * Create a detached, initialised TProfileroleRecord
     */
    public TProfileroleRecord(Integer fkProfile, EnumRole role) {
        super(TProfilerole.T_PROFILEROLE);

        set(0, fkProfile);
        set(1, role);
    }
}
