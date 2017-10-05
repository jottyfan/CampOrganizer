/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.Keys;
import de.jottyfan.camporganizer.db.jooq.tables.records.TSalescontenttypeRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class TSalescontenttype extends TableImpl<TSalescontenttypeRecord> {

    private static final long serialVersionUID = -1334671838;

    /**
     * The reference instance of <code>camp.t_salescontenttype</code>
     */
    public static final TSalescontenttype T_SALESCONTENTTYPE = new TSalescontenttype();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TSalescontenttypeRecord> getRecordType() {
        return TSalescontenttypeRecord.class;
    }

    /**
     * The column <code>camp.t_salescontenttype.name</code>.
     */
    public final TableField<TSalescontenttypeRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>camp.t_salescontenttype</code> table reference
     */
    public TSalescontenttype() {
        this(DSL.name("t_salescontenttype"), null);
    }

    /**
     * Create an aliased <code>camp.t_salescontenttype</code> table reference
     */
    public TSalescontenttype(String alias) {
        this(DSL.name(alias), T_SALESCONTENTTYPE);
    }

    /**
     * Create an aliased <code>camp.t_salescontenttype</code> table reference
     */
    public TSalescontenttype(Name alias) {
        this(alias, T_SALESCONTENTTYPE);
    }

    private TSalescontenttype(Name alias, Table<TSalescontenttypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private TSalescontenttype(Name alias, Table<TSalescontenttypeRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.T_SALESCONTENTTYPE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TSalescontenttypeRecord> getPrimaryKey() {
        return Keys.T_SALESCONTENTTYPE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TSalescontenttypeRecord>> getKeys() {
        return Arrays.<UniqueKey<TSalescontenttypeRecord>>asList(Keys.T_SALESCONTENTTYPE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalescontenttype as(String alias) {
        return new TSalescontenttype(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TSalescontenttype as(Name alias) {
        return new TSalescontenttype(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TSalescontenttype rename(String name) {
        return new TSalescontenttype(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TSalescontenttype rename(Name name) {
        return new TSalescontenttype(name, null);
    }
}
