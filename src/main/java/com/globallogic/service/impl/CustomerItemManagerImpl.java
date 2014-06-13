/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service.impl;

import com.globallogic.dao.CustomerItemDao;
import com.globallogic.model.CustomerItem;
import com.globallogic.model.HoldDetail;
import com.globallogic.service.CustomerItemManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Srikanth.Malyala
 */


@Service("customerItemManager")
public class CustomerItemManagerImpl extends GenericManagerImpl<CustomerItem, Long> implements CustomerItemManager{
    
    public CustomerItemDao customerItemDao;
    
    @Autowired
    public void setCustomerIteMDao(CustomerItemDao customerItemDao) {
        this.dao = customerItemDao;
        this.customerItemDao = customerItemDao;
    }

    @Autowired
    public CustomerItemManagerImpl(CustomerItemDao customerItemDao) {
        super(customerItemDao);
        this.customerItemDao = customerItemDao;
    }
    
     public void deleteItemsByCustomerId(long customerId){
         
        customerItemDao.deleteItemsByCustomerId(customerId);
     }
     
     
     public List<String> getItemsByCustomerId(long customerId){
         return customerItemDao.getItemsByCustomerId(customerId);
     }
     
}
