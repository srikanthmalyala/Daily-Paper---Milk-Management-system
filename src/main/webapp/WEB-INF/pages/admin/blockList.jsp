<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/scripts/numberOnly.js"%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>
<div style=" width: 75%; margin: auto;">
    <div id="search">
        <form method="get" action="${ctx}/admin/users" id="searchForm">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="Enter Emp Id To Search" onkeypress="return numbersOnly(this, event)"/>
            <input type="submit" value="<fmt:message key="button.search"/>"/>
        </form>
    </div>

    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/admin/customerForm?method=Add&from=list"/>'"
           value="<fmt:message key="button.add"/>"/>

    <display:table name="blockList" cellspacing="0" cellpadding="0" requestURI="" 
                   defaultsort="1" id="users" export="true" pagesize="25" class="table">
        <display:column property="customerId" escapeXml="true" sortable="true" titleKey="customer.customerid" style="width: 10%" />
        <display:column  property="fullName" escapeXml="true" sortable="true" titleKey="customer.name" style="width: 35%" url="/activeUser?show=true" paramId="id" paramProperty="id"/>
        <display:column property="paper" sortable="true" titleKey="customer.paper" style="width: 30%"/>
        <display:column property="phoneNo" escapeXml="true" sortable="true" titleKey="customer.phoneno" style="width: 15%" />  
       
        
        <display:setProperty name="paging.banner.item_name" value="customerList"/>
    <display:setProperty name="paging.banner.items_name" value="customerList"/>

    <display:setProperty name="export.excel.filename" value="customerList.xls"/>
    <display:setProperty name="export.csv.filename" value="customerList.csv"/>
    <display:setProperty name="export.pdf.filename" value="customerList.pdf"/>
    </display:table>
</div>


<script type="text/javascript">
    highlightTableRows("users");
</script>
