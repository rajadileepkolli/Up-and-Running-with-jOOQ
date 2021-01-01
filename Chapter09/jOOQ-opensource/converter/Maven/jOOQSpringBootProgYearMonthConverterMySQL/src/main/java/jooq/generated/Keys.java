/*
 * This file is generated by jOOQ.
 */
package jooq.generated;


import javax.annotation.processing.Generated;

import jooq.generated.tables.BankTransaction;
import jooq.generated.tables.Customer;
import jooq.generated.tables.Customerdetail;
import jooq.generated.tables.Department;
import jooq.generated.tables.Employee;
import jooq.generated.tables.Manager;
import jooq.generated.tables.Office;
import jooq.generated.tables.OfficeHasManager;
import jooq.generated.tables.Order;
import jooq.generated.tables.Orderdetail;
import jooq.generated.tables.Payment;
import jooq.generated.tables.Product;
import jooq.generated.tables.Productline;
import jooq.generated.tables.Productlinedetail;
import jooq.generated.tables.Sale;
import jooq.generated.tables.Top3product;
import jooq.generated.tables.records.BankTransactionRecord;
import jooq.generated.tables.records.CustomerRecord;
import jooq.generated.tables.records.CustomerdetailRecord;
import jooq.generated.tables.records.DepartmentRecord;
import jooq.generated.tables.records.EmployeeRecord;
import jooq.generated.tables.records.ManagerRecord;
import jooq.generated.tables.records.OfficeHasManagerRecord;
import jooq.generated.tables.records.OfficeRecord;
import jooq.generated.tables.records.OrderRecord;
import jooq.generated.tables.records.OrderdetailRecord;
import jooq.generated.tables.records.PaymentRecord;
import jooq.generated.tables.records.ProductRecord;
import jooq.generated.tables.records.ProductlineRecord;
import jooq.generated.tables.records.ProductlinedetailRecord;
import jooq.generated.tables.records.SaleRecord;
import jooq.generated.tables.records.Top3productRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * classicmodels.
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
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<BankTransactionRecord> KEY_BANK_TRANSACTION_PRIMARY = Internal.createUniqueKey(BankTransaction.BANK_TRANSACTION, DSL.name("KEY_bank_transaction_PRIMARY"), new TableField[] { BankTransaction.BANK_TRANSACTION.TRANSACTION_ID }, true);
    public static final UniqueKey<CustomerRecord> KEY_CUSTOMER_PRIMARY = Internal.createUniqueKey(Customer.CUSTOMER, DSL.name("KEY_customer_PRIMARY"), new TableField[] { Customer.CUSTOMER.CUSTOMER_NUMBER }, true);
    public static final UniqueKey<CustomerdetailRecord> KEY_CUSTOMERDETAIL_PRIMARY = Internal.createUniqueKey(Customerdetail.CUSTOMERDETAIL, DSL.name("KEY_customerdetail_PRIMARY"), new TableField[] { Customerdetail.CUSTOMERDETAIL.CUSTOMER_NUMBER }, true);
    public static final UniqueKey<DepartmentRecord> KEY_DEPARTMENT_PRIMARY = Internal.createUniqueKey(Department.DEPARTMENT, DSL.name("KEY_department_PRIMARY"), new TableField[] { Department.DEPARTMENT.DEPARTMENT_ID }, true);
    public static final UniqueKey<EmployeeRecord> KEY_EMPLOYEE_PRIMARY = Internal.createUniqueKey(Employee.EMPLOYEE, DSL.name("KEY_employee_PRIMARY"), new TableField[] { Employee.EMPLOYEE.EMPLOYEE_NUMBER }, true);
    public static final UniqueKey<ManagerRecord> KEY_MANAGER_PRIMARY = Internal.createUniqueKey(Manager.MANAGER, DSL.name("KEY_manager_PRIMARY"), new TableField[] { Manager.MANAGER.MANAGER_ID }, true);
    public static final UniqueKey<OfficeRecord> KEY_OFFICE_PRIMARY = Internal.createUniqueKey(Office.OFFICE, DSL.name("KEY_office_PRIMARY"), new TableField[] { Office.OFFICE.OFFICE_CODE }, true);
    public static final UniqueKey<OfficeHasManagerRecord> KEY_OFFICE_HAS_MANAGER_PRIMARY = Internal.createUniqueKey(OfficeHasManager.OFFICE_HAS_MANAGER, DSL.name("KEY_office_has_manager_PRIMARY"), new TableField[] { OfficeHasManager.OFFICE_HAS_MANAGER.OFFICES_OFFICE_CODE, OfficeHasManager.OFFICE_HAS_MANAGER.MANAGERS_MANAGER_ID }, true);
    public static final UniqueKey<OrderRecord> KEY_ORDER_PRIMARY = Internal.createUniqueKey(Order.ORDER, DSL.name("KEY_order_PRIMARY"), new TableField[] { Order.ORDER.ORDER_ID }, true);
    public static final UniqueKey<OrderdetailRecord> KEY_ORDERDETAIL_PRIMARY = Internal.createUniqueKey(Orderdetail.ORDERDETAIL, DSL.name("KEY_orderdetail_PRIMARY"), new TableField[] { Orderdetail.ORDERDETAIL.ORDER_ID, Orderdetail.ORDERDETAIL.PRODUCT_ID }, true);
    public static final UniqueKey<PaymentRecord> KEY_PAYMENT_PRIMARY = Internal.createUniqueKey(Payment.PAYMENT, DSL.name("KEY_payment_PRIMARY"), new TableField[] { Payment.PAYMENT.CUSTOMER_NUMBER, Payment.PAYMENT.CHECK_NUMBER }, true);
    public static final UniqueKey<PaymentRecord> KEY_PAYMENT_UNIQUE_CHECK_NUMBER = Internal.createUniqueKey(Payment.PAYMENT, DSL.name("KEY_payment_unique_check_number"), new TableField[] { Payment.PAYMENT.CHECK_NUMBER }, true);
    public static final UniqueKey<ProductRecord> KEY_PRODUCT_PRIMARY = Internal.createUniqueKey(Product.PRODUCT, DSL.name("KEY_product_PRIMARY"), new TableField[] { Product.PRODUCT.PRODUCT_ID }, true);
    public static final UniqueKey<ProductlineRecord> KEY_PRODUCTLINE_PRIMARY = Internal.createUniqueKey(Productline.PRODUCTLINE, DSL.name("KEY_productline_PRIMARY"), new TableField[] { Productline.PRODUCTLINE.PRODUCT_LINE, Productline.PRODUCTLINE.CODE }, true);
    public static final UniqueKey<ProductlineRecord> KEY_PRODUCTLINE_UNIQUE_PRODUCT_LINE = Internal.createUniqueKey(Productline.PRODUCTLINE, DSL.name("KEY_productline_unique_product_line"), new TableField[] { Productline.PRODUCTLINE.PRODUCT_LINE }, true);
    public static final UniqueKey<ProductlinedetailRecord> KEY_PRODUCTLINEDETAIL_PRIMARY = Internal.createUniqueKey(Productlinedetail.PRODUCTLINEDETAIL, DSL.name("KEY_productlinedetail_PRIMARY"), new TableField[] { Productlinedetail.PRODUCTLINEDETAIL.PRODUCT_LINE, Productlinedetail.PRODUCTLINEDETAIL.CODE }, true);
    public static final UniqueKey<ProductlinedetailRecord> KEY_PRODUCTLINEDETAIL_UNIQUE_PRODUCT_LINE_DETAIL = Internal.createUniqueKey(Productlinedetail.PRODUCTLINEDETAIL, DSL.name("KEY_productlinedetail_unique_product_line_detail"), new TableField[] { Productlinedetail.PRODUCTLINEDETAIL.PRODUCT_LINE }, true);
    public static final UniqueKey<SaleRecord> KEY_SALE_PRIMARY = Internal.createUniqueKey(Sale.SALE, DSL.name("KEY_sale_PRIMARY"), new TableField[] { Sale.SALE.SALE_ID }, true);
    public static final UniqueKey<Top3productRecord> KEY_TOP3PRODUCT_PRIMARY = Internal.createUniqueKey(Top3product.TOP3PRODUCT, DSL.name("KEY_top3product_PRIMARY"), new TableField[] { Top3product.TOP3PRODUCT.PRODUCT_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<BankTransactionRecord, PaymentRecord> BANK_TRANSACTION_IBFK_1 = Internal.createForeignKey(BankTransaction.BANK_TRANSACTION, DSL.name("bank_transaction_ibfk_1"), new TableField[] { BankTransaction.BANK_TRANSACTION.CUSTOMER_NUMBER, BankTransaction.BANK_TRANSACTION.CHECK_NUMBER }, Keys.KEY_PAYMENT_PRIMARY, new TableField[] { Payment.PAYMENT.CUSTOMER_NUMBER, Payment.PAYMENT.CHECK_NUMBER }, true);
    public static final ForeignKey<CustomerRecord, EmployeeRecord> CUSTOMERS_IBFK_1 = Internal.createForeignKey(Customer.CUSTOMER, DSL.name("customers_ibfk_1"), new TableField[] { Customer.CUSTOMER.SALES_REP_EMPLOYEE_NUMBER }, Keys.KEY_EMPLOYEE_PRIMARY, new TableField[] { Employee.EMPLOYEE.EMPLOYEE_NUMBER }, true);
    public static final ForeignKey<CustomerdetailRecord, CustomerRecord> CUSTOMERS_DETAILS_IBFK_1 = Internal.createForeignKey(Customerdetail.CUSTOMERDETAIL, DSL.name("customers_details_ibfk_1"), new TableField[] { Customerdetail.CUSTOMERDETAIL.CUSTOMER_NUMBER }, Keys.KEY_CUSTOMER_PRIMARY, new TableField[] { Customer.CUSTOMER.CUSTOMER_NUMBER }, true);
    public static final ForeignKey<DepartmentRecord, OfficeRecord> DEPARTMENT_IBFK_1 = Internal.createForeignKey(Department.DEPARTMENT, DSL.name("department_ibfk_1"), new TableField[] { Department.DEPARTMENT.OFFICE_CODE }, Keys.KEY_OFFICE_PRIMARY, new TableField[] { Office.OFFICE.OFFICE_CODE }, true);
    public static final ForeignKey<EmployeeRecord, EmployeeRecord> EMPLOYEES_IBFK_1 = Internal.createForeignKey(Employee.EMPLOYEE, DSL.name("employees_ibfk_1"), new TableField[] { Employee.EMPLOYEE.REPORTS_TO }, Keys.KEY_EMPLOYEE_PRIMARY, new TableField[] { Employee.EMPLOYEE.EMPLOYEE_NUMBER }, true);
    public static final ForeignKey<EmployeeRecord, OfficeRecord> EMPLOYEES_IBFK_2 = Internal.createForeignKey(Employee.EMPLOYEE, DSL.name("employees_ibfk_2"), new TableField[] { Employee.EMPLOYEE.OFFICE_CODE }, Keys.KEY_OFFICE_PRIMARY, new TableField[] { Office.OFFICE.OFFICE_CODE }, true);
    public static final ForeignKey<OfficeHasManagerRecord, ManagerRecord> FK_OFFICES_HAS_MANAGERS_MANAGERS1 = Internal.createForeignKey(OfficeHasManager.OFFICE_HAS_MANAGER, DSL.name("fk_offices_has_managers_managers1"), new TableField[] { OfficeHasManager.OFFICE_HAS_MANAGER.MANAGERS_MANAGER_ID }, Keys.KEY_MANAGER_PRIMARY, new TableField[] { Manager.MANAGER.MANAGER_ID }, true);
    public static final ForeignKey<OfficeHasManagerRecord, OfficeRecord> FK_OFFICES_HAS_MANAGERS_OFFICES = Internal.createForeignKey(OfficeHasManager.OFFICE_HAS_MANAGER, DSL.name("fk_offices_has_managers_offices"), new TableField[] { OfficeHasManager.OFFICE_HAS_MANAGER.OFFICES_OFFICE_CODE }, Keys.KEY_OFFICE_PRIMARY, new TableField[] { Office.OFFICE.OFFICE_CODE }, true);
    public static final ForeignKey<OrderRecord, CustomerRecord> ORDERS_IBFK_1 = Internal.createForeignKey(Order.ORDER, DSL.name("orders_ibfk_1"), new TableField[] { Order.ORDER.CUSTOMER_NUMBER }, Keys.KEY_CUSTOMER_PRIMARY, new TableField[] { Customer.CUSTOMER.CUSTOMER_NUMBER }, true);
    public static final ForeignKey<OrderdetailRecord, OrderRecord> ORDERDETAILS_IBFK_1 = Internal.createForeignKey(Orderdetail.ORDERDETAIL, DSL.name("orderdetails_ibfk_1"), new TableField[] { Orderdetail.ORDERDETAIL.ORDER_ID }, Keys.KEY_ORDER_PRIMARY, new TableField[] { Order.ORDER.ORDER_ID }, true);
    public static final ForeignKey<OrderdetailRecord, ProductRecord> ORDERDETAILS_IBFK_2 = Internal.createForeignKey(Orderdetail.ORDERDETAIL, DSL.name("orderdetails_ibfk_2"), new TableField[] { Orderdetail.ORDERDETAIL.PRODUCT_ID }, Keys.KEY_PRODUCT_PRIMARY, new TableField[] { Product.PRODUCT.PRODUCT_ID }, true);
    public static final ForeignKey<PaymentRecord, CustomerRecord> PAYMENTS_IBFK_1 = Internal.createForeignKey(Payment.PAYMENT, DSL.name("payments_ibfk_1"), new TableField[] { Payment.PAYMENT.CUSTOMER_NUMBER }, Keys.KEY_CUSTOMER_PRIMARY, new TableField[] { Customer.CUSTOMER.CUSTOMER_NUMBER }, true);
    public static final ForeignKey<ProductRecord, ProductlineRecord> PRODUCTS_IBFK_1 = Internal.createForeignKey(Product.PRODUCT, DSL.name("products_ibfk_1"), new TableField[] { Product.PRODUCT.PRODUCT_LINE }, Keys.KEY_PRODUCTLINE_PRIMARY, new TableField[] { Productline.PRODUCTLINE.PRODUCT_LINE }, true);
    public static final ForeignKey<ProductlinedetailRecord, ProductlineRecord> PRODUCTLINEDETAIL_IBFK_1 = Internal.createForeignKey(Productlinedetail.PRODUCTLINEDETAIL, DSL.name("productlinedetail_ibfk_1"), new TableField[] { Productlinedetail.PRODUCTLINEDETAIL.PRODUCT_LINE, Productlinedetail.PRODUCTLINEDETAIL.CODE }, Keys.KEY_PRODUCTLINE_PRIMARY, new TableField[] { Productline.PRODUCTLINE.PRODUCT_LINE, Productline.PRODUCTLINE.CODE }, true);
    public static final ForeignKey<ProductlinedetailRecord, ProductlineRecord> PRODUCTLINEDETAIL_IBFK_2 = Internal.createForeignKey(Productlinedetail.PRODUCTLINEDETAIL, DSL.name("productlinedetail_ibfk_2"), new TableField[] { Productlinedetail.PRODUCTLINEDETAIL.PRODUCT_LINE }, Keys.KEY_PRODUCTLINE_PRIMARY, new TableField[] { Productline.PRODUCTLINE.PRODUCT_LINE }, true);
    public static final ForeignKey<SaleRecord, EmployeeRecord> SALES_IBFK_1 = Internal.createForeignKey(Sale.SALE, DSL.name("sales_ibfk_1"), new TableField[] { Sale.SALE.EMPLOYEE_NUMBER }, Keys.KEY_EMPLOYEE_PRIMARY, new TableField[] { Employee.EMPLOYEE.EMPLOYEE_NUMBER }, true);
    public static final ForeignKey<Top3productRecord, ProductRecord> TOP3PRODUCT_IBFK_1 = Internal.createForeignKey(Top3product.TOP3PRODUCT, DSL.name("top3product_ibfk_1"), new TableField[] { Top3product.TOP3PRODUCT.PRODUCT_ID }, Keys.KEY_PRODUCT_PRIMARY, new TableField[] { Product.PRODUCT.PRODUCT_ID }, true);
}
