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
import de.jottyfan.camporganizer.db.jooq.tables.TSalesprofile;
import de.jottyfan.camporganizer.db.jooq.tables.VBudget;
import de.jottyfan.camporganizer.db.jooq.tables.VCamp;
import de.jottyfan.camporganizer.db.jooq.tables.VCamprole;
import de.jottyfan.camporganizer.db.jooq.tables.VProfile;
import de.jottyfan.camporganizer.db.jooq.tables.VRegistration;
import de.jottyfan.camporganizer.db.jooq.tables.VRole;
import de.jottyfan.camporganizer.db.jooq.tables.VSales;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in camp
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>camp.t_camp</code>.
     */
    public static final TCamp T_CAMP = de.jottyfan.camporganizer.db.jooq.tables.TCamp.T_CAMP;

    /**
     * The table <code>camp.t_document</code>.
     */
    public static final TDocument T_DOCUMENT = de.jottyfan.camporganizer.db.jooq.tables.TDocument.T_DOCUMENT;

    /**
     * The table <code>camp.t_location</code>.
     */
    public static final TLocation T_LOCATION = de.jottyfan.camporganizer.db.jooq.tables.TLocation.T_LOCATION;

    /**
     * The table <code>camp.t_person</code>.
     */
    public static final TPerson T_PERSON = de.jottyfan.camporganizer.db.jooq.tables.TPerson.T_PERSON;

    /**
     * The table <code>camp.t_persondocument</code>.
     */
    public static final TPersondocument T_PERSONDOCUMENT = de.jottyfan.camporganizer.db.jooq.tables.TPersondocument.T_PERSONDOCUMENT;

    /**
     * The table <code>camp.t_profile</code>.
     */
    public static final TProfile T_PROFILE = de.jottyfan.camporganizer.db.jooq.tables.TProfile.T_PROFILE;

    /**
     * The table <code>camp.t_profilerole</code>.
     */
    public static final TProfilerole T_PROFILEROLE = de.jottyfan.camporganizer.db.jooq.tables.TProfilerole.T_PROFILEROLE;

    /**
     * The table <code>camp.t_rss</code>.
     */
    public static final TRss T_RSS = de.jottyfan.camporganizer.db.jooq.tables.TRss.T_RSS;

    /**
     * The table <code>camp.t_sales</code>.
     */
    public static final TSales T_SALES = de.jottyfan.camporganizer.db.jooq.tables.TSales.T_SALES;

    /**
     * The table <code>camp.t_salescontent</code>.
     */
    public static final TSalescontent T_SALESCONTENT = de.jottyfan.camporganizer.db.jooq.tables.TSalescontent.T_SALESCONTENT;

    /**
     * The table <code>camp.t_salescontenttype</code>.
     */
    public static final TSalescontenttype T_SALESCONTENTTYPE = de.jottyfan.camporganizer.db.jooq.tables.TSalescontenttype.T_SALESCONTENTTYPE;

    /**
     * The table <code>camp.t_salesprofile</code>.
     */
    public static final TSalesprofile T_SALESPROFILE = de.jottyfan.camporganizer.db.jooq.tables.TSalesprofile.T_SALESPROFILE;

    /**
     * The table <code>camp.v_budget</code>.
     */
    public static final VBudget V_BUDGET = de.jottyfan.camporganizer.db.jooq.tables.VBudget.V_BUDGET;

    /**
     * The table <code>camp.v_camp</code>.
     */
    public static final VCamp V_CAMP = de.jottyfan.camporganizer.db.jooq.tables.VCamp.V_CAMP;

    /**
     * The table <code>camp.v_camprole</code>.
     */
    public static final VCamprole V_CAMPROLE = de.jottyfan.camporganizer.db.jooq.tables.VCamprole.V_CAMPROLE;

    /**
     * The table <code>camp.v_profile</code>.
     */
    public static final VProfile V_PROFILE = de.jottyfan.camporganizer.db.jooq.tables.VProfile.V_PROFILE;

    /**
     * The table <code>camp.v_registration</code>.
     */
    public static final VRegistration V_REGISTRATION = de.jottyfan.camporganizer.db.jooq.tables.VRegistration.V_REGISTRATION;

    /**
     * The table <code>camp.v_role</code>.
     */
    public static final VRole V_ROLE = de.jottyfan.camporganizer.db.jooq.tables.VRole.V_ROLE;

    /**
     * The table <code>camp.v_sales</code>.
     */
    public static final VSales V_SALES = de.jottyfan.camporganizer.db.jooq.tables.VSales.V_SALES;
}
