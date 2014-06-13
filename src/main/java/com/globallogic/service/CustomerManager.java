/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service;

import com.globallogic.model.Customer;
import com.globallogic.service.impl.GenericManagerImpl;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author srikanth.malyala
 */
public interface CustomerManager extends GenericManager<Customer, Long> {

    public  Customer getCustomerByCustomerId(Long customerId);
    
     public List<Customer> getCustomerssByEnabled() ;
    
public  Customer getCustomerById(Long Id);

public List<Customer> getBlockedCustomers();

}
