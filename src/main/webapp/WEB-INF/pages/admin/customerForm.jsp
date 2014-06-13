<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/scripts/numberOnly.js"%>

<head>
    <title><fmt:message key="paidDetail.title"/></title> 
    <meta name="menu" content="ApplyLeave"/>
    <script language="javascript" src="/scripts/PaginaCollapse.js"></script>
    <v:javascript formName="holdDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
    <script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script type="text/javascript" src="/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="/scripts/jquery-ui.min.js"></script>
</head>
<div class="divStyle">
    <spring:bind path="customer.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="error">
                <c:forEach var="error" items="${status.errorMessages}">
                    <img src="<c:url value="/images/iconWarning.gif"/>"
                         alt="<fmt:message key="icon.warning"/>" class="icon"/>
                    <c:out value="${error}" escapeXml="false"/><br />
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="customer" method="post" onsubmit="return onFormSubmit(this)" id="customerForm">
        <form:hidden path="id"/>
      
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>

       



         <table class="table1">   
                <tr><td colspan="2" class="tdHead">
                        <fmt:message key='customerProfile.heading'/></td></tr>

                

                <c:if test="${admin != 'true'}">       
                    <tr><th>   <appfuse:label styleClass="desc" key="customer.customerid"/></th>
                        <form:errors path="customerId" cssClass="fieldError"/>
                        <td> <form:input path="customerId" id="customerId" cssClass="text large" cssErrorClass="text large error" onkeypress="return numbersOnly(this, event)" style="width: 13.2em"maxlength="19"/>
                        </td></tr>

                  

                    <tr><th> <appfuse:label styleClass="desc" key="customer.createat" /></th>
                        <form:errors path="createdAt" cssClass="fieldError"/>
                        <td><form:input path="createdAt" id="createdAt" cssClass="text medium" cssErrorClass="text state error" style="width: 11.6em"/></td>
                    </c:if>  

                <tr><th>
                        <appfuse:label styleClass="desc" key="customer.firstname"/></th>
                        <form:errors path="firstName" cssClass="fieldError"/>
                    <td><form:input path="firstName" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
                <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.lastname"/></th>
                        <form:errors path="lastName" cssClass="fieldError"/>
                    <td> <form:input path="lastName" id="lastName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td>
                </tr>
                
                
                 <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.phoneno"/></th>
                        <form:errors path="phoneNo" cssClass="fieldError"/>
                    <td> <form:input path="phoneNo" id="phoneNo" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td>
                </tr>
                
                
                
                
                <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.paper"/></th>
                    <form:errors path="papers" cssClass="fieldError"/>
                    <td>   

                       
                        <c:forEach   var="paper" items="${papers}">      
                            <form:checkbox path="papers" value="${paper.paperName}" label="${paper.paperName}"/>
                        </c:forEach>
                       
                     </td>
                    </tr>

                
                
                <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.milk"/></th>
                    <form:errors path="items" cssClass="fieldError"/>
                    <td>   

                       
                        <c:forEach   var="item" items="${items}">      
                            <form:checkbox path="items" value="${item.item}" label="${item.item}"/>
                        </c:forEach>
                       
                     </td>
                    </tr>

                
                 <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.lineone"/></th>
                        <form:errors path="lineOne" cssClass="fieldError"/>
                    <td> <form:input path="lineOne" id="lineOne" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td>
                </tr>
                
                 <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.linetwo"/></th>
                        <form:errors path="lineTwo" cssClass="fieldError"/>
                    <td> <form:input path="lineTwo" id="lineTwo" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td>
                </tr>
                
                
                 <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.linethree"/></th>
                        <form:errors path="lineThree" cssClass="fieldError"/>
                    <td> <form:input path="lineThree" id="lineTwo" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td>
                </tr>
                
                 <tr>
                    <th> <appfuse:label styleClass="desc" key="customer.deposit"/></th>
                        <form:errors path="deposit" cssClass="fieldError"/>
                    <td> <form:input path="deposit" id="deposit" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td>
                </tr>
                

                <tr><td colspan="2"style="text-align: center;">
                        <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>
                        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>"/>
                    </td></tr>
                
                
            </table>
      
    </form:form>

   
</div>



<script type="text/javascript">
    var j = jQuery.noConflict();
    Form.focusFirstElement($('customerForm'));   
   
    
    j(document).ready(function(){     
        j("#createdAt").datepicker({       
          
            dateFormat: 'mm/dd/yy',changeMonth:true,changeYear:true,
            showOn: 'both', buttonImageOnly: true, buttonImage: '/images/iconCalendar.gif'
           
        });
      
   });
</script>
