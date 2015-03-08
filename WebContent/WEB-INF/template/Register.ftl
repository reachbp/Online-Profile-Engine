<html>
<head><title>REGISTRATION FORM</title>
</head>
<body >
<br><br>

<center>
<h2><cite>Registration Form</cite></h2>Get Started Now</center>
<hr/>
<script src="ValidateForm.js" language="javascript">
</script>
<a href="Login.html">Go back to Login</a>
<form name="Register" action="ControllerServlet" method="post">
<table>
<font color="red"><cite><#if message?exists>${message}</#if></cite></font>
<br>
	<tr>
		<td><label>Username</label></td>
		<td>
			<input type="text" 
					size=30 
					name="UserName" 
					onmouseover=showDiv("p1") 
					onFocus=showDiv("p1") 
					onmouseout=hideDiv("p1") 
					onkeypress="return allowAlphaNumSpe(Register.UserName,event)" 
					onBlur=changeBorderHideDiv(Register.UserName,"p1")>
		</td>
		<td>
			<span id="p1"></span>
		</td>
	</tr>
<br><tr><td></td><td><font color=#6D7B8D size=2>E.g user1@gmail.com</font></td><td></td></tr>
<br>
	<tr><td><label>Password</label></td>
		<td><input type="password" 	size=30
				 	name="PassWord"
				 	MAXLENGTH=10 
					onmouseover=showDiv("p2")
					onFocus=showDiv("p2")
					onmouseout=hideDiv("p2")
					onkeypress="return allowAlphaNum(Register.PassWord,event)" 
					onBlur=changeBorderHideDiv(Register.PassWord,"p2")>
		</td>
		<td><span id="p2"></span></td>
	</tr>
<br>
	<tr><td></td>
		<td><font color=#6D7B8D size=2>Password must be minimun 6 characters</font></td>
	</tr>
<br>
	<tr>
		<td><label>Choose Password</label></td>
		<td><input type="password" size=30 
						name="rePass" 
						MAXLENGTH=10 
						onmouseover=showDiv("p3")
						onFocus=showDiv("p3")
						onmouseout=hideDiv("p3")
						onkeypress="return allowAlphaNum(Register.rePass,event)" 
						onBlur=changeBorderHideDiv(Register.rePass,"p3")>
		</td>
		<td><span id="p3"></span></td>
	</tr>
<br>
	<tr>
		<td><label>Security Qn</label></td>
		<td><select name="SecureQn">
			<option>Choose A Question..</option>
			<option>What is your favourit TV show?</option>
			<option>What color do you like?</option>
			<option>What is your fathers name?</option>
			<option>Person whom you hate  the most !!</option>
			<option>Admirer!!1</option>
		    </select>
		</td>
		
	</tr>
<br>
	<tr>
		<td></td>
		<td><font color=#6D7B8D size=2>If You Forget ur Password..We get it for u..</font> </td>
	</tr>
<br>
	<tr>
		<td> </td>
		<td><font color=#6D7B8D size=2>by asking this Question</font> </td>
	</tr>
		   
<br>
	<tr>
		<td><label>Answer</label></td>
		<td><input type="password" size=30 
				name="SecureAns" 
				onkeypress="return allowAlphaNum(Register.SecureAns,event)"
				onmouseover=showDiv("p4")
				onFocus=showDiv("p4")
				onmouseout=hideDiv("p4")
				onBlur=changeBorderHideDiv(Register.SecureAns,"p4")>
		</td>
		<td><span id="p4"></span></td>
	</tr>
<br><tr><td></td><td></td></tr>
</table>
<br><input type="submit" name="SUBMIT" value="REGISTER" onClick="return validate_formRegister()">
</form>

</body>