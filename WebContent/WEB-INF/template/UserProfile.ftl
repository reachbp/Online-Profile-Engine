<html>
<body>
	<center><cite>Welcome ${viewUser}
	<p align="right"><a href="ControllerServlet?SUBMIT=LOGOUT&UserName=${user.getUserName()}">LOG OUT</a>
</p>
		<br><br>
	View  ${user.getUserName()}'s Profile</cite></center>
		
		<hr/>
		<center>
	<table cellspacing=5 cellpadding=10>
<br><tr><td><b>Name<b></td><td><i>${profile.getFullName()}</i></td></tr>
<br><tr><td><b>Age<b></td><td><i>${profile.getAge()}</i></td></tr>
<br><tr><td><b>Gender<b></td><td><i>${profile.getGender()}</i></td></tr>
<br><tr><td><b>Profession<b></td><td><i>${profile.getProfession()}</i></td></tr>
<br><tr><td><b>Passions<b></td><td><i>${profile.getPassions()}</i></td></tr>
<br><tr><td><b>Books<b></td><td><i>${profile.getBooks()}</i></td></tr>
<br><tr><td><b>Phone<b></td><td><i>${profile.getPhone()}</i></td></tr>
	</table>
	<A HREF="mailto:${user.getUserName()}?subject=Good%20Morning
&cc=${viewUser}">Send an email to ${profile.getFullName()}</a> 
	</center>
	<form action="ControllerServlet" method="post">
	<input type="text" style="display:none" name="UserName" value=${viewUser}>
	<input type="text" style="display:none" name="caller" value="Login">
	<input type="SUBMIT" name="SUBMIT" value="GO TO HOME">
	</form>
	</body>
	</html>
	