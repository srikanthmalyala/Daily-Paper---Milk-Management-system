/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.Constants;
import com.globallogic.model.Customer;
import com.globallogic.model.HoldDetail;
import com.globallogic.model.PaidDetail;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Simple class to retrieve a detail of current login user or current updating
 * user from the database.
 *
 * 
 */
@Controller
@RequestMapping("/activeUser*")
public class ActiveUserController extends BaseFormController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, Map model) throws Exception {

         
      
        
    
      if (request.getParameter("id")!=null&& request.getParameter("show").equals("true")) 
        {  
            Long cID=Long.parseLong(request.getParameter("id"));
            Customer customer = getCustomerManager().get(cID);
          List<PaidDetail> bills=getPayBillManager().getBillByCustomerId(cID);
          List<HoldDetail> holds=getHoldDetailManager().getHoldByCustomerId(cID);
       
          model.put("items",getCustomerItemManager().getItemsByCustomerId(customer.getCustomerId()));
          model.put("holds", holds);
          model.put("bills", bills);
          return new ModelAndView("/activeUser", Constants.CUSTOMER_LIST, customer);           
         
        }
else 
           return new ModelAndView("/admin/customerList", Constants.CUSTOMER_LIST, getCustomerManager().getAll());
        
            
           
        }
    }
