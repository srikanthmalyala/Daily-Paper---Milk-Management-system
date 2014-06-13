///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.globallogic.webapp.controller;
//
//import com.globallogic.Constants;
//import com.globallogic.model.FileParsing;
//import com.globallogic.model.Role;
//
//import com.globallogic.model.User;
//import com.globallogic.service.UserExistsException;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// *
// * @author XPMUser
// */
//@Controller
//@RequestMapping("/admin/userUpload*")
//public class UserUploadController extends BaseFormController {
//
//    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//    List<FileParsing> fileParse = new ArrayList<FileParsing>();
//
//    public UserUploadController() {
//        setCancelView("redirect:/admin/users");
//        setSuccessView("fileReport");
//    }
//
//    @ModelAttribute
//    @RequestMapping(method = RequestMethod.GET)
//    public FileUpload showForm() {
//        return new FileUpload();
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ModelAndView onSubmit(FileUpload fileUpload, BindingResult errors, HttpServletRequest request, Map model)
//            throws Exception {
//        fileParse.clear();
//
//        if (request.getParameter("cancel") != null) {
//            return new ModelAndView(getCancelView());
//        }
//
//        if (validator != null) { // validator is null during testing
//            validator.validate(fileUpload, errors);
//            if (errors.hasErrors()) {
//                return new ModelAndView("admin/userUpload");
//            }
//        }
//
//        // validate a file was entered
//        if (fileUpload.getFile().length == 0) {
//            Object[] args = new Object[]{getText("uploadForm.file", request.getLocale())};
//            errors.rejectValue("file", "errors.required", args, "File");
//            return new ModelAndView("admin/userUpload");
//        }
//
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
//        String contentType = file.getContentType();
//        if (contentType.equals("application/vnd.ms-excel") || contentType.equals("text/plain")
//                || contentType.equals("text/csv") || contentType.equals("text/tsv")) {
//
//            InputStream stream = file.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
//            String line = " ";
//            int count = 0;
//            Long empId;
//            Locale locale = request.getLocale();
//            while (line != null) {
//                while ((line = br.readLine()) != null) {
//                    String token[] = line.split(",");
//                    count++;
//
//                    if (token.length != 9) {
//                        parse(count, line, "9 field required to upload user.");
//                        break;
//                    }
//
//                    try {
//                        empId = Long.parseLong(token[0]);
//                    } catch (Exception e) {
//                        parse(count, line, "Employee id is not a number");
//                        break;
//                    }
//
//                    String email = token[3];
//                    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//                    Matcher matcher = pattern.matcher(email);
//                    if (!matcher.matches()) {
//                        parse(count, line, "Email is Invalid");
//                        break;
//                    }
//
//                    if (!(token[5].equalsIgnoreCase("user") || token[5].equalsIgnoreCase("admin"))) {
//                        parse(count, line, "Role Type  is invalid");
//                        break;
//                    }
//
//                    float leaves;
//                    try {
//                        leaves = Float.parseFloat(token[6]);
//                    } catch (Exception e) {
//                        parse(count, line, "Leaves taken is not a number");
//                        break;
//                    }
//
//                    if (!(token[7].equalsIgnoreCase("5d") || token[7].equalsIgnoreCase("4d"))) {
//                        parse(count, line, "week type is invalid");
//                        break;
//                    }
//
//                    if (!(token[8].equalsIgnoreCase("day") || token[8].equalsIgnoreCase("night"))) {
//                        parse(count, line, "shift type is invalid");
//                        break;
//                    }
//
//                    if (token[5].equalsIgnoreCase("user")) {  //set role
//                        token[5] = Constants.USER_ROLE;
//                    } else {
//                        token[5] = Constants.ADMIN_ROLE;
//                    }
//
//                    User user = new User();
//                    user.setEmpId(empId);
//                    user.setFirstName(token[1]);
//                    user.setLastName(token[2]);
//                    user.setEmail(email);
//                    user.setPassword(token[4]);
//                    user.setEnabled(true);                 
//                    user.setPasswordHint("csv");
//                    user.setCreateAt(fileUpload.getForDate());
//                    Set<Role> role = new HashSet<Role>();
//                    Role r = new Role(token[5]);
//                    role.add(r);
//                    getRoleManager().save(r);
//                    user.addRole(getRoleManager().getRole(token[5]));
//                   
//                    
//                    try {
//                        getUserManager().saveUser(user);
//                        
//                        saveMessage(request, getText("user.updated.byAdmin", user.getFullName(), locale));
//                    } catch (UserExistsException exception) {
//                        parse(count, line, exception.getMessage());
//                    }
//                }
//            }
//            //close the stream
//            stream.close();
//
//            // place the data into the request for retrieval on next page
//            request.setAttribute("fileName", file.getOriginalFilename());
//            request.setAttribute("contentType", file.getContentType());
//            request.setAttribute("size", file.getSize() + " bytes");
//        } else {
//            parse(1, null, "Only CSV file can be uploaded");
//        }
//        if (fileParse.isEmpty()) {
//            return new ModelAndView("uploadDisplay");
//        } else {
//            model.put(getSuccessView(), fileParse);
//            return new ModelAndView(getSuccessView(), "fileParsingList", fileParse);
//        }
//    }
//
//    private void parse(int count, String line, String reason) {
//        FileParsing fp = new FileParsing(count, line, reason);
//        fileParse.add(fp);
//    }
//}