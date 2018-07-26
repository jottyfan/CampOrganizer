/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables;


import de.jottyfan.camporganizer.db.jooq.Camp;
import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;
import de.jottyfan.camporganizer.db.jooq.tables.records.VCampdocumentRecord;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class VCampdocument extends TableImpl<VCampdocumentRecord> {

    private static final long serialVersionUID = 1730590197;

    /**
     * The reference instance of <code>camp.v_campdocument</code>
     */
    public static final VCampdocument V_CAMPDOCUMENT = new VCampdocument();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VCampdocumentRecord> getRecordType() {
        return VCampdocumentRecord.class;
    }

    /**
     * The column <code>camp.v_campdocument.fk_camp</code>.
     */
    public final TableField<VCampdocumentRecord, Integer> FK_CAMP = createField("fk_camp", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>camp.v_campdocument.campname</code>.
     */
    public final TableField<VCampdocumentRecord, String> CAMPNAME = createField("campname", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.v_campdocument.arrive</code>.
     */
    public final TableField<VCampdocumentRecord, Timestamp> ARRIVE = createField("arrive", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>camp.v_campdocument.fk_document</code>.
     */
    public final TableField<VCampdocumentRecord, Integer> FK_DOCUMENT = createField("fk_document", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>camp.v_campdocument.document</code>.
     */
    public final TableField<VCampdocumentRecord, String> DOCUMENT = createField("document", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.v_campdocument.documentname</code>.
     */
    public final TableField<VCampdocumentRecord, String> DOCUMENTNAME = createField("documentname", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>camp.v_campdocument.doctype</code>.
     */
    public final TableField<VCampdocumentRecord, EnumDocument> DOCTYPE = createField("doctype", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.camporganizer.db.jooq.enums.EnumDocument.class), this, "");

    /**
     * The column <code>camp.v_campdocument.filetype</code>.
     */
    public final TableField<VCampdocumentRecord, EnumFiletype> FILETYPE = createField("filetype", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype.class), this, "");

    /**
     * Create a <code>camp.v_campdocument</code> table reference
     */
    public VCampdocument() {
        this(DSL.name("v_campdocument"), null);
    }

    /**
     * Create an aliased <code>camp.v_campdocument</code> table reference
     */
    public VCampdocument(String alias) {
        this(DSL.name(alias), V_CAMPDOCUMENT);
    }

    /**
     * Create an aliased <code>camp.v_campdocument</code> table reference
     */
    public VCampdocument(Name alias) {
        this(alias, V_CAMPDOCUMENT);
    }

    private VCampdocument(Name alias, Table<VCampdocumentRecord> aliased) {
        this(alias, aliased, null);
    }

    private VCampdocument(Name alias, Table<VCampdocumentRecord> aliased, Field<?>[] parameters) {
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
    public VCampdocument as(String alias) {
        return new VCampdocument(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VCampdocument as(Name alias) {
        return new VCampdocument(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VCampdocument rename(String name) {
        return new VCampdocument(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VCampdocument rename(Name name) {
        return new VCampdocument(name, null);
    }
}