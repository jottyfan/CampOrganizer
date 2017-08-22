/*
 * This file is generated by jOOQ.
*/
package de.jottyfan.camporganizer.db.jooq.tables.records;


import de.jottyfan.camporganizer.db.jooq.enums.EnumCamprole;
import de.jottyfan.camporganizer.db.jooq.tables.TPerson;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


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
public class TPersonRecord extends UpdatableRecordImpl<TPersonRecord> implements Record11<Integer, String, String, String, String, String, String, Date, EnumCamprole, String, Integer> {

    private static final long serialVersionUID = 838093095;

    /**
     * Setter for <code>camp.t_person.pk</code>.
     */
    public void setPk(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>camp.t_person.pk</code>.
     */
    public Integer getPk() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>camp.t_person.forename</code>.
     */
    public void setForename(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>camp.t_person.forename</code>.
     */
    public String getForename() {
        return (String) get(1);
    }

    /**
     * Setter for <code>camp.t_person.surname</code>.
     */
    public void setSurname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>camp.t_person.surname</code>.
     */
    public String getSurname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>camp.t_person.street</code>.
     */
    public void setStreet(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>camp.t_person.street</code>.
     */
    public String getStreet() {
        return (String) get(3);
    }

    /**
     * Setter for <code>camp.t_person.zip</code>.
     */
    public void setZip(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>camp.t_person.zip</code>.
     */
    public String getZip() {
        return (String) get(4);
    }

    /**
     * Setter for <code>camp.t_person.city</code>.
     */
    public void setCity(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>camp.t_person.city</code>.
     */
    public String getCity() {
        return (String) get(5);
    }

    /**
     * Setter for <code>camp.t_person.phone</code>.
     */
    public void setPhone(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>camp.t_person.phone</code>.
     */
    public String getPhone() {
        return (String) get(6);
    }

    /**
     * Setter for <code>camp.t_person.birthdate</code>.
     */
    public void setBirthdate(Date value) {
        set(7, value);
    }

    /**
     * Getter for <code>camp.t_person.birthdate</code>.
     */
    public Date getBirthdate() {
        return (Date) get(7);
    }

    /**
     * Setter for <code>camp.t_person.camprole</code>.
     */
    public void setCamprole(EnumCamprole value) {
        set(8, value);
    }

    /**
     * Getter for <code>camp.t_person.camprole</code>.
     */
    public EnumCamprole getCamprole() {
        return (EnumCamprole) get(8);
    }

    /**
     * Setter for <code>camp.t_person.email</code>.
     */
    public void setEmail(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>camp.t_person.email</code>.
     */
    public String getEmail() {
        return (String) get(9);
    }

    /**
     * Setter for <code>camp.t_person.fk_camp</code>.
     */
    public void setFkCamp(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>camp.t_person.fk_camp</code>.
     */
    public Integer getFkCamp() {
        return (Integer) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<Integer, String, String, String, String, String, String, Date, EnumCamprole, String, Integer> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<Integer, String, String, String, String, String, String, Date, EnumCamprole, String, Integer> valuesRow() {
        return (Row11) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TPerson.T_PERSON.PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TPerson.T_PERSON.FORENAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TPerson.T_PERSON.SURNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TPerson.T_PERSON.STREET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return TPerson.T_PERSON.ZIP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TPerson.T_PERSON.CITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return TPerson.T_PERSON.PHONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field8() {
        return TPerson.T_PERSON.BIRTHDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<EnumCamprole> field9() {
        return TPerson.T_PERSON.CAMPROLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return TPerson.T_PERSON.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field11() {
        return TPerson.T_PERSON.FK_CAMP;
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
        return getForename();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSurname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getStreet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getZip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getCity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getPhone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value8() {
        return getBirthdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumCamprole value9() {
        return getCamprole();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value11() {
        return getFkCamp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value1(Integer value) {
        setPk(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value2(String value) {
        setForename(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value3(String value) {
        setSurname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value4(String value) {
        setStreet(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value5(String value) {
        setZip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value6(String value) {
        setCity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value7(String value) {
        setPhone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value8(Date value) {
        setBirthdate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value9(EnumCamprole value) {
        setCamprole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value10(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord value11(Integer value) {
        setFkCamp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPersonRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, String value7, Date value8, EnumCamprole value9, String value10, Integer value11) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TPersonRecord
     */
    public TPersonRecord() {
        super(TPerson.T_PERSON);
    }

    /**
     * Create a detached, initialised TPersonRecord
     */
    public TPersonRecord(Integer pk, String forename, String surname, String street, String zip, String city, String phone, Date birthdate, EnumCamprole camprole, String email, Integer fkCamp) {
        super(TPerson.T_PERSON);

        set(0, pk);
        set(1, forename);
        set(2, surname);
        set(3, street);
        set(4, zip);
        set(5, city);
        set(6, phone);
        set(7, birthdate);
        set(8, camprole);
        set(9, email);
        set(10, fkCamp);
    }
}