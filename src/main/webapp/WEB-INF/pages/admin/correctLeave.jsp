
<%@ include file="/common/taglibs.jsp"%>
<%@include file="/scripts/numberOnly.js" %>

<head>
    <v:javascript formName="leaveCorrection" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
    <script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

    <title><fmt:message key="leaveCorrection.title"/></title> 
    <meta name="menu" content="LeaveCorrection"/>
</head>
<div class="divStyle">

    <form:form commandName="leaveCorrection" method="post" action="correctLeave" id="correctLeave" onsubmit="return validateLeaveCorrection(this)">
        <form:errors path="*" cssClass="error" element="div"/>
        <form:hidden path="id"/>
        <ul><table class="table1">
                <tr><td colspan="2" class="tdHead">
                        <fmt:message key="leaveCorrection.title"/></td></tr>
                <tr>   <th>  <appfuse:label styleClass="desc" key="user.empId"/></th>
                    <form:errors path="employeeId" cssClass="fieldError"/>
                    <td> <form:input path="employeeId" id="employeeId" cssClass="text medium" cssErrorClass="text medium error" maxlength="19" 
                                onkeypress="return numbersOnly(this, event)"/></td>
                </tr>
                <tr><th>
                        <appfuse:label styleClass="desc" key="leaveDetail.days"/></th>
                    <td> <form:select path="days" cssClass="fieldError"  id="days" style="width: 13.3em">
                            <form:option value="">Select Days</form:option>
                            <form:option value="0.5">0.5</form:option>
                            <form:option value="1.0">1.0</form:option>
                            <form:option value="1.5">1.5</form:option>
                            <form:option value="2.0">2.0</form:option>
                            <form:option value="2.5">2.5</form:option>
                            <form:option value="3.0">3.0</form:option>
                            <form:option value="3.5">3.5</form:option>
                            <form:option value="4.0">4.0</form:option>
                            <form:option value="4.5">4.5</form:option>
                            <form:option value="5.0">5.0</form:option>
                        </form:select></td></tr>

                <tr>
                    <th> <appfuse:label styleClass="desc" key="leaveDetail.leaveType" /></th>
                    <td> <form:select path="leaveType" cssClass="fieldError"  id="leaveType" style="width: 13.3em">
                            <c:if test="${param.from == 'add'}">
                                <form:option value="AddedLeave">Add Leave</form:option>
                            </c:if>
                            <c:if test="${param.from == 'remove'}">
                                <form:option value="RemovedLeave">Remove Leave</form:option>
                            </c:if>
                            <c:if test="${param.from != 'remove' && param.from != 'add' }">
                                <form:option value="AddedLeave">Add Leave</form:option>
                            </c:if>
                        </form:select></td>
                </tr>
                <tr><td colspan="2"style="text-align: center;">
                        <input type="submit" class="button" name="save" value="<fmt:message key="button.update"/>"/>
                        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>"/>
                    </td></tr>

            </table>
        </ul>
    </form:form>
</div>
<script type="text/javascript">
    Form.focusFirstElement($('correctLeave'));
</script>
