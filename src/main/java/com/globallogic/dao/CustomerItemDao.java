/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao;

import com.globallogic.model.CustomerItem;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Srikanth.Malyala
 */
public interface CustomerItemDao extends GenericDao<CustomerItem, Long> {
    
   public void deleteItemsByCustomerId(long customerId);
   public List<String> getItemsByCustomerId(long customerId);
    
}
