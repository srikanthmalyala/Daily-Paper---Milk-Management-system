<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/scripts/numberOnly.js"%>

<div class="divStyle">
<form:form commandName="paper" method="post" onsubmit="return onFormSubmit(this)" id="customerForm" action="/admin/item/paperSubmit">
        <form:hidden path="id"/>
      
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>

       



        <ul> <table class="table1">   
                <tr><td colspan="2" class="tdHead">
                        <fmt:message key='customerProfile.heading'/></td></tr>

                

                
                <tr><th>
                        <appfuse:label styleClass="desc" key="paper.papername"/></th>
                        <form:errors path="paperName" cssClass="fieldError"/>
                    <td><form:input path="paperName" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            
            <tr><th>
                        <appfuse:label styleClass="desc" key="paper.papername"/></th>
                        <form:errors path="sunday" cssClass="fieldError"/>
                    <td><form:input path="sunday" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            <tr><th>
                        <appfuse:label styleClass="desc" key="paper.monday"/></th>
                        <form:errors path="monday" cssClass="fieldError"/>
                    <td><form:input path="monday" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            <tr><th>
                        <appfuse:label styleClass="desc" key="paper.tuesday"/></th>
                        <form:errors path="tuesday" cssClass="fieldError"/>
                    <td><form:input path="tuesday" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            
            
            <tr><th>
                        <appfuse:label styleClass="desc" key="paper.thursday"/></th>
                        <form:errors path="wednesday" cssClass="fieldError"/>
                    <td><form:input path="wednesday" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            
            
            <tr><th>
                        <appfuse:label styleClass="desc" key="paper.wednesday"/></th>
                        <form:errors path="thursday" cssClass="fieldError"/>
                    <td><form:input path="thursday" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            
            <tr><th>
                        <appfuse:label styleClass="desc" key="paper.friday"/></th>
                        <form:errors path="friday" cssClass="fieldError"/>
                    <td><form:input path="friday" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            <tr><th>
                        <appfuse:label styleClass="desc" key="paper.saturday"/></th>
                        <form:errors path="saturday" cssClass="fieldError"/>
                    <td><form:input path="saturday" id="firstName" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
            
             <tr><td colspan="2"style="text-align: center;">
                        <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>
                        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>"/>
                    </td></tr>
            
            </table>
        </ul>
    </form:form>

    <script type="text/javascript">
        var j = jQuery.noConflict();
    
        Form.focusFirstElement($('customerForm'));
        highlightFormElements();

        j(function() {                      
            j("#createAt").datepicker({
                minDate:'-1M', maxDate:'+1M',changeMonth:true,changeYear:true,
                showOn: 'both', buttonImageOnly: true, buttonImage: '/images/iconCalendar.gif',
                dateFormat: 'yy-mm-dd'
            });
            j("#createAt").datepicker("setDate", new Date());
        });

       

        <!-- This is here so we can exclude the selectAll call when roles is hidden -->
      
    </script>
</div>