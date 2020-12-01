package com.classicmodels.repository;

import static jooq.generated.tables.Customer.CUSTOMER;
import static jooq.generated.tables.Customerdetail.CUSTOMERDETAIL;
import static jooq.generated.tables.Employee.EMPLOYEE;
import static jooq.generated.tables.Office.OFFICE;
import static jooq.generated.tables.Orderdetail.ORDERDETAIL;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.val;
import org.springframework.stereotype.Repository;

@Repository
public class ClassicModelsRepository {

    private final DSLContext ctx;

    public ClassicModelsRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    // EXAMPLE 1
    /*
    select 
      [classicmodels].[dbo].[employee].[first_name], 
      [classicmodels].[dbo].[employee].[last_name] 
    from 
      [classicmodels].[dbo].[employee] 
    union 
    select 
      [classicmodels].[dbo].[customer].[contact_first_name], 
      [classicmodels].[dbo].[customer].[contact_last_name] 
    from 
      [classicmodels].[dbo].[customer]
     */
    public void unionEmployeeAndCustomerNames() {

        System.out.println("EXAMPLE 1\n"
                + ctx.select(EMPLOYEE.FIRST_NAME, EMPLOYEE.LAST_NAME)
                        .from(EMPLOYEE)
                        .union(select(CUSTOMER.CONTACT_FIRST_NAME, CUSTOMER.CONTACT_LAST_NAME)
                                .from(CUSTOMER))
                        .fetch()
        );
    }

    // EXAMPLE 2
    /*
    (
      [classicmodels].[dbo].[employee].[first_name] + ? 
         + [classicmodels].[dbo].[employee].[last_name]
    ) [full_name] 
    from 
      [classicmodels].[dbo].[employee] 
    union 
    select 
      (
        [classicmodels].[dbo].[customer].[contact_first_name] + ? 
           + [classicmodels].[dbo].[customer].[contact_last_name]
      ) 
    from 
      [classicmodels].[dbo].[customer]    
     */
    public void unionEmployeeAndCustomerNamesConcatColumns() {

        System.out.println("EXAMPLE 2\n"
                + ctx.select(
                        concat(EMPLOYEE.FIRST_NAME, val(" "), EMPLOYEE.LAST_NAME).as("full_name"))
                        .from(EMPLOYEE)
                        .union(select(
                                concat(CUSTOMER.CONTACT_FIRST_NAME, val(" "), CUSTOMER.CONTACT_LAST_NAME))
                                .from(CUSTOMER))
                        .fetch()
        );
    }

    // EXAMPLE 3
    /*
    select 
      (
        [classicmodels].[dbo].[employee].[first_name] + ? 
          + [classicmodels].[dbo].[employee].[last_name]
      ) [full_name], 
      ? [contactType] 
    from 
      [classicmodels].[dbo].[employee] 
    union 
    select 
      (
        [classicmodels].[dbo].[customer].[contact_first_name] + ? 
          + [classicmodels].[dbo].[customer].[contact_last_name]
      ), 
      ? [contactType] 
    from 
      [classicmodels].[dbo].[customer]    
     */
    public void unionEmployeeAndCustomerNamesDifferentiate() {

        System.out.println("EXAMPLE 3\n"
                + ctx.select(
                        concat(EMPLOYEE.FIRST_NAME, val(" "),
                                EMPLOYEE.LAST_NAME).as("full_name"),
                        val("Employee").as("contactType"))
                        .from(EMPLOYEE)
                        .union(select(
                                concat(CUSTOMER.CONTACT_FIRST_NAME, val(" "),
                                        CUSTOMER.CONTACT_LAST_NAME),
                                val("Customer").as("contactType"))
                                .from(CUSTOMER))
                        .fetch()
        );
    }

    // EXAMPLE 4
    /*
    select 
      top 100 percent (
        [classicmodels].[dbo].[employee].[first_name] + ? 
          + [classicmodels].[dbo].[employee].[last_name]
      ) [full_name] 
    from 
      [classicmodels].[dbo].[employee] 
    union 
    select 
      (
        [classicmodels].[dbo].[customer].[contact_first_name] + ? 
          + [classicmodels].[dbo].[customer].[contact_last_name]
      ) 
    from 
      [classicmodels].[dbo].[customer] 
    order by 
      full_name   
     */
    public void unionEmployeeAndCustomerNamesOrderBy() {

        System.out.println("EXAMPLE 4\n"
                + ctx.select(
                        concat(EMPLOYEE.FIRST_NAME, val(" "), EMPLOYEE.LAST_NAME).as("full_name"))
                        .from(EMPLOYEE)
                        .union(select(
                                concat(CUSTOMER.CONTACT_FIRST_NAME, val(" "), CUSTOMER.CONTACT_LAST_NAME))
                                .from(CUSTOMER))
                        .orderBy(field("full_name"))
                        .fetch()
        );
    }

    // EXAMPLE 5
    /*
    select 
      top 100 percent [classicmodels].[dbo].[office].[city], 
      [classicmodels].[dbo].[office].[country] 
    from 
      [classicmodels].[dbo].[office] 
    union all 
    select 
      [classicmodels].[dbo].[customerdetail].[city], 
      [classicmodels].[dbo].[customerdetail].[country] 
    from 
      [classicmodels].[dbo].[customerdetail] 
    order by 
      [city], 
      [country]   
     */
    public void unionAllOfficeCustomerCityAndCountry() {

        System.out.println("EXAMPLE 5\n"
                + ctx.select(OFFICE.CITY, OFFICE.COUNTRY)
                        .from(OFFICE)
                        .unionAll(select(CUSTOMERDETAIL.CITY, CUSTOMERDETAIL.COUNTRY)
                                .from(CUSTOMERDETAIL))
                        .orderBy(OFFICE.CITY, OFFICE.COUNTRY)
                        .fetch()
        );
    }
    
    // EXAMPLE 6
    /*
    select [alias_34165135].[order_id],
           [alias_34165135].[price_each],
           [alias_34165135].[quantity_ordered]
    from
      (select [classicmodels].[dbo].[orderdetail].[order_id],
              [classicmodels].[dbo].[orderdetail].[price_each],
              [classicmodels].[dbo].[orderdetail].[quantity_ordered]
       from [classicmodels].[dbo].[orderdetail]
       where [classicmodels].[dbo].[orderdetail].[quantity_ordered] <= ?
       union select [classicmodels].[dbo].[orderdetail].[order_id],
                    [classicmodels].[dbo].[orderdetail].[price_each],
                    [classicmodels].[dbo].[orderdetail].[quantity_ordered]
       from [classicmodels].[dbo].[orderdetail]
       where [classicmodels].[dbo].[orderdetail].[quantity_ordered] >= ?) [alias_34165135]    
    */
    public void findAllOrdersHavingQuantityOrderedLe20AndGe60() {

        System.out.println("EXAMPLE 6\n"
                + ctx.select().from(
                        select(ORDERDETAIL.ORDER_ID, ORDERDETAIL.PRICE_EACH, ORDERDETAIL.QUANTITY_ORDERED)
                                .from(ORDERDETAIL)
                                .where(ORDERDETAIL.QUANTITY_ORDERED.le(20))                                
                                .union(
                                        select(ORDERDETAIL.ORDER_ID, ORDERDETAIL.PRICE_EACH, ORDERDETAIL.QUANTITY_ORDERED)
                                                .from(ORDERDETAIL)
                                                .where(ORDERDETAIL.QUANTITY_ORDERED.ge(60))                                               
                                )
                )
                        .fetch()
        );
    }
}