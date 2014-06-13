/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao;

import com.globallogic.model.HoldDetail;
import java.util.List;

/**
 * HoldDetail Data Access Object (GenericDao) interface.
 *
 * @author sheetal.sisodiya
 */
public interface HoldDetailDao extends GenericDao<HoldDetail, Long> {

  
    
   
    List<HoldDetail> getHoldByCustomerId(Long customerIdId) ; 


    
    
  
     public void holdUpdate(HoldDetail ld);
    
    
}