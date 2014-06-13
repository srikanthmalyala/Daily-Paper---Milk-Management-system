
<%@ include file="/common/taglibs.jsp"%>
<%@include file="/scripts/numberOnly.js" %>

<head>
    <title><fmt:message key="paidDetail.title"/></title> 
    <meta name="menu" content="ApplyLeave"/>
    <script language="javascript" src="/scripts/PaginaCollapse.js"></script>
    <v:javascript formName="holdDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
    <script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script type="text/javascript" src="/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="/scripts/jquery-ui.min.js"></script>
</head>
<v:javascript formName="paidDetail" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

<div class="divStyle"/>
<form:form commandName="paidDetail" method="post"  id="holdForm" onsubmit="return validatePaidDetail(this)">
    <form:errors path="*" cssClass="error" element="div"/>
    <form:hidden path="id"/>
    <ul><table class="table1">
            <tr><td colspan="2" class="tdHead">
                    <fmt:message key="pay.title"/></td></tr>

            <tr> <c:if test="${param.from != 'list'}"><th>    <appfuse:label styleClass="desc" key="pay.customerid"/></th>
                    <form:errors path="customerId" cssClass="fieldError"/>
                    <td>  <form:input path="customerId" id="customerId" cssClass="text medium" cssErrorClass="text medium error" maxlength="19" 
                                onkeypress="return numbersOnly(this, event)"/></td></tr></c:if>
                    <c:if test="${param.from == 'list'}">
                <input type="hidden" name="employeeId" value="${holdDetail.user.empId}"  />
                <tr>
                    <th ><b>Employee Id :</b></th>
                    <td> ${holdDetail.user.empId}</td>    
                </tr>
            </c:if>

            <tr><th>
                    <appfuse:label styleClass="desc" key="pay.date"/></th>
                    <form:errors path="paidDate" cssClass="fieldError"/>
                <td> <form:input path="paidDate" id="holdFrom" cssClass="text medium" cssErrorClass="text medium error" style="width:11.6em"/></td></tr>
            <tr><th>
                    <appfuse:label styleClass="desc" key="pay.amount"/></th>
                    <form:errors path="paidAmount" cssClass="fieldError"/>
                <td> <form:input path="paidAmount" id="amount" cssClass="text medium" cssErrorClass="text medium error" style="width:11.6em"/></td></tr>


            <tr><td colspan="2"style="text-align: center;">
                    <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>
                    <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>"/>
                </td></tr>


        </table>
    </ul>
</form:form>
</div>
<script type="text/javascript">
    var j = jQuery.noConflict();
    Form.focusFirstElement($('holdForm'));   
   
    
    j(document).ready(function(){     
        j("#holdFrom").datepicker({       
          
            dateFormat: 'mm/dd/yy',changeMonth:true,changeYear:true,
            showOn: 'both', buttonImageOnly: true, buttonImage: '/images/iconCalendar.gif'
           
        });
      
   });
</script>
