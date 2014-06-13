/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao;

import com.globallogic.model.Customer;
import java.util.List;

/**
 *
 * @author srikanth.malyala
 */
public interface CustomerDao extends GenericDao<Customer, Long> {

    Customer getCustomerById(Long id);

    Customer getCustomerByCustomerId(Long customerId);

    public List<Customer> getCustomerssByEnabled();

    public List<Customer> getBlockedCustomers();
}