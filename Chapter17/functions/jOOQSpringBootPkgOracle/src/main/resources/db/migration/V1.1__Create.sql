/*
*********************************************************************
http://www.mysqltutorial.org
*********************************************************************
Name: MySQL Sample Database classicmodels
Link: http://www.mysqltutorial.org/mysql-sample-database.aspx
*********************************************************************

This is a modified version of the original schema for Oracle
*/

/* START */

/* DEFAULT dept_seq.nextval NOT NULL (Oracle 12c) */
/* GENERATED by default on null as IDENTITY (Oracle 12c) */

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "PAYMENT" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "BANK_TRANSACTION" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "ORDERDETAIL" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "ORDER" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "PRODUCT" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "PRODUCTLINE" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "TOP3PRODUCT" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "PRODUCTLINEDETAIL" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "OFFICE_HAS_MANAGER" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "MANAGER" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "CUSTOMER" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "CUSTOMERDETAIL" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "TOKEN" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "SALE" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "DAILY_ACTIVITY" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "EMPLOYEE" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "EMPLOYEE_STATUS" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "DEPARTMENT" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "OFFICE" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "OFFICE_FLIGHTS" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
COMMIT;

/* Type used in collect() */

BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE SALARY_ARR AS TABLE OF NUMBER(7);';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

/*Table structure for table `office` */

CREATE TABLE office (
  office_code varchar2(10) NOT NULL,
  city varchar2(50),
  phone varchar2(50) NOT NULL,
  address_line_first varchar2(50) NOT NULL,
  address_line_second varchar2(50) DEFAULT NULL,
  state varchar2(50) DEFAULT NULL,
  country varchar2(50),
  postal_code varchar2(15) NOT NULL,
  territory varchar2(10) NOT NULL,
  location sdo_geometry DEFAULT NULL,
  internal_budget int NOT NULL,
  CONSTRAINT office_pk PRIMARY KEY (office_code),
  CONSTRAINT office_postal_code_uk UNIQUE (postal_code)
) ;

/*Table structure for table `employee` */

BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE employeeOfYearArr AS VARRAY(100) OF INTEGER;';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE monthlyBonusArr AS VARRAY(100) OF INTEGER;';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE employee (
  employee_number number(10) NOT NULL,
  last_name varchar2(50) NOT NULL,
  first_name varchar2(50) NOT NULL,
  extension varchar2(10) NOT NULL,
  email varchar2(100) NOT NULL,
  office_code varchar2(10) NOT NULL,
  salary number(7) NOT NULL,
  commission int DEFAULT NULL,
  reports_to number(10) DEFAULT NULL,
  job_title varchar2(50) NOT NULL,
  employee_of_year employeeOfYearArr DEFAULT NULL,
  monthly_bonus monthlyBonusArr DEFAULT NULL,
  CONSTRAINT employee_pk PRIMARY KEY (employee_number)
 ,
  CONSTRAINT employee_employee_fk FOREIGN KEY (reports_to) REFERENCES employee (employee_number),
  CONSTRAINT employee_office_fk FOREIGN KEY (office_code) REFERENCES office (office_code)
) ;

/*Table structure for table `employee_status` */

CREATE TABLE employee_status (
  id number(10) GENERATED BY DEFAULT ON NULL AS IDENTITY,
  employee_number number(10) NOT NULL,  
  status varchar2(50) NOT NULL,  
  acquired_date date NOT NULL,
  CONSTRAINT id_pk PRIMARY KEY (id),  
  CONSTRAINT employee_status_employee_fk FOREIGN KEY (employee_number) REFERENCES employee (employee_number)
);

-- Generate ID using sequence
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "EMPLOYEE_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE employee_seq START WITH 100000 INCREMENT BY 10 MINVALUE 100000 MAXVALUE 10000000000;

/*Table structure for table `sale` */

CREATE TABLE sale (
  sale_id number(20) NOT NULL, 
  fiscal_year int NOT NULL, 
  sale float NOT NULL,    
  employee_number number(10) DEFAULT NULL,  
  hot number(1,0) DEFAULT 0,
  rate varchar2(10) DEFAULT NULL,
  vat varchar2(10) DEFAULT NULL,
  fiscal_month int NOT NULL,
  revenue_growth float NOT NULL,
  trend varchar2(10) DEFAULT NULL,  
  CONSTRAINT sale_pk PRIMARY KEY (sale_id)
,  
  CONSTRAINT sale_employee_fk FOREIGN KEY (employee_number) REFERENCES employee (employee_number),
  CONSTRAINT enum_rate_check CHECK (rate IN('SILVER', 'GOLD', 'PLATINUM')),
  CONSTRAINT enum_vat_check CHECK (vat IN('NONE', 'MIN', 'MAX'))
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "SALE_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE sale_seq START WITH 1000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER sale_seq_tr
 BEFORE INSERT ON sale FOR EACH ROW
 WHEN (NEW.sale_id IS NULL)
BEGIN
 SELECT sale_seq.NEXTVAL INTO :NEW.sale_id FROM DUAL;
END;
/

/*Table structure for table `daily_activity` */

CREATE TABLE daily_activity (
  day_id number(10) GENERATED BY DEFAULT ON NULL AS IDENTITY,
  day_date date NOT NULL,
  sales float NOT NULL,  
  visitors float NOT NULL,    
  conversion float NOT NULL,
  CONSTRAINT daily_activity_pk PRIMARY KEY (day_id)
);

/*Table structure for table token */

CREATE TABLE token (
  token_id number(20) NOT NULL,    
  sale_id number(20) NOT NULL,
  amount float NOT NULL,    
  updated_on timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT token_pk PRIMARY KEY (token_id)
 ,  
  CONSTRAINT token_sale_fk FOREIGN KEY (sale_id) REFERENCES sale (sale_id) ON DELETE CASCADE
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "TOKEN_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE token_seq START WITH 1000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER token_seq_tr
 BEFORE INSERT ON token FOR EACH ROW
 WHEN (NEW.token_id IS NULL)
BEGIN
 SELECT token_seq.NEXTVAL INTO :NEW.token_id FROM DUAL;
END;
/

/*Table structure for table `customer` */

CREATE TABLE customer (
  customer_number number(10) NOT NULL,
  customer_name varchar2(50) NOT NULL,
  contact_last_name varchar2(50) NOT NULL,
  contact_first_name varchar2(50) NOT NULL,
  phone varchar2(50) NOT NULL,
  sales_rep_employee_number number(10) DEFAULT NULL,
  credit_limit number(10,2) DEFAULT NULL,
  first_buy_date int DEFAULT NULL,
  CONSTRAINT customer_pk PRIMARY KEY (customer_number),
  CONSTRAINT customer_name_uk UNIQUE (customer_name)
 ,
  CONSTRAINT customer_employee_fk FOREIGN KEY (sales_rep_employee_number) REFERENCES employee (employee_number)
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "CUSTOMER_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE customer_seq START WITH 1000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER customer_seq_tr
 BEFORE INSERT ON customer FOR EACH ROW
 WHEN (NEW.customer_number IS NULL)
BEGIN
 SELECT customer_seq.NEXTVAL INTO :NEW.customer_number FROM DUAL;
END;
/

/*Table structure for table `customerdetail` */

CREATE TABLE customerdetail (
  customer_number number(10) NOT NULL,
  address_line_first varchar2(50) NOT NULL,
  address_line_second varchar2(50) DEFAULT NULL,
  city varchar2(50),
  state varchar2(50) DEFAULT NULL,
  postal_code varchar2(15) DEFAULT NULL,
  country varchar2(50),
  CONSTRAINT customerdetail_pk PRIMARY KEY (customer_number),
  CONSTRAINT customerdetail_address_line_first_uk UNIQUE (address_line_first)
 ,
  CONSTRAINT customerdetail_customer_fk FOREIGN KEY (customer_number) REFERENCES customer (customer_number)
) ; 

/* Table structure for table `department` */

BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE topicArr AS VARRAY(100) OF VARCHAR2(100 CHAR);';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE department (
  department_id number(10) NOT NULL,
  name varchar(50) NOT NULL,
  phone varchar(50) NOT NULL,
  code number(5) DEFAULT 1,
  office_code varchar(10) NOT NULL,
  topic topicArr DEFAULT NULL,  
  dep_net_ipv4 varchar(16) DEFAULT NULL,
  local_budget float DEFAULT NULL,
  profit float DEFAULT NULL,
  forecast_profit float DEFAULT NULL,
  cash float DEFAULT NULL,
  accounts_receivable float DEFAULT NULL,
  inventories float DEFAULT NULL,
  accounts_payable float DEFAULT NULL,
  st_borrowing float DEFAULT NULL,
  accrued_liabilities float DEFAULT NULL,
  CONSTRAINT department_pk PRIMARY KEY (department_id),
  CONSTRAINT department_code_uk UNIQUE (code)
,
  CONSTRAINT department_office_fk FOREIGN KEY (office_code) REFERENCES office (office_code)
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "DEPARTMENT_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE department_seq START WITH 10 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER department_seq_tr
 BEFORE INSERT ON department FOR EACH ROW
 WHEN (NEW.department_id IS NULL)
BEGIN
 SELECT department_seq.NEXTVAL INTO :NEW.department_id FROM DUAL;
END;
/

/*Table structure for table `manager` */

/* Define a type using CREATE TYPE */
BEGIN
   EXECUTE IMMEDIATE '
CREATE OR REPLACE TYPE evaluation_criteria AS OBJECT (
   communication_ability NUMBER(6), 
   ethics NUMBER(6), 
   performance NUMBER(6), 
   employee_input NUMBER(6),
   MEMBER FUNCTION improve(k NUMBER) RETURN evaluation_criteria,
   MAP MEMBER FUNCTION score RETURN NUMBER
   );';
   EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
COMMIT;

BEGIN
   EXECUTE IMMEDIATE '
CREATE OR REPLACE TYPE BODY evaluation_criteria AS 
   MEMBER FUNCTION improve(k NUMBER) RETURN evaluation_criteria IS 
   BEGIN 
      RETURN evaluation_criteria(self.communication_ability + k, self.ethics + k, self.performance + k, self.employee_input); 
   END improve;     
   MAP MEMBER FUNCTION score RETURN NUMBER IS 
   BEGIN 
      RETURN (SQRT(communication_ability*employee_input - ethics*performance)); 
   END score;    
END;';
   EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/
COMMIT;

CREATE TABLE manager (
  manager_id number(10) NOT NULL,
  manager_name varchar2(50) NOT NULL,  
  manager_detail varchar2(4000),  
  -- for large JSON, use manager_detail blob,
  manager_evaluation evaluation_criteria DEFAULT NULL, 
  CONSTRAINT manager_pk PRIMARY KEY (manager_id),
  CONSTRAINT ENSURE_JSON CHECK (manager_detail IS JSON)
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "MANAGER_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE manager_seq START WITH 1000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER manager_seq_tr
 BEFORE INSERT ON manager FOR EACH ROW
 WHEN (NEW.manager_id IS NULL)
BEGIN
 SELECT manager_seq.NEXTVAL INTO :NEW.manager_id FROM DUAL;
END;
/

/*Table structure for table `office_has_manager` */

CREATE TABLE office_has_manager (
  offices_office_code varchar2(10) NOT NULL,
  managers_manager_id number(10) NOT NULL,
  CONSTRAINT office_manager_uk UNIQUE (offices_office_code, managers_manager_id),
  CONSTRAINT office_fk FOREIGN KEY (offices_office_code) REFERENCES office (office_code),
  CONSTRAINT manager_fk FOREIGN KEY (managers_manager_id) REFERENCES manager (manager_id)  
);

/*Table structure for table `productline` */

CREATE TABLE productline (
  product_line varchar2(50) NOT NULL,
  code number(10) NOT NULL,
  text_description varchar2(4000) DEFAULT NULL,
  html_description xmltype,
  image blob,
  created_on date DEFAULT SYSDATE NOT NULL,
  CONSTRAINT productline_pk PRIMARY KEY (product_line, code),
  CONSTRAINT productline_uk UNIQUE(product_line)
);

/*Table structure for table `productdetail` */

CREATE TABLE productlinedetail (
  product_line varchar2(50) NOT NULL,
  code number(10) NOT NULL,
  line_capacity varchar2(20) NOT NULL,
  line_type number(1) DEFAULT 0,
  CONSTRAINT productlinedetail_pk PRIMARY KEY (product_line,code),  
  CONSTRAINT productlinedetail_uk UNIQUE(product_line),
  CONSTRAINT productlinedetail_productline_fk FOREIGN KEY (product_line,code) REFERENCES productline (product_line,code)
) ;

/*Table structure for table `product` */

CREATE TABLE product (
  product_id number(10) NOT NULL,
  product_name varchar2(70) DEFAULT NULL,
  product_line varchar2(50) DEFAULT NULL,
  code number(10) NOT NULL,
  product_scale varchar2(10) DEFAULT NULL,
  product_vendor varchar2(50) DEFAULT NULL,
  product_description clob DEFAULT NULL,
  quantity_in_stock number(7) DEFAULT 0,
  buy_price number(10,2) DEFAULT 0.0 NOT NULL,
  msrp number(10,2) DEFAULT 0.0 NOT NULL,
  specs clob DEFAULT NULL,
  product_uid number(10) NOT NULL,
  CONSTRAINT product_pk PRIMARY KEY (product_id)
 ,
  CONSTRAINT product_productline_fk FOREIGN KEY (product_line, code) REFERENCES productline (product_line, code)
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "PRODUCT_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE product_seq START WITH 1000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER product_seq_tr
 BEFORE INSERT ON product FOR EACH ROW
 WHEN (NEW.product_id IS NULL)
BEGIN
 SELECT product_seq.NEXTVAL INTO :NEW.product_id FROM DUAL;
END;
/

-- Generate an IDENTITY, non-primary key
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "PRODUCT_UID_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE product_uid_seq START WITH 10 INCREMENT BY 10;

CREATE OR REPLACE TRIGGER product_uid_seq_tr
 BEFORE INSERT ON product FOR EACH ROW
 WHEN (NEW.product_uid IS NULL)
BEGIN
 SELECT product_uid_seq.NEXTVAL INTO :NEW.product_uid FROM DUAL;
END;
/

/*Table structure for table `order` */

CREATE TABLE "ORDER" (
  order_id number(10) NOT NULL,
  order_date date NOT NULL,
  required_date date NOT NULL,
  shipped_date date DEFAULT NULL,
  status varchar2(15) NOT NULL,
  comments clob,
  customer_number number(10) NOT NULL,
  amount number(10,2) NOT NULL,
  CONSTRAINT order_pk PRIMARY KEY (order_id)
 ,
  CONSTRAINT order_customer_fk FOREIGN KEY (customer_number) REFERENCES customer (customer_number)
);

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "ORDER_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE order_seq START WITH 1000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER order_seq_tr
 BEFORE INSERT ON "ORDER" FOR EACH ROW
 WHEN (NEW.order_id IS NULL)
BEGIN
 SELECT order_seq.NEXTVAL INTO :NEW.order_id FROM DUAL;
END;
/

/*Table structure for table `orderdetail` */

CREATE TABLE orderdetail (
  orderdetail_id number(10) NOT NULL,
  order_id number(10) NOT NULL,
  product_id number(10) NOT NULL,
  quantity_ordered number(10) NOT NULL,
  price_each number(10,2) NOT NULL,
  order_line_number number(5) NOT NULL,
  CONSTRAINT orderdetail_pk PRIMARY KEY (orderdetail_id),
  CONSTRAINT orderdetail_uk UNIQUE (order_id, product_id),
  CONSTRAINT orderdetail_order_fk FOREIGN KEY (order_id) REFERENCES "ORDER" (order_id),
  CONSTRAINT orderdetail_product__fk FOREIGN KEY (product_id) REFERENCES product (product_id)
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "ORDERDETAIL_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE orderdetail_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER orderdetail_seq_tr
 BEFORE INSERT ON "ORDERDETAIL" FOR EACH ROW
 WHEN (NEW.orderdetail_id IS NULL)
BEGIN
 SELECT orderdetail_seq.NEXTVAL INTO :NEW.orderdetail_id FROM DUAL;
END;
/

/*Table structure for table `top3product` */

CREATE TABLE top3product (  
  product_id number(10) NOT NULL,
  product_name varchar2(70) DEFAULT NULL,  
  CONSTRAINT top3product_pk PRIMARY KEY (product_id),  
  CONSTRAINT top3product_product_fk FOREIGN KEY (product_id) REFERENCES product (product_id)
) ;

/*Table structure for table `payment` */

CREATE TABLE payment (
  customer_number number(10) NOT NULL,
  check_number varchar2(50) NOT NULL,
  payment_date timestamp DEFAULT CURRENT_TIMESTAMP,
  invoice_amount number(10,2) NOT NULL,
  caching_date timestamp DEFAULT NULL,
  version int DEFAULT 0,
  modified timestamp DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT payment_pk PRIMARY KEY (customer_number,check_number),
  CONSTRAINT check_number_uk UNIQUE (check_number),
  CONSTRAINT payment_customer_fk FOREIGN KEY (customer_number) REFERENCES customer (customer_number)
) ;

/* Table structure for table 'bank_transaction' */

CREATE TABLE bank_transaction (
  transaction_id number(10) NOT NULL,
  bank_name varchar2(50) NOT NULL,
  bank_iban varchar2(50) NOT NULL,  
  transfer_amount number(10,2) NOT NULL,
  caching_date timestamp DEFAULT SYSTIMESTAMP,
  customer_number number(10) NOT NULL,
  check_number varchar2(50) NOT NULL, 
  card_type varchar2(50) NOT NULL,
  status varchar2(50) DEFAULT 'SUCCESS',
  CONSTRAINT bank_transaction_pk PRIMARY KEY (transaction_id),  
  CONSTRAINT bank_transaction_customer_fk FOREIGN KEY (customer_number,check_number) REFERENCES payment (customer_number,check_number)
) ;

-- Generate ID using sequence and trigger
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE "BANK_TRANSACTION_SEQ"';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE bank_transaction_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER bank_transaction_seq_tr
 BEFORE INSERT ON bank_transaction FOR EACH ROW
 WHEN (NEW.transaction_id IS NULL)
BEGIN
 SELECT bank_transaction_seq.NEXTVAL INTO :NEW.transaction_id FROM DUAL;
END;
/

/*Table structure for table `office_flights` */

CREATE TABLE office_flights (  
  depart_town varchar2(32) NOT NULL,
  arrival_town varchar2(32) NOT NULL,
  distance_km number(7) NOT NULL,
  CONSTRAINT office_flights_pk PRIMARY KEY (depart_town, arrival_town)
);

COMMIT;

/* USER-DEFINED FUNCTIONS */
CREATE OR REPLACE PACKAGE "department_pkg"
AS
  TYPE "bgt_arr" IS TABLE OF FLOAT INDEX BY PLS_INTEGER;

  FUNCTION "get_bgt"("p_profit" IN FLOAT)
    RETURN "bgt_arr";
	
  FUNCTION "get_max_cash"
    RETURN FLOAT; 
END "department_pkg";
/
CREATE OR REPLACE PACKAGE BODY "department_pkg"
 AS  
  FUNCTION "get_bgt"("p_profit" IN FLOAT)
    RETURN "bgt_arr"
  IS
    "r_bgt_arr" "bgt_arr";
  BEGIN
    SELECT "SYSTEM"."DEPARTMENT"."LOCAL_BUDGET"
      BULK COLLECT INTO "r_bgt_arr"
      FROM "SYSTEM"."DEPARTMENT"
     WHERE "SYSTEM"."DEPARTMENT"."PROFIT" > "p_profit";

    RETURN "r_bgt_arr";
    BEGIN
    dbms_output.put_line('Control is now executing the package initialization part');
    END;
  END;
  
  FUNCTION "get_max_cash"
    RETURN FLOAT 
  IS
    "r_max_cash" FLOAT;
  BEGIN
    SELECT max("SYSTEM"."DEPARTMENT"."CASH") INTO "r_max_cash"
	  FROM "SYSTEM"."DEPARTMENT";
	  
	RETURN "r_max_cash";  
  END;  
END "department_pkg";
/

CREATE OR REPLACE FUNCTION get_total_sales(
    in_year IN PLS_INTEGER
) 
RETURN NUMBER
IS
    l_total_sales NUMBER := 0;
BEGIN
    -- get total sales
    SELECT SUM(PRICE_EACH * QUANTITY_ORDERED)
    INTO l_total_sales
    FROM ORDERDETAIL
    INNER JOIN "ORDER" USING (ORDER_ID)
    WHERE STATUS = 'Shipped'
    GROUP BY EXTRACT(YEAR FROM ORDER_DATE)
    HAVING EXTRACT(YEAR FROM ORDER_DATE) = in_year;
    
    -- return the total sales
    RETURN l_total_sales;
END;
/

CREATE OR REPLACE FUNCTION get_salary_stat(
    min_sal OUT INTEGER,
    max_sal OUT INTEGER,
    avg_sal OUT REAL) 
RETURN REAL IS
BEGIN
  SELECT MIN(salary),
         MAX(salary),
		 AVG(salary)
  INTO min_sal, max_sal, avg_sal
  FROM employee;
  RETURN avg_sal / sqrt(min_sal * max_sal);
END;
/

CREATE OR REPLACE FUNCTION swap(
	x IN OUT PLS_INTEGER,
	y IN OUT PLS_INTEGER
) 
RETURN PLS_INTEGER IS
BEGIN
   SELECT x,y INTO y,x FROM dual;
   RETURN x + y;
END; 
/

CREATE OR REPLACE NONEDITIONABLE FUNCTION "sale_price"(
    "quantity" IN PLS_INTEGER,
    "list_price" IN REAL,
    "fraction_of_price" IN REAL
)
RETURN REAL IS
    result REAL := ("list_price" - ("list_price" * "fraction_of_price")) * "quantity";    
BEGIN
    RETURN result;
END;
/

CREATE OR REPLACE NONEDITIONABLE FUNCTION "card_commission"("card_type" IN VARCHAR2)
RETURN NUMBER IS
 "commision" NUMBER := 0;
 BEGIN
   RETURN CASE "card_type"
     WHEN 'VisaElectron' THEN .15
     WHEN 'Mastercard' THEN .22
     ELSE .25
   END;
END;   
/   

CREATE OR REPLACE NONEDITIONABLE FUNCTION "get_customer" ("cl" IN NUMBER)
RETURN SYS_REFCURSOR
  AS "cur" SYS_REFCURSOR;
BEGIN
  OPEN "cur" FOR
  SELECT *
    FROM "SYSTEM"."CUSTOMER"
	WHERE "SYSTEM"."CUSTOMER"."CREDIT_LIMIT" > "cl"
    ORDER BY "SYSTEM"."CUSTOMER"."CUSTOMER_NAME";
  RETURN "cur";
END;
/

-- Create Object of your table
BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE TABLE_RES_OBJ AS OBJECT (SALES FLOAT);';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

--Create a type of your object 
BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE TABLE_RES AS TABLE OF TABLE_RES_OBJ;';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE OR REPLACE NONEDITIONABLE FUNCTION top_three_sales_per_employee (
    employee_nr IN NUMBER
) RETURN TABLE_RES IS
    table_result TABLE_RES;
BEGIN
    SELECT
        TABLE_RES_OBJ("SYSTEM"."SALE"."SALE") "sales"
    BULK COLLECT
    INTO table_result
    FROM
        "SYSTEM"."SALE"
    WHERE
        employee_nr = "SYSTEM"."SALE"."EMPLOYEE_NUMBER"
    ORDER BY
        "SYSTEM"."SALE"."SALE" DESC
    FETCH NEXT 3 ROWS ONLY;

    RETURN table_result;
END;
/

-- Create Object of your table 2
BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE TABLE_POPL_OBJ AS OBJECT (P_ID NUMBER(10), P_NAME VARCHAR2(70), P_LINE VARCHAR2(50));';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

--Create a type of your object 2
BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE TABLE_POPL AS TABLE OF TABLE_POPL_OBJ;';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE OR REPLACE NONEDITIONABLE FUNCTION product_of_product_line (
    p_line_in IN VARCHAR2
) RETURN TABLE_POPL IS
    table_result TABLE_POPL;
BEGIN
    SELECT
        TABLE_POPL_OBJ("SYSTEM"."PRODUCT"."PRODUCT_ID", 
		"SYSTEM"."PRODUCT"."PRODUCT_NAME", 
		"SYSTEM"."PRODUCT"."PRODUCT_LINE")
    BULK COLLECT
    INTO table_result
    FROM
        "SYSTEM"."PRODUCT"
    WHERE
        p_line_in = "SYSTEM"."PRODUCT"."PRODUCT_LINE";

    RETURN table_result;
END;
/

--Creating and Using a User-Defined Aggregate
BEGIN
   EXECUTE IMMEDIATE 'CREATE TYPE SecondMaxImpl AS OBJECT
(
  max NUMBER, 
  secmax NUMBER, 
  STATIC FUNCTION ODCIAggregateInitialize(sctx IN OUT SecondMaxImpl) 
    RETURN NUMBER,
  MEMBER FUNCTION ODCIAggregateIterate(self IN OUT SecondMaxImpl, 
    value IN NUMBER) RETURN NUMBER,
  MEMBER FUNCTION ODCIAggregateTerminate(self IN SecondMaxImpl, 
    returnValue OUT NUMBER, flags IN NUMBER) RETURN NUMBER,
  MEMBER FUNCTION ODCIAggregateMerge(self IN OUT SecondMaxImpl, 
    ctx2 IN SecondMaxImpl) RETURN NUMBER
);';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE OR REPLACE TYPE BODY SecondMaxImpl IS 
STATIC FUNCTION ODCIAggregateInitialize(sctx IN OUT SecondMaxImpl) RETURN NUMBER IS 
BEGIN
  sctx := SecondMaxImpl(0, 0);
  RETURN ODCIConst.Success;
END;

MEMBER FUNCTION ODCIAggregateIterate(self IN OUT SecondMaxImpl, value IN NUMBER) RETURN NUMBER IS
BEGIN
  IF value > self.max THEN
    self.secmax := self.max;
    self.max := value;
  ELSIF value > self.secmax THEN
    self.secmax := value;
  END IF;
  RETURN ODCIConst.Success;
END;

MEMBER FUNCTION ODCIAggregateTerminate(self IN SecondMaxImpl, returnValue OUT NUMBER, flags IN NUMBER) RETURN NUMBER IS
BEGIN
  returnValue := self.secmax;
  RETURN ODCIConst.Success;
END;

MEMBER FUNCTION ODCIAggregateMerge(self IN OUT SecondMaxImpl, ctx2 IN SecondMaxImpl) RETURN NUMBER IS
BEGIN
  IF ctx2.max > self.max THEN
    IF ctx2.secmax > self.secmax THEN 
      self.secmax := ctx2.secmax;
    ELSE
      self.secmax := self.max;
    END IF;
    self.max := ctx2.max;
  ELSIF ctx2.max > self.secmax THEN
    self.secmax := ctx2.max;
  END IF;
  RETURN ODCIConst.Success;
END;
END;
/

CREATE OR REPLACE FUNCTION SecondMax (input NUMBER) RETURN NUMBER 
PARALLEL_ENABLE AGGREGATE USING SecondMaxImpl;
/

-- USER-DEFINED PROCEDURES
CREATE OR REPLACE PROCEDURE get_product(pid IN NUMBER, cursor_result OUT SYS_REFCURSOR)
AS BEGIN
    OPEN cursor_result FOR
	SELECT * FROM product WHERE product_id = pid;
END;
/

CREATE OR REPLACE PROCEDURE get_emps_in_office(in_office_code IN VARCHAR,
  cursor_office OUT SYS_REFCURSOR, cursor_employee OUT SYS_REFCURSOR)
AS BEGIN
 OPEN cursor_office FOR
    SELECT city, country, internal_budget
      FROM office
     WHERE office_code=in_office_code;

 OPEN cursor_employee FOR
    SELECT employee_number,first_name,last_name
      FROM employee
     WHERE office_code=in_office_code;
END;
/

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "get_avg_price_by_product_line" (
	"pl" IN VARCHAR2,
	"average" OUT DECIMAL
)
AS BEGIN
	SELECT AVG("SYSTEM"."PRODUCT"."BUY_PRICE")
	INTO "average"
	FROM "SYSTEM"."PRODUCT"
	WHERE "SYSTEM"."PRODUCT"."PRODUCT_LINE" = "pl";
END;
/

CREATE OR REPLACE PROCEDURE refresh_top3_product(p_line_in IN VARCHAR2)
AS BEGIN
	DELETE FROM "SYSTEM"."TOP3PRODUCT"; 
        INSERT INTO "SYSTEM"."TOP3PRODUCT"("SYSTEM"."TOP3PRODUCT"."PRODUCT_ID", "SYSTEM"."TOP3PRODUCT"."PRODUCT_NAME")        
		SELECT "T"."PRODUCT_ID", "T"."PRODUCT_NAME" FROM (
        SELECT "SYSTEM"."ORDERDETAIL"."PRODUCT_ID", "PRODUCT_NAME", max("SYSTEM"."ORDERDETAIL"."QUANTITY_ORDERED") "QO" 
		FROM "SYSTEM"."ORDERDETAIL" 
		CROSS APPLY (SELECT "SYSTEM"."PRODUCT"."PRODUCT_NAME" "PRODUCT_NAME" 
		  FROM "SYSTEM"."PRODUCT" WHERE ("SYSTEM"."ORDERDETAIL"."PRODUCT_ID" = "SYSTEM"."PRODUCT"."PRODUCT_ID" 
		    AND "SYSTEM"."PRODUCT"."PRODUCT_LINE" = p_line_in))
        GROUP BY "SYSTEM"."ORDERDETAIL"."PRODUCT_ID", "PRODUCT_NAME") "T"
		ORDER BY "T"."QO"        
		FETCH NEXT 3 ROWS ONLY;         
END;
/

CREATE OR REPLACE PROCEDURE set_counter(
	counter IN OUT INTEGER,
    inc IN INTEGER
)
AS BEGIN
	counter := counter + inc;
END;
/

-- VIEWS
CREATE OR REPLACE VIEW CUSTOMER_MASTER AS
SELECT "SYSTEM"."CUSTOMER"."CUSTOMER_NAME",
       "SYSTEM"."CUSTOMER"."CREDIT_LIMIT",
       "SYSTEM"."CUSTOMERDETAIL"."CITY",
       "SYSTEM"."CUSTOMERDETAIL"."COUNTRY",
       "SYSTEM"."CUSTOMERDETAIL"."ADDRESS_LINE_FIRST",
       "SYSTEM"."CUSTOMERDETAIL"."POSTAL_CODE",
       "SYSTEM"."CUSTOMERDETAIL"."STATE"
FROM "SYSTEM"."CUSTOMER"
JOIN "SYSTEM"."CUSTOMERDETAIL" ON "SYSTEM"."CUSTOMERDETAIL"."CUSTOMER_NUMBER" = "SYSTEM"."CUSTOMER"."CUSTOMER_NUMBER"
WHERE "SYSTEM"."CUSTOMER"."FIRST_BUY_DATE" IS NOT NULL;

CREATE OR REPLACE VIEW OFFICE_MASTER AS
SELECT "SYSTEM"."OFFICE"."OFFICE_CODE",
       "SYSTEM"."OFFICE"."CITY",
       "SYSTEM"."OFFICE"."COUNTRY",
       "SYSTEM"."OFFICE"."STATE",
       "SYSTEM"."OFFICE"."PHONE",
	   "SYSTEM"."OFFICE"."POSTAL_CODE"
FROM "SYSTEM"."OFFICE"
WHERE "SYSTEM"."OFFICE"."CITY" IS NOT NULL;

CREATE OR REPLACE VIEW PRODUCT_MASTER AS
SELECT "SYSTEM"."PRODUCT"."PRODUCT_LINE",
       "SYSTEM"."PRODUCT"."PRODUCT_NAME",
       "SYSTEM"."PRODUCT"."PRODUCT_SCALE"       
FROM "SYSTEM"."PRODUCT";

/* END */
/* END */