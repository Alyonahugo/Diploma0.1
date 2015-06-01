package com.ravi.spring.service.impl;

import com.ravi.spring.dao.EmployeeDAO;
import com.ravi.spring.dao.ProjectDAO;
import com.ravi.spring.model.Employee;
import com.ravi.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by User on 01.06.2015.
 */

@Service("employeeService")
@Transactional
@ManagedBean(name = "employeeService")
@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;


    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Employee getEmployeeByLoginPassword(String login, String pass) {
        return employeeDAO.getEmployeeByLoginPassword(login, pass);
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
