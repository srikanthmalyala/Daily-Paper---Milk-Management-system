/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.model.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rajeshvarada
 */

@Controller
@RequestMapping("/admin/payment")
public class PaymentController extends BaseFormController {
    
    public void addPayment(){
    }
    @RequestMapping(method= RequestMethod.GET,value="additonForm")
    public ModelAndView showAddPaymentForm(){
        
        return new ModelAndView("additionForm", "addForm", new Payment());
    }
}
