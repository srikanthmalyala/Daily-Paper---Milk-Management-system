<%-- 
    Document   : changepassword
    Created on : 6 Jul, 2012, 12:49:44 PM
    Author     : sheetal.sisodiya
--%>

<%@ include file="/common/taglibs.jsp"%>


<head>
    <script language="javascript" src="/scripts/PaginaCollapse.js"></script>
    <title><fmt:message key="changePassword.title"/></title>
    <meta name="menu" content="ChangePassword"/>
</head>

<div class="divStyle">
    <form:form commandName="user" method="post" action="changePassword" onsubmit="return onFormSubmit(this)" id="changePassword">
        <form:hidden path="id"/>
        <form:hidden path="version"/>
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>

        <c:if test="${cookieLogin == 'true'}">
            <form:hidden path="password"/>
            <form:hidden path="oldPassword"/>
            <form:hidden path="confirmPassword"/>
        </c:if>

        <c:if test="${empty user.version}">
            <input type="hidden" name="encryptPass" value="true"/>
        </c:if>

        <ul><table class="table1">
                <c:if test="${cookieLogin != 'true'}">                
                    <tr ><td colspan="2" class="tdHead">
                            <fmt:message key="changePassword.title"/></td></tr>
                    <tr> 
                        <th>  <appfuse:label styleClass="desc" key="changePassword.oldPassword"/></th>
                        <form:errors path="oldPassword" cssClass="fieldError"/>
                        <td><form:password path="oldPassword" id="oldPassword" cssClass="text medium" cssErrorClass="text medium error" /></td>
                    </tr>
                    <tr>
                        <th> <appfuse:label styleClass="desc" key="user.password"/></th>
                        <form:errors path="password" cssClass="fieldError"/>
                        <td><form:password path="password" id="password" cssClass="text medium" cssErrorClass="text medium error" 
                                       onchange="passwordChanged(this)" showPassword="true" onkeyup='CheckPasswordStrength(this.value);'/> <div id='pwd_strength'></div> </td>                     

                    </tr>
                    <tr>
                       <th><appfuse:label styleClass="desc" key="user.confirmPassword"/></th>
                       <form:errors path="confirmPassword" cssClass="fieldError"/>
                       <td><form:password path="confirmPassword" id="confirmPassword" cssClass="text medium" cssErrorClass="text medium error" showPassword="true"/></td>
                    </tr>
                </c:if>
                <tr><td colspan="2" style="text-align: center;">
                        <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>
                    </td></tr>
            </table>
        </ul>
    </form:form>
</div>
