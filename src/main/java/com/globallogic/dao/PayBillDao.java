/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao;

import com.globallogic.model.PaidDetail;
import java.util.List;

/**
 *
 * @author oasis
 */
public interface PayBillDao extends  GenericDao<PaidDetail, Long>{
    
    List<PaidDetail> getBillByCustomerId(Long customerId);
    
}
