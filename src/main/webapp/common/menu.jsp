<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
    <ul id="primary-nav" class="menuList" >        
        <menu:displayMenu name="UserMenu"/>
        <menu:displayMenu name="ViewMenu"/>       
        <menu:displayMenu name="UploadMenu"/>
        <menu:displayMenu name="ItemManager"/>
        <menu:displayMenu name="HoldService"/>   
        <menu:displayMenu name="BlockList"/>   
         <menu:displayMenu name="PayBill"/>
        <menu:displayMenu name="ChangePassword"/>
       
    </ul>
</menu:useMenuDisplayer>
