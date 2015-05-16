package com.ravi.spring.service;
 
import java.util.List;

import com.ravi.spring.dao.CustomerDAO;
import com.ravi.spring.model.Customer;

/**
 * Customer Service
 * 
 * @author rdarbham
 *
 */
public interface CustomerService {
 
    /**
     * Add Customer
     *
     * @param  customer Customer
     */
    public void addCustomer(Customer customer);
 
    /**
     * Delete Customer
     *
     * @param   customer  Customer
     */
    public void deleteCustomer(Customer customer);
 
    /**
     * Update Customer
     *
     * @param customer  Customer
     */
    public void updateCustomer(Customer customer);
 
    /**
     * Get Customer
     *
     * @param  id int Customer Id
     */

    public Customer getCustomerById(int id);
 
    /**
     * Get Customer List
     *
     */

    public List<Customer> getCustomers();
 
    /**
     * Get Customer DAO
     *
     * @return customerDAO - Customer DAO
     */
    public CustomerDAO getCustomerDAO();
 
    /**
     * Set Customer DAO
     *
     * @param  customerDAO - CustomerDAO
     */
    public void setCustomerDAO(CustomerDAO customerDAO);
}