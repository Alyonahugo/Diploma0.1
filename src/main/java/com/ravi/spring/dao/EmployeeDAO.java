package com.ravi.spring.dao;

import com.ravi.spring.model.Employee;
import com.ravi.spring.model.Section;

import java.util.List;

/**
 * Created by User on 27.05.2015.
 */
public interface EmployeeDAO {

    public void addEmployee(Employee employee);

    public List<Employee> getEmployees();

    public void updateEmployee(Employee employee);

    public void updateEmployees(List<Employee> employees);

}
