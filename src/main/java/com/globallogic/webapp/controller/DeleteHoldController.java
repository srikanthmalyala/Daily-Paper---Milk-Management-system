/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.model.User;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author srikanth.malyala
 */
@Controller
@RequestMapping("/holdActions*")
public class DeleteHoldController extends BaseFormController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, Map model, HttpServletResponse response) throws Exception {
        String id;
        Locale locale = request.getLocale();
        if ((id = request.getParameter("id")) != null && request.getParameter("action").equals("delete")) {
            Long holdId = Long.parseLong(id);
            Long empId = Long.parseLong(request.getParameter("eid"));
            getHoldDetailManager().remove(holdId);
            saveMessage(request, getText("holdDetail.deleted", locale));
            response.sendRedirect("/activeUser?show=true&id=" + empId);
        }
        return null;
    }
}
