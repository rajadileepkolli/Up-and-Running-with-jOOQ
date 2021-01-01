/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.records;


import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jooq.generated.tables.Top3product;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.4",
        "schema version:1.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Top3productRecord extends UpdatableRecordImpl<Top3productRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>classicmodels.top3product.product_id</code>.
     */
    public void setProductId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>classicmodels.top3product.product_id</code>.
     */
    @NotNull
    public Long getProductId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>classicmodels.top3product.product_name</code>.
     */
    public void setProductName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>classicmodels.top3product.product_name</code>.
     */
    @Size(max = 70)
    public String getProductName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Top3product.TOP3PRODUCT.PRODUCT_ID;
    }

    @Override
    public Field<String> field2() {
        return Top3product.TOP3PRODUCT.PRODUCT_NAME;
    }

    @Override
    public Long component1() {
        return getProductId();
    }

    @Override
    public String component2() {
        return getProductName();
    }

    @Override
    public Long value1() {
        return getProductId();
    }

    @Override
    public String value2() {
        return getProductName();
    }

    @Override
    public Top3productRecord value1(Long value) {
        setProductId(value);
        return this;
    }

    @Override
    public Top3productRecord value2(String value) {
        setProductName(value);
        return this;
    }

    @Override
    public Top3productRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached Top3productRecord
     */
    public Top3productRecord() {
        super(Top3product.TOP3PRODUCT);
    }

    /**
     * Create a detached, initialised Top3productRecord
     */
    public Top3productRecord(Long productId, String productName) {
        super(Top3product.TOP3PRODUCT);

        setProductId(productId);
        setProductName(productName);
    }
}
