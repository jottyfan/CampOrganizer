/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.db.jooq.tables.records.VRoleRecord;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


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
public class VRole extends TableImpl<VRoleRecord> {

    private static final long serialVersionUID = -1011596958;

    /**
     * The reference instance of <code>camp.v_role</code>
     */
    public static final VRole V_ROLE = new VRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VRoleRecord> getRecordType() {
        return VRoleRecord.class;
    }

    /**
     * The column <code>camp.v_role.unnest</code>.
     */
    public final TableField<VRoleRecord, EnumRole> UNNEST = createField("unnest", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.camporganizer.db.jooq.enums.EnumRole.class), this, "");

    /**
     * Create a <code>camp.v_role</code> table reference
     */
    public VRole() {
        this("v_role", null);
    }

    /**
     * Create an aliased <code>camp.v_role</code> table reference
     */
    public VRole(String alias) {
        this(alias, V_ROLE);
    }

    private VRole(String alias, Table<VRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private VRole(String alias, Table<VRoleRecord> aliased, Field<?>[] parameters) {
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
    public VRole as(String alias) {
        return new VRole(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VRole rename(String name) {
        return new VRole(name, null);
    }
}
