/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.Constants;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author oasis
 */
@Controller
@RequestMapping("/admin/blockedCustomers*")

public class BlockedCustomers extends BaseFormController{
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request)  {
          
          
         return new ModelAndView("/admin//blockList",Constants.BLOCK_LIST,getCustomerManager().getBlockedCustomers());

      }    
    
}
