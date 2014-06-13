
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao.hibernate;

import com.globallogic.dao.HoldDetailDao;
import com.globallogic.model.HoldDetail;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve HoldDetail objects.
 *
 * @author sheetal.sisodiya
 */
@Repository("holdDetailDao")
public class HoldDetailDaoHibernate extends GenericDaoHibernate<HoldDetail, Long> implements HoldDetailDao {

    /**
     * Constructor that sets the entity to HoldDetail.class.
     */
    public HoldDetailDaoHibernate() {
        super(HoldDetail.class);
    }

  

    public List<HoldDetail> getHoldByCustomerId(Long customerId) {
        List hold = getHibernateTemplate().find("from HoldDetail where customer_id=?", customerId);
        return hold;

    }

    /**
     * {@inheritDoc}
     */
  

    public void holdUpdate(HoldDetail ld) {
        getHibernateTemplate().saveOrUpdate(ld);
    }

    /**
     * {@inheritDoc}
     */
    
   

   

    
}
