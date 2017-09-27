/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.tables.records.TRssRecord;

import java.sql.Timestamp;

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
public class TRss extends TableImpl<TRssRecord> {

    private static final long serialVersionUID = 761258327;

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
     * Create a <code>camp.t_rss</code> table reference
     */
    public TRss() {
        this("t_rss", null);
    }

    /**
     * Create an aliased <code>camp.t_rss</code> table reference
     */
    public TRss(String alias) {
        this(alias, T_RSS);
    }

    private TRss(String alias, Table<TRssRecord> aliased) {
        this(alias, aliased, null);
    }

    private TRss(String alias, Table<TRssRecord> aliased, Field<?>[] parameters) {
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
    public TRss as(String alias) {
        return new TRss(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TRss rename(String name) {
        return new TRss(name, null);
    }
}
