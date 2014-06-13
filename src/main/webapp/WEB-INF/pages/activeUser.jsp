<%-- 
    Document   : activeUser
    Created on : 29 Aug, 2012, 4:08:35 PM
    Author     : sheetal.sisodiya
--%>

<%@ include file="/common/taglibs.jsp"%>
<head>
    <script language="javascript" src="/scripts/PaginaCollapse.js"></script>
    <title><fmt:message key="activeUsers.title"/></title>
    <meta name="menu" content="ViewMenu"/>
</head>
<div style=" width: 50%; margin: auto;">
    <table  class="table1" > 
        <tr>
            <th >Customer Id :</th>
            <td> ${customerList.customerId}</td>    
        </tr>
        
        <tr>    
            <th> Name :</th>
            <td> ${customerList.fullName}</td>
        </tr>
        <tr>
            <th >phoneNo : </th>
            <td> ${customerList.phoneNo}</td>
        </tr>
       
        <tr>
            <th >Milk:</th>     
            <c:forEach items="${items}" var="item">
            <td> ${item.item}</td><br>     
               </c:forEach>
        </tr>
        
        <tr>
            <th >Deposit :</th>     
            <td> ${customerList.deposit}</td>        
        </tr>
        
        <tr>
            <th >Address :</th>     
            <td> ${customerList.lineOne}</td>        
        </tr>
           <td colspan="2" style="text-align: right;"> <c:if test="${admin == 'true'}">   
                    <a href="${customerList.editUser}"title='User Edit'><img src='/images/edit.jpg' width='18px' height='18px'/></a>| 
                    <a href="${customerList.deleteUser}" title='Delete User'onClick='return confirmDelete()'><img src='/images/delete.jpg' width='18px' height='18px' /></a>| 
                    <a href="${customerList.backUser}"title='Back To List'><img src='/images/back.jpg' width='18px' height='18px'/></a>
                </c:if></td></tr>
    </table>
</div><div style="width:65%; margin: auto;">
    <display:table name="bills"  id="customer" cellspacing="0" cellpadding="0" defaultsort="1" class="table" pagesize="15" requestURI="">
        <display:column property="customerId" escapeXml="true" titleKey="pay.customerid" style="width: 10%" url="/admin/leaveForm?from=list" paramId="id" paramProperty="id"/>
        <display:column property="paidDate" escapeXml="true" style="width: 15%" titleKey="pay.date" />
        <display:column property="paidAmount" escapeXml="true" style="width: 15%" titleKey="pay.amount" />
     
    </display:table>
    
     <li style="display:inline"><a href="#" onclick="showhide('div1');">Click To see the Hold Detail</a></li> |<li style="display:inline;">
            
    <div id="div1" style="display: none;">
        <display:table name="holds" id="customer" cellspacing="0" cellpadding="0" defaultsort="1" class="table" pagesize="15" requestURI="">
            <display:column property="customerId" escapeXml="true" style="width: 15%" titleKey="pay.customerid" />
            <display:column property="holdFrom" escapeXml="true" style="width: 25%" titleKey="holdDetail.holdFrom" />
            <display:column property="holdTo" escapeXml="true" style="width: 20%" titleKey="holdDetail.holdTo" />
            <display:column property="days" escapeXml="true" style="width: 20%" titleKey="holdDetail.days" />
       </display:table></div>


   
</div>