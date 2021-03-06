/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.db.jooq.tables.VRole;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
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
public class VRoleRecord extends TableRecordImpl<VRoleRecord> implements Record1<EnumRole> {

    private static final long serialVersionUID = -942821253;

    /**
     * Setter for <code>camp.v_role.unnest</code>.
     */
    public void setUnnest(EnumRole value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.v_role.unnest</code>.
     */
    public EnumRole getUnnest() {
        return (EnumRole) get(0);
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<EnumRole> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<EnumRole> valuesRow() {
        return (Row1) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<EnumRole> field1() {
        return VRole.V_ROLE.UNNEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumRole component1() {
        return getUnnest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumRole value1() {
        return getUnnest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VRoleRecord value1(EnumRole value) {
        setUnnest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VRoleRecord values(EnumRole value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VRoleRecord
     */
    public VRoleRecord() {
        super(VRole.V_ROLE);
    }

    /**
     * Create a detached, initialised VRoleRecord
     */
    public VRoleRecord(EnumRole unnest) {
        super(VRole.V_ROLE);

        set(0, unnest);
    }
}
