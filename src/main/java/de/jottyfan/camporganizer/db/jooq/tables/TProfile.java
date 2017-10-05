/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.Keys;
import de.jottyfan.camporganizer.db.jooq.tables.records.TProfileRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
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
public class TProfile extends TableImpl<TProfileRecord> {

    private static final long serialVersionUID = 736161643;

    /**
     * The reference instance of <code>camp.t_profile</code>
     */
    public static final TProfile T_PROFILE = new TProfile();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TProfileRecord> getRecordType() {
        return TProfileRecord.class;
    }

    /**
     * The column <code>camp.t_profile.pk</code>.
     */
    public final TableField<TProfileRecord, Integer> PK = createField("pk", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('camp.t_profile_pk_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>camp.t_profile.forename</code>.
     */
    public final TableField<TProfileRecord, String> FORENAME = createField("forename", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_profile.surname</code>.
     */
    public final TableField<TProfileRecord, String> SURNAME = createField("surname", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_profile.username</code>.
     */
    public final TableField<TProfileRecord, String> USERNAME = createField("username", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>camp.t_profile.password</code>.
     */
    public final TableField<TProfileRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>camp.t_profile.duedate</code>.
     */
    public final TableField<TProfileRecord, Timestamp> DUEDATE = createField("duedate", org.jooq.impl.SQLDataType.TIMESTAMP.defaultValue(org.jooq.impl.DSL.field("(now() + '1 year'::interval)", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>camp.t_profile.uuid</code>.
     */
    public final TableField<TProfileRecord, String> UUID = createField("uuid", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>camp.t_profile</code> table reference
     */
    public TProfile() {
        this(DSL.name("t_profile"), null);
    }

    /**
     * Create an aliased <code>camp.t_profile</code> table reference
     */
    public TProfile(String alias) {
        this(DSL.name(alias), T_PROFILE);
    }

    /**
     * Create an aliased <code>camp.t_profile</code> table reference
     */
    public TProfile(Name alias) {
        this(alias, T_PROFILE);
    }

    private TProfile(Name alias, Table<TProfileRecord> aliased) {
        this(alias, aliased, null);
    }

    private TProfile(Name alias, Table<TProfileRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.T_PROFILE_PKEY, Indexes.T_PROFILE_USERNAME_KEY, Indexes.T_PROFILE_UUID_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TProfileRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_PROFILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TProfileRecord> getPrimaryKey() {
        return Keys.T_PROFILE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TProfileRecord>> getKeys() {
        return Arrays.<UniqueKey<TProfileRecord>>asList(Keys.T_PROFILE_PKEY, Keys.T_PROFILE_USERNAME_KEY, Keys.T_PROFILE_UUID_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfile as(String alias) {
        return new TProfile(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TProfile as(Name alias) {
        return new TProfile(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TProfile rename(String name) {
        return new TProfile(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TProfile rename(Name name) {
        return new TProfile(name, null);
    }
}
