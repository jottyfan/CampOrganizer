/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.enums;


import de.jottyfan.camporganizer.db.jooq.Camp;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


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
public enum EnumCamprole implements EnumType {

    boy("boy"),

    girl("girl"),

    helperboy("helperboy"),

    helpergirl("helpergirl"),

    kitchen("kitchen");

    private final String literal;

    private EnumCamprole(String literal) {
        this.literal = literal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return getSchema() == null ? null : getSchema().getCatalog();
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
    public String getName() {
        return "enum_camprole";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLiteral() {
        return literal;
    }
}
