/*
 * Copyright 2017 Indo Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indo.example;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;

import static indo.jdbc.ResultSets.*;

public class Employee implements RowIdentity {
    private boolean active;

    private BigDecimal salary;

    private Date hireDate;
    private Date departureDate;

    private Integer employeeId;
    private Long payrollId;

    private String firstName;
    private String lastName;

    public Employee() {
    }

    public Employee(Integer employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(ResultSet rs) {
        this(getBoolean(rs, "active"),
                getBigDecimal(rs, "salary"),
                getDate(rs, "hireDate"),
                getDate(rs, "departureDate"),
                getInt(rs, "employeeId"),
                getLong(rs, "payrollId"),
                getString(rs, "firstName"),
                getString(rs, "lastName"));
    }


    public Employee(boolean active, BigDecimal salary, Date hireDate, Date departureDate, Integer employeeId, Long payrollId, String firstName, String lastName) {
        this.active = active;
        this.salary = salary;
        this.hireDate = hireDate;
        this.departureDate = departureDate;
        this.employeeId = employeeId;
        this.payrollId = payrollId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d)", getFirstName(), getLastName(), getEmployeeId());
    }

    @Override
    public Integer getRowId() {
        return getEmployeeId() - 100;
    }
}
