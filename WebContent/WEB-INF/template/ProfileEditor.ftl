<html>
<body onLoad="document.CreateProfile.fullname.focus();">
<script src="ValidateForm.js" language="javascript">
</script>
<center> <cite>Welcome ${user.getUserName()}</cite></center>
<p align="right"><a href="ControllerServlet?SUBMIT=LOGOUT&UserName=${user.getUserName()}">LOG OUT</a>
</p>
<br><br>
<#if caller!="Login">
CREATE YOUR PROFILE
<#else>
EDIT YOUR PROFILE
</#if>  
<hr/>
<form name="CreateProfile" action ="ControllerServlet" method="post">
<center>
<table>
<br>
	<tr>
		<td>Full Name</td>
		<td>
			<input type="text" 
						name="fullname" 
						title="Enter the fullname here"
						onKeyPress="return allowOnlyAlpha(CreateProfile.fullname,event)"  
						onBlur="changeBorderNoError(CreateProfile.fullname)" 
						value=<#if caller="Login">'${profile.fullName}'</#if>>
		</td>
	</tr>
<br>
	<tr>
		<td>Age </td>
		<td>
			<input type="text" 
						name="age" 
						title="Enter the Age here"
						MAXLENGTH=2 
						onKeyPress="return allowOnlyNumeric(CreateProfile.age,event)" 
						onBlur="changeBorderNoError(CreateProfile.age)" 
						value=<#if caller="Login">'${profile.getAge()}'<#else>0</#if> >
		</td>
	</tr>
<br>
	<tr>
		<td>Gender</td>
			<#if caller=="Login">
		<td>
			<input type="text" 
						name="gendertext" 
						readonly 
						value='${profile.getGender()}'>
		</td>
			</#if>
		<td>MALE <input type="radio" 
						name="gender" 
						value="MALE" 
						checked 
						onClick="changeGenderText(this)">
			FEMALE<input type="radio" 
						name="gender" 
						value="FEMALE" 
						onClick="changeGenderText(this)">
		</td>
	</tr>
<br>
	<tr>
		<td>Profession</td>
		<td>
			<input type="text" 
						name="profession" 
						title="Enter the Profession here"
						onKeyPress="return allowAlpha(CreateProfile.profession,event)" 
						onBlur="changeBorderNoError(CreateProfile.profession)" 
						value=<#if caller="Login">'${profile.getProfession()}'</#if> >
		</td>
	</tr>
<br>
	<tr>
		<td>Books</td>
		<td>
			<input type="text" 
						name="books" 
						title="Enter your favourite book titles"
						onKeyPress="return allowAlpha(CreateProfile.books,event)" 
						onBlur="changeBorderNoError(CreateProfile.books)" 
						value=<#if caller="Login">'${profile.getBooks()}'</#if> >
		</td>
	</tr>
<br>
	<tr>
		<td>Passions</td>
		<td>
			<input type="text" 
						name="passions" 
						title="What are u passionate about?"
						onKeyPress="return allowAlpha(CreateProfile.passions,event)" 
						onBlur="changeBorderNoError(CreateProfile.passions)"  
						value=<#if caller="Login">'${profile.getPassions()}'</#if> >
		</td>
	</tr>
<br>
	<tr>
		<td>Phone</td>
		<td>
			<input type="text" 
						name="phone" 
						title="How do we reach u?"
						MAXLENGTH=15
						onKeyPress="return allowOnlyNumPhone(CreateProfile.phone,event)" 
						onBlur="changeBorderNoError(CreateProfile.phone)" 
						value=<#if caller="Login">'${profile.getPhone()}'<#else>0</#if> > 
		</td>
	</tr>
<table>
<br><input type="submit" name="SUBMIT" value=<#if caller!="Login">"CREATE MY PROFILE"<#else>"EDIT MY PROFILE"</#if> onClick="buttonValueChange()" >
</center>
<input type="text" style="display:none"  name="userid" value='${user.userId}'>
<input type="text" style="display:none"  name="UserName" value='${user.userName}'>
<input type="text" style="display:none"  name="caller" value='${caller}'>
	<input type="text" style="display:none" name="caller" value="Login">
	<input type="submit" name="SUBMIT" value="GO TO HOME" onClick="submit()">
</form>
