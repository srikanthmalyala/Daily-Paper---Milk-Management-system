/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service;

import com.globallogic.dao.PayBillDao;
import com.globallogic.model.PaidDetail;
import java.util.List;

/**
 *
 * @author oasis
 */
public interface PayBillManager extends GenericManager<PaidDetail, Long> {
    
    
     void setPayBillDao(PayBillDao   payBillDao);
     List<PaidDetail> getBillByCustomerId(Long customerId);
    
    
}
