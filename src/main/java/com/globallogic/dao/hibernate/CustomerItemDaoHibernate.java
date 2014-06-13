/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao.hibernate;

import com.globallogic.dao.CustomerItemDao;
import com.globallogic.model.CustomerItem;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Srikanth.Malyala
 */
@Repository("customerItemDao")
public class CustomerItemDaoHibernate extends GenericDaoHibernate<CustomerItem, Long> implements CustomerItemDao{

    public CustomerItemDaoHibernate() {
        super(CustomerItem.class);
    }
    
    
    
 
    public void deleteItemsByCustomerId(long customerId) {
        List<CustomerItem> items = getHibernateTemplate().find("from CustomerItem where customer_id=?",customerId);       
         getHibernateTemplate().deleteAll(items);
    
    }

    public List<String> getItemsByCustomerId(long customerId) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        session.beginTransaction();
     //  SQLQuery sqlQuery= session.createSQLQuery(" select i.item from Item i,CustomerItem ci where i.id=ci.item_id and ci.customer_id="+customerId);
       
         return getHibernateTemplate().find(" select i.item from Item i,CustomerItem ci where i.id=ci.itemId and ci.customer_id=?",customerId);
    
    }
 
}

 