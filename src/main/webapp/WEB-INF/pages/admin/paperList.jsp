<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/scripts/numberOnly.js"%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>
<div style=" width: 75%; margin: auto;">
   

    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/admin/item/paperForm?method=Add&from=list"/>'"
           value="<fmt:message key="button.add"/>"/>

    <display:table name="paperList" cellspacing="0" cellpadding="0" requestURI="" 
                   defaultsort="1" id="users" export="true" pagesize="25" class="table">
        <display:column property="id" escapeXml="true" sortable="true" titleKey="id"  />
        <display:column  property="paperName" escapeXml="true" sortable="true" titleKey="Paper Name" style="width: 25%" url="/admin/item/paperForm" paramId="id" paramProperty="id"/>
        <display:column property="monday" sortable="true" titleKey="Monday" />
        <display:column property="tuesday" escapeXml="true" sortable="true" titleKey="tuesday"  />  
        <display:column  property="wednesday" titleKey="Wednesday" sortable="true"  />              
        <display:column property="thursday" escapeXml="true" sortable="true" titleKey="Thursday"  />  
        <display:column  property="friday" titleKey="Friday" sortable="true"  />              
        <display:column  property="saturday" titleKey="Saturday" sortable="true"  />  
        <display:column  property="sunday" titleKey="sunday" sortable="true"  />  
        <display:column  property="deleteItem" titleKey="" sortable="true"  />
        
       
    </display:table>
</div>


<script type="text/javascript">
    highlightTableRows("users");
</script>
