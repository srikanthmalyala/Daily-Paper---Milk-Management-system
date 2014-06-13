/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.model.Customer;
import com.globallogic.model.CustomerItem;
import com.globallogic.model.Item;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author srikanth.malyala
 */
@Controller
@RequestMapping("/admin/customerForm*")
public class CustomerFormController extends BaseFormController {

    public CustomerFormController() {
        this.successView = "/admin/customers";
    }

    @ModelAttribute
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    protected Customer showForm(HttpServletRequest request, Map model)
            throws Exception {
        String id = request.getParameter("id");

        model.put("papers", getPaperManager().getAll());
        model.put("items", getItemManager().getAll());


        if (!StringUtils.isBlank(id)) {
            return getCustomerManager().get(Long.parseLong(id));

        }

        return new Customer();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Customer customer, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {           
            getCustomerManager().merge(customer);
            
            //delete all existing relationa in customer_item
            getCustomerItemManager().deleteItemsByCustomerId(customer.getCustomerId());
        } else {
            getCustomerManager().save(customer);
        }


        //adds to the customer_item table
        List<String> items = customer.getItems();
        for (String itemName : items) {
            Item item = getItemManager().getItemByName(itemName);
            CustomerItem custItem = new CustomerItem();
            custItem.setCustomer(customer);
            custItem.setItem(item);
            custItem.setCustomerId(customer.getId());
            custItem.setItemId(item.getId());
            getCustomerItemManager().save(custItem);
        }

        
        response.sendRedirect(successView);
        return "";
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
