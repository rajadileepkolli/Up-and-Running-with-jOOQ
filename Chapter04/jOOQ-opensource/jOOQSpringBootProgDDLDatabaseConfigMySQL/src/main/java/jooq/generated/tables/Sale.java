/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import jooq.generated.Classicmodels;
import jooq.generated.Indexes;
import jooq.generated.Keys;
import jooq.generated.tables.records.SaleRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.4",
        "schema version:1.1"
    },
    date = "2020-10-19T13:27:12.864Z",
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sale extends TableImpl<SaleRecord> {

    private static final long serialVersionUID = -50339395;

    /**
     * The reference instance of <code>classicmodels.sale</code>
     */
    public static final Sale SALE = new Sale();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SaleRecord> getRecordType() {
        return SaleRecord.class;
    }

    /**
     * The column <code>classicmodels.sale.sale_id</code>.
     */
    public final TableField<SaleRecord, Long> SALE_ID = createField(DSL.name("sale_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>classicmodels.sale.fiscal_year</code>.
     */
    public final TableField<SaleRecord, Integer> FISCAL_YEAR = createField(DSL.name("fiscal_year"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>classicmodels.sale.sale</code>.
     */
    public final TableField<SaleRecord, Double> SALE_ = createField(DSL.name("sale"), org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>classicmodels.sale.employee_number</code>.
     */
    public final TableField<SaleRecord, Long> EMPLOYEE_NUMBER = createField(DSL.name("employee_number"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>classicmodels.sale</code> table reference
     */
    public Sale() {
        this(DSL.name("sale"), null);
    }

    /**
     * Create an aliased <code>classicmodels.sale</code> table reference
     */
    public Sale(String alias) {
        this(DSL.name(alias), SALE);
    }

    /**
     * Create an aliased <code>classicmodels.sale</code> table reference
     */
    public Sale(Name alias) {
        this(alias, SALE);
    }

    private Sale(Name alias, Table<SaleRecord> aliased) {
        this(alias, aliased, null);
    }

    private Sale(Name alias, Table<SaleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Sale(Table<O> child, ForeignKey<O, SaleRecord> key) {
        super(child, key, SALE);
    }

    @Override
    public Schema getSchema() {
        return Classicmodels.CLASSICMODELS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SALE_EMPLOYEE_NUMBER);
    }

    @Override
    public Identity<SaleRecord, Long> getIdentity() {
        return Keys.IDENTITY_SALE;
    }

    @Override
    public UniqueKey<SaleRecord> getPrimaryKey() {
        return Keys.KEY_SALE_PRIMARY;
    }

    @Override
    public List<UniqueKey<SaleRecord>> getKeys() {
        return Arrays.<UniqueKey<SaleRecord>>asList(Keys.KEY_SALE_PRIMARY);
    }

    @Override
    public List<ForeignKey<SaleRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SaleRecord, ?>>asList(Keys.SALES_IBFK_1);
    }

    public Employee employee() {
        return new Employee(this, Keys.SALES_IBFK_1);
    }

    @Override
    public Sale as(String alias) {
        return new Sale(DSL.name(alias), this);
    }

    @Override
    public Sale as(Name alias) {
        return new Sale(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Sale rename(String name) {
        return new Sale(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Sale rename(Name name) {
        return new Sale(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Integer, Double, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
