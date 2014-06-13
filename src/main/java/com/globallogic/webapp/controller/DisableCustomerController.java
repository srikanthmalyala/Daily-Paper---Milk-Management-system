/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.Constants;
import com.globallogic.model.Customer;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author srikanth.malyala
 */
@Controller
@RequestMapping("/disableCustomer*")
public class DisableCustomerController extends BaseFormController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, Map model) throws Exception {

       if (request.getParameter("id") != null) {
            String id = request.getParameter("id");
            Customer customer=getCustomerManager().get(Long.parseLong(id));
            customer.setAccountEnabled(false);
            getCustomerManager().save(customer);          
            List<Customer> customersByEnabled=getCustomerManager().getCustomerssByEnabled();
            return new ModelAndView("admin/customerList", Constants.CUSTOMER_LIST, customersByEnabled);
        }
        return null;
    }
}
