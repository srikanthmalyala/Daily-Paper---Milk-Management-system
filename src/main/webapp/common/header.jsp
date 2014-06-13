<%@ include file="/common/taglibs.jsp"%>

<div id="branding" style="margin-left:-8em;">
    <h1><a href="<c:url value='/'/>"><fmt:message key="webapp.name"/></a></h1>
</div>
<hr />
   <div style="padding:3% 0 0 85%">       
       <security:authorize access="isAuthenticated()">
        <security:authentication property="principal.fullName" /> |
        <a href="<fmt:message key='/logout'/>"><fmt:message key="user.logout"/>
    </security:authorize></a>       
</div>
<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>
