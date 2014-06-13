/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.model.Customer;
import com.globallogic.model.PaidDetail;
import com.globallogic.model.User;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

/**
 *
 * @author oasis
 */


@Controller
@RequestMapping("/admin/payForm*")
public class PayBill extends BaseFormController {

    public PayBill() {
        
        setSuccessView("/admin/customers");
        setCancelView("/admin/payForm");
    }

    
   
    
    
     @ModelAttribute
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    protected PaidDetail showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return getPayBillManager().get(Long.parseLong(id));
        }
        return new PaidDetail();
     }
     
      @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PaidDetail paidDetail, BindingResult errors,HttpServletRequest request) {      
        
           
          if (errors.hasErrors()) {
            return getCancelView();
        }
             
             Locale locale = request.getLocale();
       
    
            Customer customerByEmpId = getCustomerManager().getCustomerByCustomerId(paidDetail.getCustomerId());
            if(customerByEmpId==null)
            {
           saveError(request, getText("paybill.nocustomer",locale));
            return getCancelView();

            }
            paidDetail.setCustomer(customerByEmpId);
            
         
        getPayBillManager().save(paidDetail);
        return "/admin/customers";
    }
    
    
}
