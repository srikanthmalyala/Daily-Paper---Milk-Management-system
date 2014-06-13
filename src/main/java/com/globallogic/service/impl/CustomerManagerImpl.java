/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service.impl;

import com.globallogic.dao.CustomerDao;
import com.globallogic.model.Customer;
import com.globallogic.service.CustomerManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author srikanth.malyala
 */

@Service("customerManager")

 public class CustomerManagerImpl extends GenericManagerImpl<Customer, Long> implements CustomerManager{
     
     public CustomerDao customerDao;
     
    @Autowired
     public void setCustomerDao(CustomerDao customerDao)
     {
           this.dao=customerDao;
           this.customerDao=customerDao;
           
     }

    @Autowired
    public CustomerManagerImpl(CustomerDao customerDao) {
        super(customerDao);
        this.customerDao=customerDao;             
        
    }

    public Customer getCustomerById(Long id) {
        
        return customerDao.getCustomerById(id);
       
    }
     
    
     public Customer getCustomerByCustomerId(Long customerId) {
        
        return customerDao.getCustomerByCustomerId(customerId);
       
    }
    
   
    
    public List<Customer> getCustomerssByEnabled() {
         return customerDao.getCustomerssByEnabled();
     }

    public List<Customer> getBlockedCustomers() {
        return customerDao.getBlockedCustomers();
    }
             
 
     
 }