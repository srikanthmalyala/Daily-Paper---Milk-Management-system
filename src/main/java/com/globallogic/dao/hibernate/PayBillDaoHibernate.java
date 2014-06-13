/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao.hibernate;

import com.globallogic.dao.PayBillDao;
import com.globallogic.model.PaidDetail;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oasis
 */

@Repository("payBillDao")
public class PayBillDaoHibernate extends GenericDaoHibernate<PaidDetail, Long> implements PayBillDao {
    
    
    public PayBillDaoHibernate(){
        super(PaidDetail.class);
    }

    public List<PaidDetail> getBillByCustomerId(Long customerId) {
       List<PaidDetail> bills=getHibernateTemplate().find("from PaidDetail where customer_id=?",customerId);
       if(bills.isEmpty())
             return null;
                   else
           return bills;
    }
    
}
