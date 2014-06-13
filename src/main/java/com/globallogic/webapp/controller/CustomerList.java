package com.globallogic.webapp.controller;

import com.globallogic.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Simple class to retrieve a list of users from the database.
 * <p/>
 * <p> <a href="UserController.java.html"><i>View Source</i></a> </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/admin/customers*")
public class CustomerList extends BaseFormController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest()  {        
        
        
         return new ModelAndView("admin/customerList", Constants.CUSTOMER_LIST, getCustomerManager().getCustomerssByEnabled());
    }
    
}