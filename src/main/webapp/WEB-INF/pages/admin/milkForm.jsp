<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/scripts/numberOnly.js"%>

<div class="divStyle">
    <form:form commandName="milk" method="post" onsubmit="return onFormSubmit(this)" id="milkForm" action="/admin/item/milkSubmit">
        <form:hidden path="id"/>
      
        

       



        <ul> <table class="table1">   
                <tr><td colspan="2" class="tdHead">
                        <fmt:message key='customerProfile.heading'/></td></tr>

                

                
                <tr><th>
                        <appfuse:label styleClass="desc" key="milk.itemname"/></th>
                        <form:errors path="item" cssClass="fieldError"/>
                    <td><form:input path="item" id="item" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
                
            
            <tr><th>
                        <appfuse:label styleClass="desc" key="milk.cost"/></th>
                        <form:errors path="cost" cssClass="fieldError"/>
                    <td><form:input path="cost" id="cost" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/></td></tr>
            
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