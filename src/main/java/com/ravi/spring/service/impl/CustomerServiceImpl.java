package com.ravi.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.spring.dao.CustomerDAO;
import com.ravi.spring.model.Customer;
import com.ravi.spring.service.CustomerService;

/**
 * Implementation of Customer Service
 * @author rdarbham
 *
 */
@Service("CustomerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
	   // CustomerDAO is injected...
    @Autowired
    CustomerDAO customerDAO;
 
    /**
     * Add Customer
     *
     * @param  customer Customer
     */
    @Transactional(readOnly = false)
    @Override
    public void addCustomer(Customer customer) {
        getCustomerDAO().addCustomer(customer);
    }
 
    /**
     * Delete Customer
     *
     * @param   customer  Customer
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteCustomer(Customer customer) {
        getCustomerDAO().deleteCustomer(customer);
    }
 
    /**
     * Update Customer
     *
     * @param customer  Customer
     */
    @Transactional(readOnly = false)
    @Override
    public void updateCustomer(Customer customer) {
        getCustomerDAO().updateCustomer(customer);
    }
 
    /**
     * Get Customer
     *
     * @param  id int Customer Id
     */
    @Override
    public Customer getCustomerById(int id) {
        return getCustomerDAO().getCustomerById(id);
    }
 
    /**
     * Get Customer List
     *
     */
    @Override
    public List<Customer> getCustomers() {
        return getCustomerDAO().getCustomers();
    }
 
    /**
     * Get Customer DAO
     *
     * @return customerDAO - Customer DAO
     */
    @Override
    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }
 
    /**
     * Set Customer DAO
     *
     * @param  customerDAO - CustomerDAO
     */
    @Override
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
}
