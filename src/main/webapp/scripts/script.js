/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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