/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.tables.TCamp;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.TableRecordImpl;


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
public class TCampRecord extends TableRecordImpl<TCampRecord> implements Record12<Integer, String, Timestamp, Timestamp, Integer, Integer, Integer, String, String, Integer, Boolean, Integer> {

    private static final long serialVersionUID = 536746246;

    /**
     * Setter for <code>camp.t_camp.pk</code>.
     */
    public void setPk(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_camp.pk</code>.
     */
    public Integer getPk() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.t_camp.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_camp.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>camp.t_camp.arrive</code>.
     */
    public void setArrive(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>camp.t_camp.arrive</code>.
     */
    public Timestamp getArrive() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>camp.t_camp.depart</code>.
     */
    public void setDepart(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>camp.t_camp.depart</code>.
     */
    public Timestamp getDepart() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>camp.t_camp.fk_location</code>.
     */
    public void setFkLocation(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>camp.t_camp.fk_location</code>.
     */
    public Integer getFkLocation() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>camp.t_camp.min_age</code>.
     */
    public void setMinAge(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>camp.t_camp.min_age</code>.
     */
    public Integer getMinAge() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>camp.t_camp.max_age</code>.
     */
    public void setMaxAge(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>camp.t_camp.max_age</code>.
     */
    public Integer getMaxAge() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>camp.t_camp.price</code>.
     */
    public void setPrice(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>camp.t_camp.price</code>.
     */
    public String getPrice() {
        return (String) get(7);
    }

    /**
     * Setter for <code>camp.t_camp.countries</code>.
     */
    public void setCountries(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>camp.t_camp.countries</code>.
     */
    public String getCountries() {
        return (String) get(8);
    }

    /**
     * Setter for <code>camp.t_camp.fk_document</code>.
     */
    public void setFkDocument(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>camp.t_camp.fk_document</code>.
     */
    public Integer getFkDocument() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>camp.t_camp.lock_sales</code>.
     */
    public void setLockSales(Boolean value) {
        set(10, value);
    }

    /**
     * Getter for <code>camp.t_camp.lock_sales</code>.
     */
    public Boolean getLockSales() {
        return (Boolean) get(10);
    }

    /**
     * Setter for <code>camp.t_camp.fk_profile</code>.
     */
    public void setFkProfile(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>camp.t_camp.fk_profile</code>.
     */
    public Integer getFkProfile() {
        return (Integer) get(11);
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<Integer, String, Timestamp, Timestamp, Integer, Integer, Integer, String, String, Integer, Boolean, Integer> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<Integer, String, Timestamp, Timestamp, Integer, Integer, Integer, String, String, Integer, Boolean, Integer> valuesRow() {
        return (Row12) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TCamp.T_CAMP.PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TCamp.T_CAMP.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return TCamp.T_CAMP.ARRIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return TCamp.T_CAMP.DEPART;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return TCamp.T_CAMP.FK_LOCATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return TCamp.T_CAMP.MIN_AGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return TCamp.T_CAMP.MAX_AGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return TCamp.T_CAMP.PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return TCamp.T_CAMP.COUNTRIES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field10() {
        return TCamp.T_CAMP.FK_DOCUMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field11() {
        return TCamp.T_CAMP.LOCK_SALES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field12() {
        return TCamp.T_CAMP.FK_PROFILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getPk();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getArrive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component4() {
        return getDepart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getFkLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getMinAge();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component7() {
        return getMaxAge();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getCountries();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component10() {
        return getFkDocument();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component11() {
        return getLockSales();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component12() {
        return getFkProfile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getPk();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getArrive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getDepart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getFkLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getMinAge();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getMaxAge();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getCountries();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value10() {
        return getFkDocument();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value11() {
        return getLockSales();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value12() {
        return getFkProfile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value1(Integer value) {
        setPk(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value3(Timestamp value) {
        setArrive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value4(Timestamp value) {
        setDepart(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value5(Integer value) {
        setFkLocation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value6(Integer value) {
        setMinAge(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value7(Integer value) {
        setMaxAge(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value8(String value) {
        setPrice(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value9(String value) {
        setCountries(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value10(Integer value) {
        setFkDocument(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value11(Boolean value) {
        setLockSales(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord value12(Integer value) {
        setFkProfile(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TCampRecord values(Integer value1, String value2, Timestamp value3, Timestamp value4, Integer value5, Integer value6, Integer value7, String value8, String value9, Integer value10, Boolean value11, Integer value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TCampRecord
     */
    public TCampRecord() {
        super(TCamp.T_CAMP);
    }

    /**
     * Create a detached, initialised TCampRecord
     */
    public TCampRecord(Integer pk, String name, Timestamp arrive, Timestamp depart, Integer fkLocation, Integer minAge, Integer maxAge, String price, String countries, Integer fkDocument, Boolean lockSales, Integer fkProfile) {
        super(TCamp.T_CAMP);

        set(0, pk);
        set(1, name);
        set(2, arrive);
        set(3, depart);
        set(4, fkLocation);
        set(5, minAge);
        set(6, maxAge);
        set(7, price);
        set(8, countries);
        set(9, fkDocument);
        set(10, lockSales);
        set(11, fkProfile);
    }
}
