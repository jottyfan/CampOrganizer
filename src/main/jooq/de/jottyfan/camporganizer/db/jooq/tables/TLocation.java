/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.Keys;
import de.jottyfan.camporganizer.db.jooq.tables.records.TLocationRecord;

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
public class TLocation extends TableImpl<TLocationRecord> {

    private static final long serialVersionUID = -315115414;

    /**
     * The reference instance of <code>camp.t_location</code>
     */
    public static final TLocation T_LOCATION = new TLocation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TLocationRecord> getRecordType() {
        return TLocationRecord.class;
    }

    /**
     * The column <code>camp.t_location.pk</code>.
     */
    public final TableField<TLocationRecord, Integer> PK = createField("pk", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('camp.t_location_pk_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>camp.t_location.name</code>.
     */
    public final TableField<TLocationRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>camp.t_location.url</code>.
     */
    public final TableField<TLocationRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_location.fk_document</code>.
     */
    public final TableField<TLocationRecord, Integer> FK_DOCUMENT = createField("fk_document", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>camp.t_location</code> table reference
     */
    public TLocation() {
        this(DSL.name("t_location"), null);
    }

    /**
     * Create an aliased <code>camp.t_location</code> table reference
     */
    public TLocation(String alias) {
        this(DSL.name(alias), T_LOCATION);
    }

    /**
     * Create an aliased <code>camp.t_location</code> table reference
     */
    public TLocation(Name alias) {
        this(alias, T_LOCATION);
    }

    private TLocation(Name alias, Table<TLocationRecord> aliased) {
        this(alias, aliased, null);
    }

    private TLocation(Name alias, Table<TLocationRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.T_LOCATION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TLocationRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_LOCATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TLocation as(String alias) {
        return new TLocation(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TLocation as(Name alias) {
        return new TLocation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TLocation rename(String name) {
        return new TLocation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TLocation rename(Name name) {
        return new TLocation(name, null);
    }
}
