package com.classicmodels.repository;

import com.classicmodels.pojo.CustomerDTO;
import java.math.BigDecimal;
import java.util.List;
import static jooq.generated.tables.Customer.CUSTOMER;
import static jooq.generated.tables.Customerdetail.CUSTOMERDETAIL;
import org.jooq.DSLContext;
import org.simpleflatmapper.jooq.SelectQueryMapper;
import org.simpleflatmapper.jooq.SelectQueryMapperFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private final SelectQueryMapper<CustomerDTO> customerMapper;
    private final DSLContext create;

    public CustomerRepository(DSLContext create) {
        this.create = create;
        this.customerMapper = SelectQueryMapperFactory
                .newInstance()
                .newMapper(CustomerDTO.class);
    }

    public List<CustomerDTO> findCustomerByCreditLimit(float creditLimit) {

        List<CustomerDTO> customers = customerMapper.asList(
                create.select(CUSTOMER.CUSTOMER_NAME, CUSTOMER.PHONE, CUSTOMER.CREDIT_LIMIT,
                              CUSTOMERDETAIL.ADDRESS_LINE_FIRST, CUSTOMERDETAIL.STATE, CUSTOMERDETAIL.CITY)
                        .from(CUSTOMER)
                        .innerJoin(CUSTOMERDETAIL).using(CUSTOMER.CUSTOMER_NUMBER)
                        .where(CUSTOMER.CREDIT_LIMIT.le(BigDecimal.valueOf(creditLimit)))
                        .orderBy(CUSTOMER.CUSTOMER_NUMBER)
        );

        return customers;
    }
}
