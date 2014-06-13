    <script type="text/javascript">
    if (getCookie("username") != null) {
    $("j_username").value = getCookie("username");
    $("j_password").focus();
} else {
    $("j_username").focus();
}
    
function saveEmpId(theForm) {
    var expires = new Date();
    expires.setTime(expires.getTime() + 24 * 30 * 60 * 60 * 1000); // sets it for approx 30 days.
    setCookie("username",theForm.j_username.value,expires,"<c:url value="/"/>");
}
    
function validateForm(form) {                                                               
    return validateRequired(form); 
} 
    
function numbersOnly(myfield, e, dec) {                                                               
    var key;
    var keychar;

    if (window.event)
        key = window.event.keyCode;
    else if (e)
        key = e.which;
    else
        return true;
    keychar = String.fromCharCode(key);

    // control keys
    if ((key==null) || (key==0) || (key==8) || 
        (key==9) || (key==13) || (key==27) )
        return true;

    // numbers
    else if ((("0123456789").indexOf(keychar) > -1))
        return true;

    // decimal point jump
    else if (dec && (keychar == "."))
    {
        myfield.form.elements[dec].focus();
        return false;
    }
    else
        return false;
}
    
function passwordHint() {
    if ($("j_username").value.length == 0) {
        alert("<fmt:message key="errors.required"><fmt:param><fmt:message key="label.empId"/></fmt:param></fmt:message>");
        $("j_username").focus();
    } else {
        location.href="<c:url value="/passwordHint"/>?empId=" + $("j_username").value;
    }
}
    
function required () { 
    this.aa = new Array("j_username", "<fmt:message key="errors.required"><fmt:param><fmt:message key="label.empId"/></fmt:param></fmt:message>", new Function ("varName", " return this[varName];"));
    this.ab = new Array("j_password", "<fmt:message key="errors.required"><fmt:param><fmt:message key="label.password"/></fmt:param></fmt:message>", new Function ("varName", " return this[varName];"));
} 
    </script>
