/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.webapp.controller;

import com.globallogic.model.User;
import com.globallogic.webapp.util.RequestUtil;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Simple Class to change Password.
 *
 * @author sheetal.sisodiya
 */
@Controller
@RequestMapping("/changePassword*")
public class ChangePasswordFormController extends BaseFormController {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ChangePasswordFormController() {
        setCancelView("redirect:/changePassword");
        setSuccessView("redirect:/activeUser");
    }

    @ModelAttribute
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    protected User showForm(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return getUserManager().get(new Long(id));
        }
        return new User();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(User user, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(user, errors);
        }

        Locale locale = request.getLocale();
        String encodePassword = passwordEncoder.encodePassword(user.getOldPassword(), null); //encode password

        User currentUser = getUserManager().getUserByUserName(getRemoteEmpId());//get current user detail
        String password = currentUser.getPassword();

        //check encoded password and DB save password
        if (!encodePassword.equals(password)) {
            saveError(request, getText("user.passwordError", locale));//if password not matched.......
            return getCancelView();
        }

        try {
            currentUser.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
            getUserManager().savePassword(currentUser);
            saveMessage(request, getText("user.passwordChange", locale));
            // Send an account information e-mail
            message.setSubject(getText("signup.email.subject", locale));
            try {
                sendUserMessage(user, getText("user.email.message", user.getFullName(), locale),
                        RequestUtil.getAppURL(request));
            } catch (MailException me) {
                saveError(request, me.getCause().getLocalizedMessage());
            }
            response.sendRedirect("/activeUser?show=true&id=" + currentUser.getId());
        } catch (AccessDeniedException ade) {
            // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
            log.warn(ade.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        return null;
    }
}