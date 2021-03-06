/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.tables.records.TSalesprofileRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class TSalesprofile extends TableImpl<TSalesprofileRecord> {

    private static final long serialVersionUID = -1990372098;

    /**
     * The reference instance of <code>camp.t_salesprofile</code>
     */
    public static final TSalesprofile T_SALESPROFILE = new TSalesprofile();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TSalesprofileRecord> getRecordType() {
        return TSalesprofileRecord.class;
    }

    /**
     * The column <code>camp.t_salesprofile.pk</code>.
     */
    public final TableField<TSalesprofileRecord, Integer> PK = createField("pk", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>camp.t_salesprofile.fk_camp</code>.
     */
    public final TableField<TSalesprofileRecord, Integer> FK_CAMP = createField("fk_camp", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>camp.t_salesprofile.fk_profile</code>.
     */
    public final TableField<TSalesprofileRecord, Integer> FK_PROFILE = createField("fk_profile", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>camp.t_salesprofile</code> table reference
     */
    public TSalesprofile() {
        this(DSL.name("t_salesprofile"), null);
    }

    /**
     * Create an aliased <code>camp.t_salesprofile</code> table reference
     */
    public TSalesprofile(String alias) {
        this(DSL.name(alias), T_SALESPROFILE);
    }

    /**
     * Create an aliased <code>camp.t_salesprofile</code> table reference
     */
    public TSalesprofile(Name alias) {
        this(alias, T_SALESPROFILE);
    }

    private TSalesprofile(Name alias, Table<TSalesprofileRecord> aliased) {
        this(alias, aliased, null);
    }

    private TSalesprofile(Name alias, Table<TSalesprofileRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.T_SALESPROFILE_FK_CAMP_FK_PROFILE_KEY, Indexes.T_SALESPROFILE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesprofile as(String alias) {
        return new TSalesprofile(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalesprofile as(Name alias) {
        return new TSalesprofile(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TSalesprofile rename(String name) {
        return new TSalesprofile(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TSalesprofile rename(Name name) {
        return new TSalesprofile(name, null);
    }
}
