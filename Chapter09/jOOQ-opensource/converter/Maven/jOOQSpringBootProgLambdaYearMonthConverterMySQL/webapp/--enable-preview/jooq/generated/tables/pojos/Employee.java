/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.pojos;


import java.io.Serializable;

import javax.annotation.processing.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.4",
        "schema version:1.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long    employeeNumber;
    private String  lastName;
    private String  firstName;
    private String  extension;
    private String  email;
    private String  officeCode;
    private Integer salary;
    private Long    reportsTo;
    private String  jobTitle;
    private String  employeeOfYear;

    public Employee() {}

    public Employee(Employee value) {
        this.employeeNumber = value.employeeNumber;
        this.lastName = value.lastName;
        this.firstName = value.firstName;
        this.extension = value.extension;
        this.email = value.email;
        this.officeCode = value.officeCode;
        this.salary = value.salary;
        this.reportsTo = value.reportsTo;
        this.jobTitle = value.jobTitle;
        this.employeeOfYear = value.employeeOfYear;
    }

    public Employee(
        Long    employeeNumber,
        String  lastName,
        String  firstName,
        String  extension,
        String  email,
        String  officeCode,
        Integer salary,
        Long    reportsTo,
        String  jobTitle,
        String  employeeOfYear
    ) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.officeCode = officeCode;
        this.salary = salary;
        this.reportsTo = reportsTo;
        this.jobTitle = jobTitle;
        this.employeeOfYear = employeeOfYear;
    }

    /**
     * Getter for <code>classicmodels.employee.employee_number</code>.
     */
    @NotNull
    public Long getEmployeeNumber() {
        return this.employeeNumber;
    }

    /**
     * Setter for <code>classicmodels.employee.employee_number</code>.
     */
    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * Getter for <code>classicmodels.employee.last_name</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for <code>classicmodels.employee.last_name</code>.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for <code>classicmodels.employee.first_name</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for <code>classicmodels.employee.first_name</code>.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for <code>classicmodels.employee.extension</code>.
     */
    @NotNull
    @Size(max = 10)
    public String getExtension() {
        return this.extension;
    }

    /**
     * Setter for <code>classicmodels.employee.extension</code>.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Getter for <code>classicmodels.employee.email</code>.
     */
    @NotNull
    @Size(max = 100)
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>classicmodels.employee.email</code>.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for <code>classicmodels.employee.office_code</code>.
     */
    @NotNull
    @Size(max = 10)
    public String getOfficeCode() {
        return this.officeCode;
    }

    /**
     * Setter for <code>classicmodels.employee.office_code</code>.
     */
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    /**
     * Getter for <code>classicmodels.employee.salary</code>.
     */
    @NotNull
    public Integer getSalary() {
        return this.salary;
    }

    /**
     * Setter for <code>classicmodels.employee.salary</code>.
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     * Getter for <code>classicmodels.employee.reports_to</code>.
     */
    public Long getReportsTo() {
        return this.reportsTo;
    }

    /**
     * Setter for <code>classicmodels.employee.reports_to</code>.
     */
    public void setReportsTo(Long reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * Getter for <code>classicmodels.employee.job_title</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getJobTitle() {
        return this.jobTitle;
    }

    /**
     * Setter for <code>classicmodels.employee.job_title</code>.
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Getter for <code>classicmodels.employee.employee_of_year</code>.
     */
    @Size(max = 50)
    public String getEmployeeOfYear() {
        return this.employeeOfYear;
    }

    /**
     * Setter for <code>classicmodels.employee.employee_of_year</code>.
     */
    public void setEmployeeOfYear(String employeeOfYear) {
        this.employeeOfYear = employeeOfYear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Employee (");

        sb.append(employeeNumber);
        sb.append(", ").append(lastName);
        sb.append(", ").append(firstName);
        sb.append(", ").append(extension);
        sb.append(", ").append(email);
        sb.append(", ").append(officeCode);
        sb.append(", ").append(salary);
        sb.append(", ").append(reportsTo);
        sb.append(", ").append(jobTitle);
        sb.append(", ").append(employeeOfYear);

        sb.append(")");
        return sb.toString();
    }
}
