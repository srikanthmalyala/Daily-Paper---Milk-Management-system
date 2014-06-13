/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service;

import com.globallogic.dao.HoldDetailDao;
import com.globallogic.model.HoldDetail;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author sheetal.sisodiya
 */
public interface HoldDetailManager extends GenericManager<HoldDetail, Long> {

    /**
     * Convenience method for testing - allows you to mock the DAO and set it on
     * an interface.
     *
     * @param holdDetailDao the HoldDetailDao implementation to use
     */
    void setHoldDetailDao(HoldDetailDao holdDetailDao);

    /**
     * Finds a holdDetail of user by their empId.
     *
     * @param empId the user's empId used to login
     * @return List of hold object
     * @throws
     * org.springframework.security.core.userdetails.UsernameNotFoundException
     * exception thrown when hold not found
     */
    
    List<HoldDetail> getHoldByCustomerId(Long customerId) throws UsernameNotFoundException;
    
    
  
}
