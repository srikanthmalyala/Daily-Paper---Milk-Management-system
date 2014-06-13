/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.model.Customer;
import com.globallogic.model.HoldDetail;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author srikanth.malyala
 */

@Controller
@RequestMapping("/admin/holdForm*")

public class HoldServiceController extends BaseFormController {

    public HoldServiceController() {
        
        this.cancelView="/admin/holdForm";
        this.successView="/admin/users";
        
    }

    @ModelAttribute
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    protected HoldDetail showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return getHoldDetailManager().get(new Long(id));
        }
        return new HoldDetail();
    }

    
  
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HoldDetail holdDetail, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
         Locale locale = request.getLocale();
                         
        if (errors.hasErrors()) {
            return getCancelView();
        }
       
        try {
            Customer customerByEmpId = getCustomerManager().getCustomerByCustomerId(holdDetail.getCustomerId());
            holdDetail.setCustomer(customerByEmpId);
            System.out.println("in submit form 2");
         } catch (UsernameNotFoundException exception) {
            return getCancelView();
        }
        
      

        double holdFrom = holdDetail.getHoldFrom().getTime();
        double holdTo = holdDetail.getHoldTo().getTime();
        double diff = ((holdTo - holdFrom) / (1000 * 60 * 60 * 24)) + 1;  //Get date diff in btween 2 dates.
        int days = holdDetail.getDays();
       
        
        if (diff != days) { //Date diff and days should be same.
            saveError(request, getText("HoldDetail.dayMisMatch", locale));
            return getCancelView();
        }
        
        getHoldDetailManager().save(holdDetail);        
        return getSuccessView();
    }
}
