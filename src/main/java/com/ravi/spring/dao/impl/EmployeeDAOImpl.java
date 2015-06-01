package com.ravi.spring.dao.impl;

import com.ravi.spring.dao.EmployeeDAO;
import com.ravi.spring.model.Employee;
import com.ravi.spring.model.Project;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 01.06.2015.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Employee getEmployeeByLoginPassword(String login, String pass) {
        List<Employee> list = getSessionFactory().getCurrentSession()
                .createQuery("from Employee  where name=? and password = ?")
                .setParameter(0, login).setParameter(1, pass).list();
        if (list != null) {
            return list.get(0);
        }
        return null;
    }
}
