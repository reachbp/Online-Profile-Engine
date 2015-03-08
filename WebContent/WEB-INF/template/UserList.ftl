<html>
<head>
<title>
LIST OF USER NAMES</title>
</head>
<body>

<center> <cite>Welcome ${user.getUserName()}</cite>
<p align="right"><a href="ControllerServlet?SUBMIT=LOGOUT&UserName=${user.getUserName()}">LOG OUT</a>
</p>
<br>
<#assign  str="heelo">
<#if message?exists>${message}</#if>
<hr/>
<table cellspacing=10 cellpadding=5>
<#list list_of_names as x>
<#assign  str="Results Found">
<tr><td> <a href="ControllerServlet?UserName=${x}&viewUser=${user.getUserName()}&SUBMIT=VIEWMYPROFILE">${x}</a></td>
<#if user.getUserName()=="admin">
<td> <a href="ControllerServlet?deleteUser=${x}&SUBMIT=DELETE PROFILE&UserName=${user.getUserName()}">Delete</a></td>
</#if>

<tr>

</#list>
<#if str!="Results Found">
No Results Found
</#if>
</table>
<form action="ControllerServlet" method="post">
	<input type="text" style="display:none" name="UserName" value=${user.getUserName()}>
	<input type="text" style="display:none" name="caller" value="Login">
	<input type="SUBMIT" name="SUBMIT" value="GO TO HOME">
	</form>

</body>
</html>