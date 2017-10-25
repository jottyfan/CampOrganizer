/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.Indexes;
import de.jottyfan.camporganizer.db.jooq.Keys;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.db.jooq.tables.records.TDocumentRecord;

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
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TDocument extends TableImpl<TDocumentRecord> {

    private static final long serialVersionUID = 476415410;

    /**
     * The reference instance of <code>camp.t_document</code>
     */
    public static final TDocument T_DOCUMENT = new TDocument();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TDocumentRecord> getRecordType() {
        return TDocumentRecord.class;
    }

    /**
     * The column <code>camp.t_document.pk</code>.
     */
    public final TableField<TDocumentRecord, Integer> PK = createField("pk", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('camp.t_document_pk_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>camp.t_document.doctype</code>.
     */
    public final TableField<TDocumentRecord, EnumDocument> DOCTYPE = createField("doctype", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.camporganizer.db.jooq.enums.EnumDocument.class), this, "");

    /**
     * The column <code>camp.t_document.name</code>.
     */
    public final TableField<TDocumentRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_document.document</code>.
     */
    public final TableField<TDocumentRecord, String> DOCUMENT = createField("document", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.t_document.filetype</code>.
     */
    public final TableField<TDocumentRecord, EnumFiletype> FILETYPE = createField("filetype", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype.class), this, "");

    /**
     * Create a <code>camp.t_document</code> table reference
     */
    public TDocument() {
        this(DSL.name("t_document"), null);
    }

    /**
     * Create an aliased <code>camp.t_document</code> table reference
     */
    public TDocument(String alias) {
        this(DSL.name(alias), T_DOCUMENT);
    }

    /**
     * Create an aliased <code>camp.t_document</code> table reference
     */
    public TDocument(Name alias) {
        this(alias, T_DOCUMENT);
    }

    private TDocument(Name alias, Table<TDocumentRecord> aliased) {
        this(alias, aliased, null);
    }

    private TDocument(Name alias, Table<TDocumentRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.T_DOCUMENT_NAME_KEY, Indexes.T_DOCUMENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TDocumentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_DOCUMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TDocumentRecord> getPrimaryKey() {
        return Keys.T_DOCUMENT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TDocumentRecord>> getKeys() {
        return Arrays.<UniqueKey<TDocumentRecord>>asList(Keys.T_DOCUMENT_PKEY, Keys.T_DOCUMENT_NAME_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TDocument as(String alias) {
        return new TDocument(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TDocument as(Name alias) {
        return new TDocument(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TDocument rename(String name) {
        return new TDocument(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TDocument rename(Name name) {
        return new TDocument(name, null);
    }
}
