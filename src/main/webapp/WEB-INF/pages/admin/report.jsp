<%-- 
    Document   : report
    Created on : 27 Aug, 2012, 5:08:04 PM
    Author     : sheetal.sisodiya
--%>

<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="report.title"/></title>
    <meta name="heading" content="<fmt:message key='report.title'/>"/>
    <meta name="menu" content="report"/>
</head>
<div style=" width: 75%; margin: auto;">
<div class="separator"></div>

<display:table name="reportList" class="table" requestURI="/admin/report" id="reportList" export="true" pagesize="25">
    <display:column property="empId" escapeXml="true" style="width: 30%" titleKey="user.empId" sortable="true"/>
    <display:column property="LOP" escapeXml="true" style="width: 20%" titleKey="report.LOP" sortable="true"/>
    <display:column property="PL" escapeXml="true" style="width: 20%" titleKey="report.PL" sortable="true"/>
    <display:column property="ED" escapeXml="true" style="width: 30%" titleKey="report.ED" sortable="true"/>
    <display:column property="HF" escapeXml="true" style="width: 30%" titleKey="report.HF" sortable="true"/>
    <display:setProperty name="paging.banner.item_name" value="report"/>
    <display:setProperty name="paging.banner.items_name" value="reports"/>
    <display:setProperty name="export.excel.filename" value="reportList.xls"/>
    <display:setProperty name="export.csv.filename" value="reportList.csv"/>
    <display:setProperty name="export.pdf.filename" value="reportList.pdf"/>
</display:table>

<input type="button" style="margin-right: 5px"
       onclick="location.href='<c:url value="/admin/reportForm"/>'"
       value="<fmt:message key="button.reset"/>"/>
<input type="button" style="margin-right: 5px"
       onclick="location.href='<c:url value="/admin/users"/>'"
       value="<fmt:message key="Done"/>"/>

<script type="text/javascript">
    highlightTableRows("reportList");
</script>
</div>