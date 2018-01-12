/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.Keys;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.db.jooq.tables.records.TPersondocumentRecord;

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
public class TPersondocument extends TableImpl<TPersondocumentRecord> {

    private static final long serialVersionUID = -166821891;

    /**
     * The reference instance of <code>camp.t_persondocument</code>
     */
    public static final TPersondocument T_PERSONDOCUMENT = new TPersondocument();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TPersondocumentRecord> getRecordType() {
        return TPersondocumentRecord.class;
    }

    /**
     * The column <code>camp.t_persondocument.pk</code>.
     */
    public final TableField<TPersondocumentRecord, Integer> PK = createField("pk", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('camp.t_persondocument_pk_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>camp.t_persondocument.fk_person</code>.
     */
    public final TableField<TPersondocumentRecord, Integer> FK_PERSON = createField("fk_person", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>camp.t_persondocument.name</code>.
     */
    public final TableField<TPersondocumentRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_persondocument.document</code>.
     */
    public final TableField<TPersondocumentRecord, String> DOCUMENT = createField("document", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_persondocument.filetype</code>.
     */
    public final TableField<TPersondocumentRecord, EnumFiletype> FILETYPE = createField("filetype", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype.class), this, "");

    /**
     * Create a <code>camp.t_persondocument</code> table reference
     */
    public TPersondocument() {
        this(DSL.name("t_persondocument"), null);
    }

    /**
     * Create an aliased <code>camp.t_persondocument</code> table reference
     */
    public TPersondocument(String alias) {
        this(DSL.name(alias), T_PERSONDOCUMENT);
    }

    /**
     * Create an aliased <code>camp.t_persondocument</code> table reference
     */
    public TPersondocument(Name alias) {
        this(alias, T_PERSONDOCUMENT);
    }

    private TPersondocument(Name alias, Table<TPersondocumentRecord> aliased) {
        this(alias, aliased, null);
    }

    private TPersondocument(Name alias, Table<TPersondocumentRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.T_PERSONDOCUMENT_FK_PERSON_NAME_KEY, Indexes.T_PERSONDOCUMENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TPersondocumentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_PERSONDOCUMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersondocument as(String alias) {
        return new TPersondocument(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersondocument as(Name alias) {
        return new TPersondocument(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TPersondocument rename(String name) {
        return new TPersondocument(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TPersondocument rename(Name name) {
        return new TPersondocument(name, null);
    }
}
