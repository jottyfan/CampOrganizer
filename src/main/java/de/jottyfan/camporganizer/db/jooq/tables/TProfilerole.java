/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.Keys;
import de.jottyfan.camporganizer.db.jooq.enums.EnumRole;
import de.jottyfan.camporganizer.db.jooq.tables.records.TProfileroleRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class TProfilerole extends TableImpl<TProfileroleRecord> {

    private static final long serialVersionUID = 1477357557;

    /**
     * The reference instance of <code>camp.t_profilerole</code>
     */
    public static final TProfilerole T_PROFILEROLE = new TProfilerole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TProfileroleRecord> getRecordType() {
        return TProfileroleRecord.class;
    }

    /**
     * The column <code>camp.t_profilerole.fk_profile</code>.
     */
    public final TableField<TProfileroleRecord, Integer> FK_PROFILE = createField("fk_profile", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>camp.t_profilerole.role</code>.
     */
    public final TableField<TProfileroleRecord, EnumRole> ROLE = createField("role", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.camporganizer.db.jooq.enums.EnumRole.class), this, "");

    /**
     * Create a <code>camp.t_profilerole</code> table reference
     */
    public TProfilerole() {
        this(DSL.name("t_profilerole"), null);
    }

    /**
     * Create an aliased <code>camp.t_profilerole</code> table reference
     */
    public TProfilerole(String alias) {
        this(DSL.name(alias), T_PROFILEROLE);
    }

    /**
     * Create an aliased <code>camp.t_profilerole</code> table reference
     */
    public TProfilerole(Name alias) {
        this(alias, T_PROFILEROLE);
    }

    private TProfilerole(Name alias, Table<TProfileroleRecord> aliased) {
        this(alias, aliased, null);
    }

    private TProfilerole(Name alias, Table<TProfileroleRecord> aliased, Field<?>[] parameters) {
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
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.T_PROFILEROLE_FK_PROFILE_ROLE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TProfileroleRecord>> getKeys() {
        return Arrays.<UniqueKey<TProfileroleRecord>>asList(Keys.T_PROFILEROLE_FK_PROFILE_ROLE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TProfileroleRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TProfileroleRecord, ?>>asList(Keys.T_PROFILEROLE__T_PROFILEROLE_FK_PROFILE_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfilerole as(String alias) {
        return new TProfilerole(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfilerole as(Name alias) {
        return new TProfilerole(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TProfilerole rename(String name) {
        return new TProfilerole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TProfilerole rename(Name name) {
        return new TProfilerole(name, null);
    }
}
