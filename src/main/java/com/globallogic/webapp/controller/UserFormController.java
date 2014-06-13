//package com.globallogic.webapp.controller;
//import com.globallogic.Constants;
//import com.globallogic.model.Role;
//
//import com.globallogic.model.User;
//import com.globallogic.service.UserExistsException;
//import com.globallogic.service.UserManager;
//import com.globallogic.webapp.util.RequestUtil;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.mail.MailException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationTrustResolver;
//import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.ServletRequestDataBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * Implementation of <strong>SimpleFormController</strong> that interacts with
// * the {@link UserManager} to retrieve/persist values to the database.
// *
// * <p><a href="UserFormController.java.html"><i>View Source</i></a>
// *
// * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
// */
//@Controller
//@RequestMapping("/admin/userForm*")
//public class UserFormController extends BaseFormController {
//
//    public UserFormController() {
//        setCancelView("redirect:/activeUser");
//        setSuccessView("redirect:/admin/users");
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String onSubmit(User user, BindingResult errors, HttpServletRequest request,
//            HttpServletResponse response)
//            throws Exception {
//        if (request.getParameter("cancel") != null) {
//            return getSuccessView();
//        }
//
//        if (validator != null) { // validator is null during testing
//            validator.validate(user, errors);
//            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
//                return "admin/userForm";
//            }
//        }
//
//        log.debug("entering 'onSubmit' method...");
//        Locale locale = request.getLocale();
//
//        // only attempt to change roles if user is admin for other users,
//        // showForm() method will handle populating
//        if (request.isUserInRole(Constants.ADMIN_ROLE)) {
//            String userRole = request.getParameter("userRoles");
//
//            if (userRole != null) {
//                user.getRoles().clear();
//                user.addRole(getRoleManager().getRole(userRole));
//            }
//        }
//        
//        Integer originalVersion = user.getVersion();
//       
//       
//
//        try {
//            user.setEnabled(true);
//            getUserManager().saveUser(user);
//            if (StringUtils.isBlank(request.getParameter("version"))) {
//              
//            }
//        } catch (AccessDeniedException ade) {
//            // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
//            log.warn(ade.getMessage());
//            response.sendError(HttpServletResponse.SC_FORBIDDEN);
//            return null;
//        } catch (UserExistsException e) {
//            errors.rejectValue("empId", "errors.existing.user",
//                    new Object[]{user.getEmpId(), user.getEmail()}, "duplicate user");
//
//            // redisplay the unencrypted passwords
//            user.setPassword(user.getConfirmPassword());
//            // reset the version # to what was passed in
//            user.setVersion(originalVersion);
//            return "admin/userForm";
//        }
//
//        if (!StringUtils.equals(request.getParameter("from"), "list")) {
//            saveMessage(request, getText("user.saved", user.getFullName(), locale));
//            // return to main Menu
//            response.sendRedirect("/activeUser?show=true&id=" + user.getId());
//        } else {
//            if (StringUtils.isBlank(request.getParameter("version"))) {
//                saveMessage(request, getText("user.added", user.getFullName(), locale));
//                // Send an account information e-mail
//                message.setSubject(getText("signup.email.subject", locale));
//                try {
//                    sendUserMessage(user, getText("user.email.message", user.getFullName(), locale),
//                            RequestUtil.getAppURL(request));
//                } catch (MailException me) {
//                    saveError(request, me.getCause().getLocalizedMessage());
//                }
//                response.sendRedirect("/activeUser?show=true&id=" + user.getId());
//            } else {
//                saveMessage(request, getText("user.updated.byAdmin", user.getFullName(), locale));
//            }
//        }
//        return null;
//    }
//
//    
//    
//    @ModelAttribute
//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
//    protected User showForm(HttpServletRequest request, HttpServletResponse response, Map model)
//            throws Exception {
//
//        // If not an administrator, make sure user is not trying to add or edit another user
//        if (!request.isUserInRole(Constants.ADMIN_ROLE) && !isFormSubmission(request)) {
//            if (isAdd(request) || request.getParameter("id") != null) {
//                response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                log.warn("User '" + request.getRemoteUser() + "' is trying to edit user with id '"
//                        + request.getParameter("id") + "'");
//                throw new AccessDeniedException("You do not have permission to modify other users.");
//            }
//        }
//
//        String admin = "false";
//        if (request.getParameter("admin") != null) {
//            admin = "true";
//        }
//        if (model != null) {
//            if (admin.equals("true")) {
//                String userId = request.getParameter("id");
//                User user = getUserManager().getUser(userId);
//              
//                model.put("admin", "true");
//                model.put("user", user);
//               
//
//            } else {
//                model.put("admin", "false");
//            }
//        }
//
//        if (!isFormSubmission(request)) {
//            String userId = request.getParameter("id");
//
//            // if user logged in with remember me, display a warning that they can't change passwords
//            log.debug("checking for remember me login...");
//
//            AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
//            SecurityContext ctx = SecurityContextHolder.getContext();
//
//            if (ctx.getAuthentication() != null) {
//                Authentication auth = ctx.getAuthentication();
//
//                if (resolver.isRememberMe(auth)) {
//                    request.getSession().setAttribute("cookieLogin", "true");
//
//                    // add warning message
//                    saveMessage(request, getText("userProfile.cookieLogin", request.getLocale()));
//                }
//            }
//
//            User user;
//            if (userId == null && !isAdd(request)) {
//                user = getUserManager().getUserByEmpId(getRemoteEmpId());
//            } else if (!StringUtils.isBlank(userId) && !"".equals(request.getParameter("version"))) {
//                user = getUserManager().getUser(userId);
//            } else {
//                user = new User();
//                user.addRole(new Role(Constants.USER_ROLE));
//            }
//            user.setConfirmPassword(user.getPassword());
//            return user;
//        } else {
//            // populate user object from database, so all fields don't need to be hidden fields in form
//            return getUserManager().getUser(request.getParameter("id"));
//        }
//    }
//
//    private boolean isFormSubmission(HttpServletRequest request) {
//        return request.getMethod().equalsIgnoreCase("post");
//    }
//
//    protected boolean isAdd(HttpServletRequest request) {
//        String method = request.getParameter("method");
//        return (method != null && method.equalsIgnoreCase("add"));
//    }
//
//    @Override
//    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(true);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }
//}
