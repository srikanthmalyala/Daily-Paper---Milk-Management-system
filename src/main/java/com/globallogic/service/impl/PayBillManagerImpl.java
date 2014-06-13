/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service.impl;

import com.globallogic.dao.HoldDetailDao;
import com.globallogic.dao.PayBillDao;
import com.globallogic.model.PaidDetail;
import com.globallogic.service.PayBillManager;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oasis
 */



@Service("payService")
public class PayBillManagerImpl extends GenericManagerImpl<PaidDetail, Long>  implements PayBillManager{
    
    private PayBillDao payDao;
    
      @Autowired
    public void setPayBillDao(PayBillDao  payDao) {
        this.dao = payDao;
        this.payDao = payDao;
    }

    @Autowired
    public    PayBillManagerImpl(PayBillDao payDao) {
        super(payDao);
        this.payDao = payDao;
    }

    public List<PaidDetail> getBillByCustomerId(Long customerId) {
        return payDao.getBillByCustomerId(customerId);
    }
    
    
   

    
}
