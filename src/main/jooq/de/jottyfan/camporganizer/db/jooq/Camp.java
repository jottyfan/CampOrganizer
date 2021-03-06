/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq;


import de.jottyfan.camporganizer.db.jooq.tables.TCamp;
import de.jottyfan.camporganizer.db.jooq.tables.TCampdocument;
import de.jottyfan.camporganizer.db.jooq.tables.TCampprofile;
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
import de.jottyfan.camporganizer.db.jooq.tables.VCampdocument;
import de.jottyfan.camporganizer.db.jooq.tables.VCamprole;
import de.jottyfan.camporganizer.db.jooq.tables.VProfile;
import de.jottyfan.camporganizer.db.jooq.tables.VRegistration;
import de.jottyfan.camporganizer.db.jooq.tables.VRole;
import de.jottyfan.camporganizer.db.jooq.tables.VSales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Camp extends SchemaImpl {

    private static final long serialVersionUID = 600557266;

    /**
     * The reference instance of <code>camp</code>
     */
    public static final Camp CAMP = new Camp();

    /**
     * The table <code>camp.t_camp</code>.
     */
    public final TCamp T_CAMP = de.jottyfan.camporganizer.db.jooq.tables.TCamp.T_CAMP;

    /**
     * The table <code>camp.t_campdocument</code>.
     */
    public final TCampdocument T_CAMPDOCUMENT = de.jottyfan.camporganizer.db.jooq.tables.TCampdocument.T_CAMPDOCUMENT;

    /**
     * The table <code>camp.t_campprofile</code>.
     */
    public final TCampprofile T_CAMPPROFILE = de.jottyfan.camporganizer.db.jooq.tables.TCampprofile.T_CAMPPROFILE;

    /**
     * The table <code>camp.t_document</code>.
     */
    public final TDocument T_DOCUMENT = de.jottyfan.camporganizer.db.jooq.tables.TDocument.T_DOCUMENT;

    /**
     * The table <code>camp.t_location</code>.
     */
    public final TLocation T_LOCATION = de.jottyfan.camporganizer.db.jooq.tables.TLocation.T_LOCATION;

    /**
     * The table <code>camp.t_person</code>.
     */
    public final TPerson T_PERSON = de.jottyfan.camporganizer.db.jooq.tables.TPerson.T_PERSON;

    /**
     * The table <code>camp.t_persondocument</code>.
     */
    public final TPersondocument T_PERSONDOCUMENT = de.jottyfan.camporganizer.db.jooq.tables.TPersondocument.T_PERSONDOCUMENT;

    /**
     * The table <code>camp.t_profile</code>.
     */
    public final TProfile T_PROFILE = de.jottyfan.camporganizer.db.jooq.tables.TProfile.T_PROFILE;

    /**
     * The table <code>camp.t_profilerole</code>.
     */
    public final TProfilerole T_PROFILEROLE = de.jottyfan.camporganizer.db.jooq.tables.TProfilerole.T_PROFILEROLE;

    /**
     * The table <code>camp.t_rss</code>.
     */
    public final TRss T_RSS = de.jottyfan.camporganizer.db.jooq.tables.TRss.T_RSS;

    /**
     * The table <code>camp.t_sales</code>.
     */
    public final TSales T_SALES = de.jottyfan.camporganizer.db.jooq.tables.TSales.T_SALES;

    /**
     * The table <code>camp.t_salescontent</code>.
     */
    public final TSalescontent T_SALESCONTENT = de.jottyfan.camporganizer.db.jooq.tables.TSalescontent.T_SALESCONTENT;

    /**
     * The table <code>camp.t_salescontenttype</code>.
     */
    public final TSalescontenttype T_SALESCONTENTTYPE = de.jottyfan.camporganizer.db.jooq.tables.TSalescontenttype.T_SALESCONTENTTYPE;

    /**
     * The table <code>camp.t_salesprofile</code>.
     */
    public final TSalesprofile T_SALESPROFILE = de.jottyfan.camporganizer.db.jooq.tables.TSalesprofile.T_SALESPROFILE;

    /**
     * The table <code>camp.v_budget</code>.
     */
    public final VBudget V_BUDGET = de.jottyfan.camporganizer.db.jooq.tables.VBudget.V_BUDGET;

    /**
     * The table <code>camp.v_camp</code>.
     */
    public final VCamp V_CAMP = de.jottyfan.camporganizer.db.jooq.tables.VCamp.V_CAMP;

    /**
     * The table <code>camp.v_campdocument</code>.
     */
    public final VCampdocument V_CAMPDOCUMENT = de.jottyfan.camporganizer.db.jooq.tables.VCampdocument.V_CAMPDOCUMENT;

    /**
     * The table <code>camp.v_camprole</code>.
     */
    public final VCamprole V_CAMPROLE = de.jottyfan.camporganizer.db.jooq.tables.VCamprole.V_CAMPROLE;

    /**
     * The table <code>camp.v_profile</code>.
     */
    public final VProfile V_PROFILE = de.jottyfan.camporganizer.db.jooq.tables.VProfile.V_PROFILE;

    /**
     * The table <code>camp.v_registration</code>.
     */
    public final VRegistration V_REGISTRATION = de.jottyfan.camporganizer.db.jooq.tables.VRegistration.V_REGISTRATION;

    /**
     * The table <code>camp.v_role</code>.
     */
    public final VRole V_ROLE = de.jottyfan.camporganizer.db.jooq.tables.VRole.V_ROLE;

    /**
     * The table <code>camp.v_sales</code>.
     */
    public final VSales V_SALES = de.jottyfan.camporganizer.db.jooq.tables.VSales.V_SALES;

    /**
     * No further instances allowed
     */
    private Camp() {
        super("camp", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            TCamp.T_CAMP,
            TCampdocument.T_CAMPDOCUMENT,
            TCampprofile.T_CAMPPROFILE,
            TDocument.T_DOCUMENT,
            TLocation.T_LOCATION,
            TPerson.T_PERSON,
            TPersondocument.T_PERSONDOCUMENT,
            TProfile.T_PROFILE,
            TProfilerole.T_PROFILEROLE,
            TRss.T_RSS,
            TSales.T_SALES,
            TSalescontent.T_SALESCONTENT,
            TSalescontenttype.T_SALESCONTENTTYPE,
            TSalesprofile.T_SALESPROFILE,
            VBudget.V_BUDGET,
            VCamp.V_CAMP,
            VCampdocument.V_CAMPDOCUMENT,
            VCamprole.V_CAMPROLE,
            VProfile.V_PROFILE,
            VRegistration.V_REGISTRATION,
            VRole.V_ROLE,
            VSales.V_SALES);
    }
}
