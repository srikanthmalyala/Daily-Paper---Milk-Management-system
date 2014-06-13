<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="login.title"/></title>
    <meta name="menu" content="Login"/>
</head>
<div class="divStyle">

    <form method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
          onsubmit="saveEmpId(this);return validateForm(this)">

        <ul><table class="table1">
                <c:if test="${param.error != null}">
                    <li class="error">
                        <img src="${ctx}/images/iconWarning.gif" alt="<fmt:message key='icon.warning'/>" class="icon"/>
                        <fmt:message key="errors.password.mismatch"/>
                        <%--${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}--%>
                    </li>
                </c:if>
                <tr ><td colspan="2" class="tdHead">
                        <fmt:message key="login.title"/></td></tr>
                <tr> 
                    <th>
                        <label for="j_username" class="required desc">
                            <fmt:message key="label.username"/> <span class="req">*</span>
                        </label></th>
                    <td> <input type="text" class="text medium" name="j_username" id="j_username" tabindex="1" /></td></tr>

                <tr> 
                    <th>
                        <label for="j_password" class="required desc">
                            <fmt:message key="label.password"/> <span class="req">*</span>
                        </label></th>
                    <td><input type="password" class="text medium" name="j_password" id="j_password" tabindex="2" /></td></tr>
                <tr>
                    <td> <c:if test="${appConfig['rememberMeEnabled']}">
                    <li>
                        <input type="checkbox" class="checkbox" name="_spring_security_remember_me" id="rememberMe" tabindex="3"/>
                        <label for="rememberMe" class="choice"><fmt:message key="login.rememberMe"/></label>
                    </li>
                </c:if></td>
                <td style="text-align: left;">
                    <input type="submit" class="button" name="login" value="<fmt:message key='button.login'/>" tabindex="4" />
                </td></table>
        </ul>

    </form>

    <%@ include file="/scripts/login.js"%>

    <p><fmt:message key="login.passwordHint"/></p>
</div>