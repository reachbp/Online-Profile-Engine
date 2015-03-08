<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>LOGIN PAGE</title>
</head>
<body >
<script src="ValidateForm.js" language="javascript">
</script>
<br><br><br><br><br><br></br>

<h3><cite>Login here</cite></h3>
<form name="LoginForm" action="ControllerServlet" method="post">

<table cellspacing=5 cellpadding=5><tr><td><label>UserName</label></td>
	<td><input type="text" name="UserName"
					title="Enter the Username here"
	  				onmouseover=showDiv("p1") 
	  				onfocus=showDiv("p1") 
	  				onmouseout=hideDiv("p1") 
	  				onkeypress="return allowAlphaNumSpe(LoginForm.UserName,event)" 
	  				onblur=changeBorderHideDiv(LoginForm.UserName,"p1")>
	  </td><td><span id="p1"></span></td></tr>
<br>
<tr><td><label>Password</label></td>
	<td><input type="password" name="PassWord" 
					title="Enter the Password here" 
					onmouseover=showDiv("p2") 
					onfocus=showDiv("p2") 
					onmouseout=hideDiv("p2")  
					maxlength=10  
					onkeypress="return allowAlphaNum(LoginForm.PassWord,event)" 
					onblur=changeBorderHideDiv(LoginForm.PassWord,"p2")></input>
	 </td>
	 <td><span id="p2"></span>
	 </td>
</tr>
<tr><td><a  href="ControllerServlet?SUBMIT=SIGNUP">Sign up for  Free!!!</a></td>
<td><a href="ForgotPassword.html">Forgot password</a></td></tr>
<tr><td></td><td><font color="red">${message}</font></td></tr>
<tr><td></td><td><font color="red"><#if message=="Login Not Accepted">You gave ${user.getUserName()}</#if></font></td></tr>
<tr><td></td><td><p align="left"><input type="submit" name="SUBMIT" value="Sign In" onClick="return validate_form()"></p></td></tr>

</table>

<form>
</body>
</html>