<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/scripts/numberOnly.js"%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>
<div style=" width: 75%; margin: auto;">
   

    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/admin/item/milkForm?method=Add&from=list"/>'"
           value="<fmt:message key="button.add"/>"/>

    <display:table name="milkList" cellspacing="0" cellpadding="0" requestURI="" 
                   defaultsort="1" id="users" export="true" pagesize="25" class="table">
        <display:column property="id" escapeXml="true" sortable="true" titleKey="id"  />
        <display:column  property="item" escapeXml="true" sortable="true" titleKey="Paper Name" style="width: 25%" url="/admin/item/milkForm" paramId="id" paramProperty="id"/>
        <display:column property="cost" sortable="true" titleKey="Price" />
        <display:column  property="deleteItem" titleKey="" sortable="true"  />
        
       
    </display:table>
</div>


<script type="text/javascript">
    highlightTableRows("users");
</script>
