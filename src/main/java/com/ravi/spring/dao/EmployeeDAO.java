package com.ravi.spring.dao;

import com.ravi.spring.model.Employee;

/**
 * Created by User on 01.06.2015.
 */
public interface EmployeeDAO {

    Employee getEmployeeById(int id);

    Employee getEmployeeByLoginPassword(String login, String pass);
}
