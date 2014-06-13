/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Delete Confirmation
function confirmDelete()
{
    var r=confirm("Are you sure you want to delete?");
if (r==true){
     return true;
}
else {
     return false;
}
}
var state = 'none';
function showhide(show) {
    if (state == 'block') {
        state = 'none';
    }
    else {
        state = 'block';
    }
    if (document.all) { //IS IE 4 or 5 (or 6 beta)
        eval( "document.all." + show + ".style.display = state");
    }
    if (document.layers) { //IS NETSCAPE 4 or below
        document.layers[show].display = state;
    }
    if (document.getElementById &&!document.all) {
        hza = document.getElementById(show);
        hza.style.display = state;
    }
}
//Change Password validation
function onFormSubmit(form) {
    var e = form.elements;

    /* Your validation code. */

    if(e['oldPassword'].value == "")
    {
        alert("Please Enter OldPassword Value");
        return false;
    }
    else if(e['password'].value == "")
    {
        alert("Please Enter Password Value");
        return false;
    }else if(e['password'].value.length <= 5)
    {
        alert("Password Length Must be min 6");
        return false;
    }else if(!(e['password'].value.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)))
    {
        alert("Password Must Have small and capital letter");
        return false;
    }
    else if(e['password'].value != e['confirmPassword'].value) {
        alert('Your passwords do not match. Please type more carefully.');
        return false;
   
    }
    return true;
}
function IsEnoughLength(str,length){
    if ((str == null) || isNaN(length))
        return false;
    else if (str.length < length)
        return false;
    return true;
}
function HasMixedCase(passwd){
    if(passwd.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))
        return true;
    else
        return false;
}
function HasNumeral(passwd){
    if(passwd.match(/[0-9]/))
        return true;
    else
        return false;
}
function HasSpecialChars(passwd){
    if(passwd.match(/.[!,@,#,$,%,^,&,*,?,_,~]/))
        return true;
    else
        return false;
}
function CheckPasswordStrength(password){       
    var pass_strength;
    if (IsEnoughLength(password,14) && HasMixedCase(password) && HasNumeral(password) && HasSpecialChars(password))
        pass_strength = "<b><font style='color:olive'>Very strong</font></b>";
    else if (IsEnoughLength(password,8) && HasMixedCase(password) && (HasNumeral(password) || HasSpecialChars(password)))
        pass_strength = "<b><font style='color:Blue'>Strong</font></b>";
    else if ((IsEnoughLength(password,6) && HasNumeral(password)||IsEnoughLength(password,6)&& HasSpecialChars(password)||IsEnoughLength(password,6)&&HasMixedCase(password))&&HasMixedCase(password))
        pass_strength = "<b><font style='color:Green'>Good</font></b>";
    else if(!(HasMixedCase(password)))
        pass_strength = "<b><font style='color:red'>Weak</font></b>";
    else
        pass_strength = "<b><font style='color:red'>Weak</font></b>";

    document.getElementById('pwd_strength').innerHTML = pass_strength;
}

//LeaveForm HalfDay
function halfDayCheck(id){
    var days = document.getElementById(id).value;
    if(days != Math.round(days))
        document.getElementById('hide').style.display = 'block';
    else
        document.getElementById('hide').style.display = 'none'
}
//Report form validation        
function onSubmit(form) {
    var e = form.elements;
    /* Your validation code. */
    if(e['leaveFrom'].value == "")
    {
        alert("Please Enter From Date");
        return false;
    }
    else if(e['leaveTo'].value == "")
    {
        alert("Please Enter To Date");
        return false;
    }
    return true;
}

