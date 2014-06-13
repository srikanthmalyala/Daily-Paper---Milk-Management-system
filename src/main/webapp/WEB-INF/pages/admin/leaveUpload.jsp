<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="upload.title"/></title>
    <meta name="menu" content="UploadMenu"/>
    <v:javascript formName="fileUpload" staticJavascript="false"/>
    <script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
    <script type="text/javascript" src="/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="/scripts/jquery-ui.min.js"></script>
</head>

<spring:bind path="fileUpload.*">
    <c:if test="${not empty status.errorMessages}">
        <div class="error">    
            <c:forEach var="error" items="${status.errorMessages}">
                <img src="<c:url value="/images/iconWarning.gif"/>"
                     alt="<fmt:message key="icon.warning"/>" class="icon" />
                <c:out value="${error}" escapeXml="false"/><br />
            </c:forEach>
        </div>
    </c:if>
</spring:bind>

<div class="separator"></div>

<div class="divStyle"/>
<form:form commandName="fileUpload" method="post" action="leaveUpload" enctype="multipart/form-data"
           onsubmit="return validateFileUpload(this)" id="uploadForm">
    <ul><table class="table1">
            <tr><td colspan="2" class="tdHead">
                    <fmt:message key="menu.selectLeaveFile"/></td></tr>
            <tr><th>
                    <appfuse:label key="uploadForm.forDate" styleClass="desc"/></th>
                    <form:errors path="forDate" cssClass="fieldError"/>
                <td><form:input path="forDate" id="forDate" cssClass="text medium" cssErrorClass="text state error"/></td></tr>
            <tr><th>
                    <appfuse:label key="uploadForm.file" styleClass="desc"/></th>
                    <form:errors path="file" cssClass="fieldError"/>
                <td> <input type="file" name="file" id="file" class="file medium"/></td></tr>

            <tr> <td colspan="2" style="text-align: center;">
                    <input type="submit" name="upload" class="button" onclick="bCancel=false"
                           value="<fmt:message key="button.upload"/>" />
                    <input type="submit" name="cancel" class="button" onclick="bCancel=true"
                           value="<fmt:message key="button.cancel"/>" /></td></tr>
        </table>
    </ul>
</form:form>
</div>
    <p class="message"style="width:45%"><fmt:message key="uploadLeave.message"/></p>

<script type="text/javascript">
    var j = jQuery.noConflict();
    Form.focusFirstElement($('uploadForm'));
    highlightFormElements();
    
    j(function() {                      
        j("#forDate").datepicker({
            minDate:'-1M', maxDate:'+1M',changeMonth:true,changeYear:true,
            showOn: 'both', buttonImageOnly: true, buttonImage: '/images/iconCalendar.gif',
            dateFormat: 'mm/dd/yy'
        });
        j("#forDate").datepicker("setDate", new Date());
    });
     j('#file').bind('change', function() {
        if(this.files[0].size > 2097152){
            alert("The maximum allowed size of file for this application is 2 MB.");
            this.value = null; // Clear the field.
        }
    });
</script>

