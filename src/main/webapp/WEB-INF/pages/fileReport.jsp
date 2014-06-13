<%-- 
    Document   : fileReport
    Created on : 31 Aug, 2012, 10:40:15 AM
    Author     : sheetal.sisodiya
--%>

<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fileReport.title"/></title>
    <meta name="heading" content="<fmt:message key='fileReport.heading'/>"/>
    <meta name="menu" content="UploadMenu"/>
</head>

<display:table name="fileParsingList" cellspacing="0" cellpadding="0" requestURI="" 
               defaultsort="1" id="fileParsing" pagesize="25" class="table">
    <display:column property="count" escapeXml="true" sortable="true" titleKey="fileReport.serial" style="width: 34%"/>
    <display:column property="record" sortable="true" titleKey="fileReport.record" style="width: 25%" autolink="true" media="html"/>
    <display:column property="reason" sortable="true" titleKey="fileReport.reason" style="width: 25%" autolink="true" media="html"/>
</display:table>

<input type="button" style="width: 120px" value="Upload Another User"
                   onclick="location.href='/admin/userUpload'" />
           <input type="button" style="width: 120px" value="Upload Another Leave"
                   onclick="location.href='/admin/leaveUpload'" />
<script type="text/javascript">
    highlightTableRows("fileParsing");
</script>
