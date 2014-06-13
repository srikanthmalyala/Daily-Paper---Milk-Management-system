package com.globallogic.webapp.controller;

import com.csvreader.CsvReader;
import com.globallogic.model.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class to upload Files.
 * <p/>
 * <p> <a href="FileUploadFormController.java.html"><i>View Source</i></a> </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/admin/billUpload*")
public class BillUploadController extends BaseFormController {

    public BillUploadController() {
        setCancelView("redirect:/admin/users");
        setSuccessView("fileReport");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public FileUpload showForm() {
        return new FileUpload();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(FileUpload fileUpload, BindingResult errors, HttpServletRequest request, Map model)
            throws Exception {
        List<FileParsing> fileParse = new ArrayList<FileParsing>();
        fileParse.clear();

        if (request.getParameter("cancel") != null) {
            return new ModelAndView(getCancelView());
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fileUpload, errors);
            if (errors.hasErrors()) {
                return new ModelAndView("admin/leaveUpload");
            }
        }

        // validate a file was entered
        if (fileUpload.getFile().length == 0) {
            Object[] args = new Object[]{getText("uploadForm.file", request.getLocale())};
            errors.rejectValue("file", "errors.required", args, "File");
            return new ModelAndView("admin/leaveUpload");
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");



        String contentType = file.getContentType();
        if (file.getSize() > 2097152) {//Validation for upload file size
            saveError(request, "You can upload upto 2Mb size file,Your file size exceeds to 2 MB");
            return null;
        }
        if (contentType.equals("application/vnd.ms-excel") || contentType.equals("text/plain")
                || contentType.equals("text/csv") || contentType.equals("text/tsv")) {

            //retrieve the file data
            InputStream stream = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            CsvReader reader = new CsvReader(br);
            reader.setTrimWhitespace(true);
            int count = 1;
            boolean line = true;
            reader.readHeaders();

            if (reader.getHeaderCount() != 2) {
                fileParse.add(new FileParsing(count, reader.getRawRecord(), "Required Headers 2 to upload leave"));
                line = false;
            }
            String exp_headers = "customerid,paidAmount";
            String header = reader.getRawRecord().trim().toLowerCase().replace("_", "");
            if (line == true && !header.equalsIgnoreCase(exp_headers)) {
                fileParse.add(new FileParsing(count, reader.getRawRecord(), "Required headers doesn't match"));
                line = false;
            }

            Long custId;
            Locale locale = request.getLocale();
            reader.skipLine();
            String customerId;
            float paidAmount;
            while (line != false) {
                while (line = reader.readRecord()) {
                    String record = reader.getRawRecord();
                    customerId = reader.get(0);
                    
                    if (reader.getColumnCount() != 2) {
                        fileParse.add(new FileParsing(count, record, "Required Fields 2 to upload leave"));
                        break;
                    }


                    try {
                        if (customerId.length() > 18) {
                            fileParse.add(new FileParsing(count, record, "Customer id should have less than or equal to 18 digit"));
                            break;
                        }
                        custId = Long.parseLong(customerId);
                    } catch (NumberFormatException e) {
                        fileParse.add(new FileParsing(count, record, "customer id is not a number"));
                        break;
                    }


                    try {

                        paidAmount = Integer.parseInt(reader.get(1));
                    } catch (NumberFormatException nae) {
                        fileParse.add(new FileParsing(count, record, "Paid Amount is not a number"));
                        break;
                    }

                    Customer customer = getCustomerManager().getCustomerByCustomerId(custId);

                    if (customer == null) {
                        fileParse.add(new FileParsing(count, record, "Customer doesnt exist with ID" + customerId));
                        break;

                    }

                    PaidDetail pd = new PaidDetail();
                    pd.setCustomer(customer);
                    pd.setCustomerId(custId);
                    pd.setPaidAmount(paidAmount);
                    pd.setPaidDate(new Date());

                    getPayBillManager().save(pd);


                    saveMessage(request, getText("leaveDetail.added", customer.getFullName(), locale));

                }
            }
            //close the stream
            stream.close();

            // place the data into the request for retrieval on next page
            request.setAttribute("fileName", file.getOriginalFilename());
            request.setAttribute("contentType", file.getContentType());
            request.setAttribute("size", file.getSize() + " bytes");
        } else {
            fileParse.add(new FileParsing(1, null, "only csv file can be uploaded"));
        }

        if (fileParse.isEmpty()) {
            return new ModelAndView("uploadDisplay");
        } else {
            model.put(getSuccessView(), fileParse);
            return new ModelAndView(getSuccessView(), "fileParsingList", fileParse);
        }
    }
}
