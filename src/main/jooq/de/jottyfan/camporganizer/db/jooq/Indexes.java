/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq;


import de.jottyfan.camporganizer.db.jooq.tables.TCamp;
import de.jottyfan.camporganizer.db.jooq.tables.TDocument;
import de.jottyfan.camporganizer.db.jooq.tables.TLocation;
import de.jottyfan.camporganizer.db.jooq.tables.TPerson;
import de.jottyfan.camporganizer.db.jooq.tables.TPersondocument;
import de.jottyfan.camporganizer.db.jooq.tables.TProfile;
import de.jottyfan.camporganizer.db.jooq.tables.TProfilerole;
import de.jottyfan.camporganizer.db.jooq.tables.TRss;
import de.jottyfan.camporganizer.db.jooq.tables.TSales;
import de.jottyfan.camporganizer.db.jooq.tables.TSalescontent;
import de.jottyfan.camporganizer.db.jooq.tables.TSalescontenttype;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling indexes of tables of the <code>camp</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index T_CAMP_PKEY = Indexes0.T_CAMP_PKEY;
    public static final Index T_DOCUMENT_NAME_KEY = Indexes0.T_DOCUMENT_NAME_KEY;
    public static final Index T_DOCUMENT_PKEY = Indexes0.T_DOCUMENT_PKEY;
    public static final Index T_LOCATION_PKEY = Indexes0.T_LOCATION_PKEY;
    public static final Index T_PERSON_PKEY = Indexes0.T_PERSON_PKEY;
    public static final Index UK_PERSON = Indexes0.UK_PERSON;
    public static final Index T_PERSONDOCUMENT_FK_PERSON_NAME_KEY = Indexes0.T_PERSONDOCUMENT_FK_PERSON_NAME_KEY;
    public static final Index T_PERSONDOCUMENT_PKEY = Indexes0.T_PERSONDOCUMENT_PKEY;
    public static final Index T_PROFILE_PKEY = Indexes0.T_PROFILE_PKEY;
    public static final Index T_PROFILE_USERNAME_KEY = Indexes0.T_PROFILE_USERNAME_KEY;
    public static final Index T_PROFILE_UUID_KEY = Indexes0.T_PROFILE_UUID_KEY;
    public static final Index T_PROFILEROLE_FK_PROFILE_ROLE_KEY = Indexes0.T_PROFILEROLE_FK_PROFILE_ROLE_KEY;
    public static final Index T_RSS_PKEY = Indexes0.T_RSS_PKEY;
    public static final Index T_SALES_PKEY = Indexes0.T_SALES_PKEY;
    public static final Index T_SALESCONTENT_FK_SALES_FK_SALESCONTENTTYPE_KEY = Indexes0.T_SALESCONTENT_FK_SALES_FK_SALESCONTENTTYPE_KEY;
    public static final Index T_SALESCONTENTTYPE_PKEY = Indexes0.T_SALESCONTENTTYPE_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 extends AbstractKeys {
        public static Index T_CAMP_PKEY = createIndex("t_camp_pkey", TCamp.T_CAMP, new OrderField[] { TCamp.T_CAMP.PK }, true);
        public static Index T_DOCUMENT_NAME_KEY = createIndex("t_document_name_key", TDocument.T_DOCUMENT, new OrderField[] { TDocument.T_DOCUMENT.NAME }, true);
        public static Index T_DOCUMENT_PKEY = createIndex("t_document_pkey", TDocument.T_DOCUMENT, new OrderField[] { TDocument.T_DOCUMENT.PK }, true);
        public static Index T_LOCATION_PKEY = createIndex("t_location_pkey", TLocation.T_LOCATION, new OrderField[] { TLocation.T_LOCATION.PK }, true);
        public static Index T_PERSON_PKEY = createIndex("t_person_pkey", TPerson.T_PERSON, new OrderField[] { TPerson.T_PERSON.PK }, true);
        public static Index UK_PERSON = createIndex("uk_person", TPerson.T_PERSON, new OrderField[] { TPerson.T_PERSON.FORENAME, TPerson.T_PERSON.SURNAME, TPerson.T_PERSON.BIRTHDATE, TPerson.T_PERSON.FK_CAMP }, true);
        public static Index T_PERSONDOCUMENT_FK_PERSON_NAME_KEY = createIndex("t_persondocument_fk_person_name_key", TPersondocument.T_PERSONDOCUMENT, new OrderField[] { TPersondocument.T_PERSONDOCUMENT.FK_PERSON, TPersondocument.T_PERSONDOCUMENT.NAME }, true);
        public static Index T_PERSONDOCUMENT_PKEY = createIndex("t_persondocument_pkey", TPersondocument.T_PERSONDOCUMENT, new OrderField[] { TPersondocument.T_PERSONDOCUMENT.PK }, true);
        public static Index T_PROFILE_PKEY = createIndex("t_profile_pkey", TProfile.T_PROFILE, new OrderField[] { TProfile.T_PROFILE.PK }, true);
        public static Index T_PROFILE_USERNAME_KEY = createIndex("t_profile_username_key", TProfile.T_PROFILE, new OrderField[] { TProfile.T_PROFILE.USERNAME }, true);
        public static Index T_PROFILE_UUID_KEY = createIndex("t_profile_uuid_key", TProfile.T_PROFILE, new OrderField[] { TProfile.T_PROFILE.UUID }, true);
        public static Index T_PROFILEROLE_FK_PROFILE_ROLE_KEY = createIndex("t_profilerole_fk_profile_role_key", TProfilerole.T_PROFILEROLE, new OrderField[] { TProfilerole.T_PROFILEROLE.FK_PROFILE, TProfilerole.T_PROFILEROLE.ROLE }, true);
        public static Index T_RSS_PKEY = createIndex("t_rss_pkey", TRss.T_RSS, new OrderField[] { TRss.T_RSS.PK }, true);
        public static Index T_SALES_PKEY = createIndex("t_sales_pkey", TSales.T_SALES, new OrderField[] { TSales.T_SALES.PK }, true);
        public static Index T_SALESCONTENT_FK_SALES_FK_SALESCONTENTTYPE_KEY = createIndex("t_salescontent_fk_sales_fk_salescontenttype_key", TSalescontent.T_SALESCONTENT, new OrderField[] { TSalescontent.T_SALESCONTENT.FK_SALES, TSalescontent.T_SALESCONTENT.FK_SALESCONTENTTYPE }, true);
        public static Index T_SALESCONTENTTYPE_PKEY = createIndex("t_salescontenttype_pkey", TSalescontenttype.T_SALESCONTENTTYPE, new OrderField[] { TSalescontenttype.T_SALESCONTENTTYPE.NAME }, true);
    }
}
