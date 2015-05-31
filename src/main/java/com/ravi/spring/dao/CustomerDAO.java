package com.ravi.spring.dao;
 
import java.util.List;

import com.ravi.spring.model.Customer;

/**
 * Customer DAO Repository
 * @author rdarbham
 *
 */
public interface CustomerDAO  {
 
    /**
     * Add customer
     *
     * @param   customer   customer
     */
    void addCustomer(Customer customer);
 
    /**
     * Delete customer
     *
     * @param   customer  customer
     */
    void deleteCustomer(Customer customer);
 
    /**
     * Update customer
     *
     * @param  customer customer 
     */
    void updateCustomer(Customer customer);
 
    /**
     * Get customer
     *
     * @param  id int  
     * @return customer
     */

    Customer getCustomerById(int id);
 
    /**
     * Get customer List
     *
     * @return List - customer list
     */

    List<Customer> getCustomers();
 
}