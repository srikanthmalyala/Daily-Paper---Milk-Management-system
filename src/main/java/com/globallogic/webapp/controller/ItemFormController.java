/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.model.HoldDetail;
import com.globallogic.model.Item;
import com.globallogic.model.Paper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author srikanth.malyala
 */


@Controller
@RequestMapping("/admin/item")
public class ItemFormController extends BaseFormController{
   

  @RequestMapping(value="paperForm",method= RequestMethod.GET) 
  public ModelAndView showForm(HttpServletRequest request)
    {
         String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {            
            return new ModelAndView("/admin/paperForm", "paper", getPaperManager().get(Long.parseLong(id)));
        }        
        return new ModelAndView("/admin/paperForm", "paper", new Paper());
    }
    
  
   @RequestMapping(value="milkForm",method= RequestMethod.GET) 
  public ModelAndView showMilkForm(HttpServletRequest request)
    {
         String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {            
            return new ModelAndView("/admin/milkForm", "milk", getItemManager().get(Long.parseLong(id)));
        }        
        return new ModelAndView("/admin/milkForm", "milk", new Item());
    }
   
   
  @RequestMapping(value="paperList",method= RequestMethod.GET) 
  public ModelAndView paperList(HttpServletRequest request)
    {
         
        return new ModelAndView("/admin/paperList", "paperList",getPaperManager().getAll());
    }
    
  
  
  @RequestMapping(value="milkList",method= RequestMethod.GET) 
  public ModelAndView milkList(HttpServletRequest request)
    {
         
        return new ModelAndView("/admin/milkList", "milkList",getItemManager().getAll());
    }
   
  
  
  @RequestMapping(value="deletePaperItem",method= RequestMethod.GET) 
  public ModelAndView deletePaperItem(HttpServletRequest request)
    {   
        String id = request.getParameter("id");
        if (!StringUtils.isBlank(id))    
             getPaperManager().remove(Long.parseLong(id));
              
        return new ModelAndView("/admin/paperList", "paperList",getPaperManager().getAll());
    }
  
  @RequestMapping(value="deleteMilkItem",method= RequestMethod.GET) 
  public ModelAndView deleteMilkItem(HttpServletRequest request)
    {   
        String id = request.getParameter("id");
        if (!StringUtils.isBlank(id))    
             getItemManager().remove(Long.parseLong(id));
              
        return new ModelAndView("/admin/milkList", "milkList",getItemManager().getAll());
    }
  
  
   @RequestMapping(value="paperSubmit",method = RequestMethod.POST)
    public ModelAndView onPaperSubmit(Paper paper, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
        
                         
        if (errors.hasErrors()) {
            return new ModelAndView("/admin/paperForm","paperList",paper);
        }
       
         getPaperManager().merge(paper);
         return new ModelAndView("/admin/paperList","paperList",getPaperManager().getAll());
    }
   
   @RequestMapping(value="milkSubmit",method = RequestMethod.POST)
    public ModelAndView onMilkSubmit(Item milk, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {       
                         
        if (errors.hasErrors()) {
            return new ModelAndView("/admin/milkForm","milkList",milk);
        }
        
        
       
         getItemManager().merge(milk);
         return new ModelAndView("/admin/milkList","milkList",getItemManager().getAll());
    }
    
   
   
}
