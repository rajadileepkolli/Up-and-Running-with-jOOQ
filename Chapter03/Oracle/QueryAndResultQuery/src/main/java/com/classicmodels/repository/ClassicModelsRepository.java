package com.classicmodels.repository;

import java.util.List;
import static jooq.generated.tables.Employee.EMPLOYEE;
import static jooq.generated.tables.Payment.PAYMENT;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.Record;
import org.jooq.ResultQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ClassicModelsRepository {

    private final DSLContext ctx;

    public ClassicModelsRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    @Transactional
    public int jooqQuery() {

        // Query query = ctx.query("DELETE FROM PAYMENT WHERE CUSTOMER_NUMBER = 103");
        
        Query query = ctx.deleteFrom(PAYMENT)
                .where(PAYMENT.CUSTOMER_NUMBER.eq(103L));

        int affectedRows = query.execute();

        return affectedRows;
    }

    public List<String> jooqResultQuery() {

        /*
        ResultQuery<Record> resultQuery = ctx.resultQuery(
                "SELECT JOB_TITLE FROM EMPLOYEE WHERE OFFICE_CODE = '4'");        
        Result<Record> fetched = resultQuery.fetch();
        */
        
        ResultQuery<Record1<String>> resultQuery = ctx.select(EMPLOYEE.JOB_TITLE)
                .from(EMPLOYEE)
                .where(EMPLOYEE.OFFICE_CODE.eq("4"));
        Result<Record1<String>> fetched = resultQuery.fetch();

        List<String> result = fetched.into(String.class);

        return result;
    }

}
