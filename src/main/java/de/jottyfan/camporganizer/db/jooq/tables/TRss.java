/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.Keys;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;

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
public class TRss extends TableImpl<TRssRecord> {

    private static final long serialVersionUID = 2075442785;

    /**
     * The reference instance of <code>camp.t_rss</code>
     */
    public static final TRss T_RSS = new TRss();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TRssRecord> getRecordType() {
        return TRssRecord.class;
    }

    /**
     * The column <code>camp.t_rss.msg</code>.
     */
    public final TableField<TRssRecord, String> MSG = createField("msg", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_rss.regdate</code>.
     */
    public final TableField<TRssRecord, Timestamp> REGDATE = createField("regdate", org.jooq.impl.SQLDataType.TIMESTAMP.defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>camp.t_rss.recipient</code>.
     */
    public final TableField<TRssRecord, String> RECIPIENT = createField("recipient", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_rss.pk</code>.
     */
    public final TableField<TRssRecord, Integer> PK = createField("pk", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('camp.t_rss_pk_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>camp.t_rss</code> table reference
     */
    public TRss() {
        this(DSL.name("t_rss"), null);
    }

    /**
     * Create an aliased <code>camp.t_rss</code> table reference
     */
    public TRss(String alias) {
        this(DSL.name(alias), T_RSS);
    }

    /**
     * Create an aliased <code>camp.t_rss</code> table reference
     */
    public TRss(Name alias) {
        this(alias, T_RSS);
    }

    private TRss(Name alias, Table<TRssRecord> aliased) {
        this(alias, aliased, null);
    }

    private TRss(Name alias, Table<TRssRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.T_RSS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TRssRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_RSS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TRssRecord> getPrimaryKey() {
        return Keys.T_RSS_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TRssRecord>> getKeys() {
        return Arrays.<UniqueKey<TRssRecord>>asList(Keys.T_RSS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TRss as(String alias) {
        return new TRss(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TRss as(Name alias) {
        return new TRss(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TRss rename(String name) {
        return new TRss(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TRss rename(Name name) {
        return new TRss(name, null);
    }
}
