package com.globallogic.webapp.controller;

import com.globallogic.model.User;
import com.globallogic.webapp.util.RequestUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Simple class to retrieve and send a password hint to users.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/passwordHint*")
public class PasswordHintController extends BaseFormController {

    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request)
            throws Exception {
        log.debug("entering 'handleRequest' method...");

        String userName = request.getParameter("empId");
        MessageSourceAccessor text = new MessageSourceAccessor(messageSource, request.getLocale());

        // ensure that the empId has been sent
        if (userName == null) {
            log.warn("Employee Id not specified, notifying user that it's a required field.");
            request.setAttribute("error", text.getMessage("errors.required", text.getMessage("user.empId")));
            return new ModelAndView("login");
        }

        log.debug("Processing Password Hint...");

        // look up the user's information
        try {
            User user = getUserManager().getUserByUserName(userName);

            StringBuffer msg = new StringBuffer();
            msg.append("Your password hint is: ").append(user.getPasswordHint());
            msg.append("\n\nLogin at: ").append(RequestUtil.getAppURL(request));

            message.setTo(user.getEmail());
            String subject = '[' + text.getMessage("webapp.name") + "] "
                    + text.getMessage("user.passwordHint");
            message.setSubject(subject);
            message.setText(msg.toString());
            mailEngine.send(message);

            saveMessage(request, text.getMessage("login.passwordHint.sent", new Object[]{userName, user.getEmail()}));
        } catch (UsernameNotFoundException e) {
            log.warn(e.getMessage());
            saveError(request, text.getMessage("login.passwordHint.error", new Object[]{userName}));
        } catch (MailException me) {
            log.warn(me.getMessage());
            saveError(request, me.getCause().getLocalizedMessage());
        }

        return new ModelAndView(new RedirectView(request.getContextPath()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void saveError(HttpServletRequest request, String error) {
        List errors = (List) request.getSession().getAttribute("errors");
        if (errors == null) {
            errors = new ArrayList();
        }
        errors.add(error);
        request.getSession().setAttribute("errors", errors);
    }

    // this method is also in BaseForm Controller
    @SuppressWarnings("unchecked")
    @Override
    public void saveMessage(HttpServletRequest request, String msg) {
        List messages = (List) request.getSession().getAttribute(BaseFormController.MESSAGES_KEY);
        if (messages == null) {
            messages = new ArrayList();
        }
        messages.add(msg);
        request.getSession().setAttribute(BaseFormController.MESSAGES_KEY, messages);
    }
}
