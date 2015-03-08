<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>HOME</title>
</head>
<body>
<script language="javascript">
function verify()
{
return confirm("Do u want to delete your profile");
}
</script>
<center> <cite>Welcome ${user.getUserName()}</cite>
<p align="right"><a href="ControllerServlet?SUBMIT=LOGOUT&UserName=${user.getUserName()}">LOG OUT</a>
</p>
<br>
<i> ${message}</i></center>
<hr />
<center>

<fieldset >
<table cellspacing =3 cellpadding=10 style="height: 164px; width: 295px">
<#if message!="Your Profile has been Deleted" && message!="Profile not Exist">
		<tr>
			<td >
				<a href=ControllerServlet?SUBMIT=VIEWMYPROFILE&UserName=${user.getUserName()}&viewUser=${user.getUserName()} title="Click to View!!!">VIEW MY PROFILE</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href=ControllerServlet?SUBMIT=EDIT&UserName=${user.getUserName()} title="Click to Edit!!!">EDIT MY PROFILE</a>
			</td>
		</tr>
		<tr>
			<td>
			<a href=ControllerServlet?SUBMIT=DELETEMINE&UserName=${user.getUserName()} onClick="return verify()" title="Click to Delete!!!">DELETE MY PROFILE</a>
			</td>
		</tr>
</#if>
<#if message=="Your Profile has been Deleted" || message=="Profile not Exist">
		<tr>
			<td>
			<a href="ControllerServlet?SUBMIT=CREATE&UserName=${user.getUserName()}" title="Click to Create!!!" >CREATE MY PROFILE</a>
			</td>
		</tr>
</#if>
</table>
</fieldset>
<br>

<fieldset >

<table  cellspacing =3 cellpadding=10 > 
<tr><td><a href="ControllerServlet?SUBMIT=SEARCH&UserName=${user.getUserName()}" title="Click to Search Profiles!!!">SEARCH USER PROFILES</a></td></tr>
<tr><br><td><a href="ControllerServlet?UserName=${user.getUserName()}&SUBMIT=VIEW ALL" title="Click to View All Profiles!!!">VIEW ALL PROFILES</a></td></tr>
<tr><br><td></td></tr>
</table>
</fieldset>
</body>
</html>