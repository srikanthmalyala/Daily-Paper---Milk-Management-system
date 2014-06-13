/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao.hibernate;

import com.globallogic.dao.CustomerDao;
import com.globallogic.model.Customer;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author srikanth.malyala
 */
@Repository("customerDao")
public class CustomerDaoHibernate extends GenericDaoHibernate <Customer, Long> implements CustomerDao
{
    
    
public CustomerDaoHibernate()
{
    super(Customer.class);
}



public Customer getCustomerByCustomerId(Long customerId)  {
      List<Customer>  customers=  getHibernateTemplate().find("from Customer where customerId=?",customerId);
      if(customers.isEmpty())
          return null;
      else
       
       return customers.get(0);
        
    }


public Customer getCustomerById(Long id) throws DataAccessException {
       Customer customer= getHibernateTemplate().get(Customer.class, id);
       return customer;
        
    }


 public List<Customer> getCustomerssByEnabled()  {
        return getHibernateTemplate().find("from Customer where account_enabled=1");
    }
 
  public List<Customer> getBlockedCustomers()  {
        return getHibernateTemplate().find("from Customer where deposit<=21");
    }

 
 
 

}







 