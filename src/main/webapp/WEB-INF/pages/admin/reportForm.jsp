<%-- 
    Document   : reportForm
    Created on : 28 Aug, 2012, 12:31:19 PM
    Author     : sheetal.sisodiya
--%>

<%@ include file="/common/taglibs.jsp"%>
<%@include file="/scripts/numberOnly.js" %>

<head>
    <title><fmt:message key="report.title"/></title>
    <meta name="menu" content="Reports"/>
    <script language="javascript" src="/scripts/PaginaCollapse.js"></script>
    <script type="text/javascript" src="/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="/scripts/jquery-ui.min.js"></script>
</head>
<div class="divStyle">
    <form:form commandName="leaveDetail" method="post" action="reportForm" id="reportForm" onsubmit="return onSubmit(this)">
        <form:errors path="*" cssClass="error" element="div"/>
        <form:hidden path="id"/>
        <ul><table class="table1">
            <tr><td colspan="2"class="tdHead">
                            <fmt:message key="report.title"/></td></tr>
            <tr><th><appfuse:label styleClass="desc" key="leaveDetail.empId"/></th>
                <form:errors path="employeeId" cssClass="fieldError"/>
                <td> <form:input path="employeeId" id="employeeId" cssClass="text medium" cssErrorClass="text medium error" maxlength="50" 
                            onkeypress="return numbersOnly(this, event)"/></td></tr>           
            
            <tr><th> <appfuse:label styleClass="desc" key="leaveDetail.leaveFrom"/></th>
                <form:errors path="leaveFrom" cssClass="fieldError"/>
                <td> <form:input path="leaveFrom" id="leaveFrom" cssClass="text medium" cssErrorClass="text medium error"style="width:11.6em" maxlength="50"/></td></tr>
            
            <tr><th>
                <appfuse:label styleClass="desc" key="leaveDetail.leaveTo"/></th>
                <form:errors path="leaveTo" cssClass="fieldError"/>
                <td> <form:input path="leaveTo" id="leaveTo" cssClass="text medium" cssErrorClass="text medium error" style="width:11.6em"maxlength="50"/></td></tr>
            

            <tr><td colspan="2"style="text-align: center;">
                <input type="submit" value="<fmt:message key="button.search"/>"/></td></tr>
            </table>
        </ul>
    </form:form>
</div>

<script type="text/javascript">
    var j = jQuery.noConflict();
    Form.focusFirstElement($('reportForm'));
    
    j(document).ready(function(){     
        j("#leaveFrom").datepicker({        
            dateFormat: 'mm/dd/yy',changeMonth:true,changeYear:true,
            showOn: 'both', buttonImageOnly: true, buttonImage: '/images/iconCalendar.gif',
            onSelect: function(selected)         
            {
                j("#leaveTo").datepicker("option", "minDate", selected);
            }
        });
        j("#leaveFrom").datepicker("setDate", new Date());
                                
        j("#leaveTo").datepicker({                            
            dateFormat: 'mm/dd/yy',changeMonth:true,changeYear:true,
            showOn: 'both', buttonImageOnly: true, buttonImage: '/images/iconCalendar.gif',
            onSelect: function(selected)
            {
                j("#leaveFrom").datepicker("option", "maxDate", selected);
            }
        }); 
        j("#leaveTo").datepicker("setDate", new Date());
    });

</script>
