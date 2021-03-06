/*
 * This file is generated by jOOQ.
 */
package my.mpp.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import my.mpp.jooq.Indexes;
import my.mpp.jooq.JPublic;
import my.mpp.jooq.Keys;
import my.mpp.jooq.tables.records.JCustomersRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
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
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JCustomers extends TableImpl<JCustomersRecord> {

    private static final long serialVersionUID = -1847983290;

    /**
     * The reference instance of <code>public.customers</code>
     */
    public static final JCustomers CUSTOMERS = new JCustomers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JCustomersRecord> getRecordType() {
        return JCustomersRecord.class;
    }

    /**
     * The column <code>public.customers.id</code>.
     */
    public final TableField<JCustomersRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('customers_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.customers.name</code>.
     */
    public final TableField<JCustomersRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * Create a <code>public.customers</code> table reference
     */
    public JCustomers() {
        this(DSL.name("customers"), null);
    }

    /**
     * Create an aliased <code>public.customers</code> table reference
     */
    public JCustomers(String alias) {
        this(DSL.name(alias), CUSTOMERS);
    }

    /**
     * Create an aliased <code>public.customers</code> table reference
     */
    public JCustomers(Name alias) {
        this(alias, CUSTOMERS);
    }

    private JCustomers(Name alias, Table<JCustomersRecord> aliased) {
        this(alias, aliased, null);
    }

    private JCustomers(Name alias, Table<JCustomersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> JCustomers(Table<O> child, ForeignKey<O, JCustomersRecord> key) {
        super(child, key, CUSTOMERS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return JPublic.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CUSTOMERS_ID_UINDEX, Indexes.CUSTOMERS_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<JCustomersRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CUSTOMERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<JCustomersRecord> getPrimaryKey() {
        return Keys.CUSTOMERS_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<JCustomersRecord>> getKeys() {
        return Arrays.<UniqueKey<JCustomersRecord>>asList(Keys.CUSTOMERS_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JCustomers as(String alias) {
        return new JCustomers(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JCustomers as(Name alias) {
        return new JCustomers(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JCustomers rename(String name) {
        return new JCustomers(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JCustomers rename(Name name) {
        return new JCustomers(name, null);
    }
}
