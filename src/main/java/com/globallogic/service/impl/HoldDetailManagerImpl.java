/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service.impl;

import com.globallogic.dao.HoldDetailDao;
import com.globallogic.model.HoldDetail;
import com.globallogic.service.HoldDetailManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of HoldDetailManager interface.
 *
 * @author sheetal.sisodiya
 */
@Service("holdDetailManager")
public class HoldDetailManagerImpl extends GenericManagerImpl<HoldDetail, Long> implements HoldDetailManager {

    private HoldDetailDao holdDetailDao;

    @Autowired
    public void setHoldDetailDao(HoldDetailDao holdDetailDao) {
        this.dao = holdDetailDao;
        this.holdDetailDao = holdDetailDao;
    }

    @Autowired
    public HoldDetailManagerImpl(HoldDetailDao holdDetailDao) {
        super(holdDetailDao);
        this.holdDetailDao = holdDetailDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<HoldDetail> getHoldByCustomerId(Long customerId) throws UsernameNotFoundException {
        return holdDetailDao.getHoldByCustomerId(customerId);
    }

  
    
   
}
