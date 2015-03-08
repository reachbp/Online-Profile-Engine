

/*Allows Alphabets and Numbersonly*/
var username="Enter Email id";
function allowAlphaNum(text,e)
	{
		var key;
		if(window.event)
		key=window.event.keyCode;
		if((key>=97 && key<=122)||(key>=65 && key<=90)||(key>=48 && key<=57))	
		{
			changeBorderNoError(text);
			return true;
		}
		else 
		{
			changeBorderError(text);
			return false;
		}
	}

/*Allows Only Alphabets ,Numbers, And Space*/	
function allowAlphaNumSpe(text,e)
	{
		var key;
		if(window.event)
		key=window.event.keyCode;
		if((key>=48 && key<=57) ||(key>=97 && key<=122) || (key>=65 && key<=90) )
		{
			changeBorderNoError(text);
			return true;
		}
		if(key==64 || key==95 || key==46)
		{
			changeBorderNoError(text);
			return true;
		}
		else 
		{
			changeBorderError(text);
			return false;
		}
	}
/*Allows Only Numbers*/
function allowOnlyNumeric(text,e)
	{	
		var key;
		if(window.event)
		key=window.event.keyCode;
		if((key>=48 && key<=57))
		{
			changeBorderNoError(text);
			return true;
		}
		else 
		{
			changeBorderError(text);
			return false;
		}
	}
/*Allow number,+,- and # for Phone */
function allowOnlyNumPhone(text,e)
	{	
		var key;
		if(window.event)
		key=window.event.keyCode;
		if((key>=48 && key<=57)||(key==35)||(key==45)||(key==43))
		{
			changeBorderNoError(text);
			return true;
		}
		else 
		{
			changeBorderError(text);
			return false;
		}
	}
/* Allows only Alphabets and Space..Restricts First Space*/
function allowOnlyAlpha(text,e)
	{
		var key;
		var len=document.CreateProfile.fullname.value.length;
		if(window.event)
			key=window.event.keyCode;
		if((key>=97 && key<=122) || (key>=65 && key<=90) )
			{
				changeBorderNoError(text);
				return true;
			}
		if(len==0 && key==32)
			{
				changeBorderError(text);
				return false;
			}
		if(len!=0 && key==32)
			{
				changeBorderNoError(text);
				return true;
			}
		else
			{
				changeBorderError(text); 
				return false;
			}
	}

/*Changes the TextBox BorderColour to Red when onFocus an OnError*/

	function changeBorderError(text)
		{
		  text.style.borderColor="red";
		}
/*Resets The TextBox BorderColour to default when OnBlur and NoError*/
	function changeBorderNoError(text)
		{
		  text.style.borderColor="white";
		}
/*Sets the Text value based on Radio checked
  Used by Create FTL*/
	function changeGenderText()
		{
      	  if(document.CreateProfile.gender[0].checked)
		  document.CreateProfile.gendertext.value="MALE";
          else if(document.CreateProfile.gender[1].checked)
          document.CreateProfile.gendertext.value="FEMALE";
		}
/*Resets the button Value .Used by create FTL*/
	function buttonValueChange()
		{
		  document.CreateProfile.SUBMIT[0].value="CREATE MY PROFILE";
          
		}
/*Allows only Alphabets Space an comma*/		
	function allowAlpha(text,e)
		{
			var key;
			if(window.event)
			key=window.event.keyCode;
			if((key>=97 && key<=122) || (key>=65 && key<=90) )
				{
				changeBorderNoError(text);
				return true;
				}
			if(key==32 || key==44)
				{
				changeBorderNoError(text);
				return true;
				}
			if((key>=48) && (key<=57))
				{
				if(text.name=="books")
				{
				changeBorderNoError(text);
				return true;
				}
				else return false;
				}
			else	
				{
				changeBorderError(text); 
				return false;
				}
		}
	/*Validates Login Form*/
	function validate_form()
		{
		
			var username=document.LoginForm.UserName.value;
			var password=document.LoginForm.PassWord.value;
			if(username=="" && password=="")
				{
				alert("Enter Username & Password!!!!");	
				return false;
				}
			if(username=="")
				{
				alert("Enter Username!!!!");	
				return false;
				}
			if(password=="")
				{
				alert("Enter Password!!!");	
				return false;
				}
			else	
				return true;
		}
	/*Validates Registration Form*/
	function validate_formRegister()
		{
			var user=document.Register.UserName.value;
			var pass=document.Register.PassWord.value;
			var repass=document.Register.rePass.value;
			var secureqn=document.Register.SecureQn.options[document.Register.SecureQn.selectedIndex].text;
			var alert_message="Enter ";
			if(user=="" )
				{
				alert_message+=" Username";
				if(pass=="")
					alert_message+=" and PassWord";
				if(secureqn=="Choose A Question..")
					alert_message+=" and Choose a SecurityQuestion!!!";
				alert(alert_message);
				document.Register.reset();
				return false;
				}
			if(repass=="")
				{
				alert("Enter Password");
				if(secureqn=="Choose A Question..")
					alert("choose a question");
				document.Register.reset();
				return false;
				}
			if(pass!=repass)
				{
				alert("Passwords dont match");
				if(secureqn=="Choose A Question..")
					alert("choose a question");
				document.Register.reset();
				return false;
				}
			if(pass.length<6)
				{
				alert("Password has to be minimum six character");
				if(secureqn=="Choose A Question..")
					alert("choose a question");
				document.Register.reset();
				return false;
				}
		  if(secureqn=="Choose A Question..")
				{
				alert("choose a question");
				document.Register.reset();
				return false;
				}
			else
				return true;
		}
		
/*Show Guider Text while OnFocus ans onmouseOver*/
	function showDiv(id)
		{
			var textToPrint="";
			if(id=="p1")
				textToPrint="Enter Your Username";
			if((id=="p2") || (id=="p3"))
				textToPrint="Password with no Special characters ";
			if(id=="p4")
				textToPrint="Answer with no special characters";
			document.getElementById(id).innerHTML=textToPrint;
		}
/*Hide Guider Text while OnBlur and OnMouseOut*/
	function hideDiv(id)
		{
			document.getElementById(id).innerHTML="             ";
		}

/*Forgot Password */
var xmlHttp=null;
var secureqn;
var secureans;
/*Sets the fields required for changin Password to invisble on BofyLoad*/
	function setInvisible()
	{
		document.getElementById("reset").style.visibility="hidden";
		document.getElementById("PassButton").style.visibility="hidden";
	}
/*Issues an XML http request to get the security question from the database*/
	function GetSecurityQuestion()
	{
		var str=document.ForgotPassword.UserName.value;
		if (str.length==0)
		  { 	
			  document.getElementById("secureQn").value="";
			  return;
		  }
		try
		  {
			  xmlHttp=new XMLHttpRequest();
		  }
		catch(e)
		  {
			  try
			    {
				    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			    }
			  catch(e)
			    {
				    alert ("Your browser does not support XMLHTTP!");
				    return;  
			    }
		  }
		var url="XmlController?UserName="+str+"&SUBMIT=FORGOTXML";
		document.getElementById("PassButton").style.visibility="visible";
		xmlHttp.open("POST",url,false);
		xmlHttp.send(null);
		var receivedText=xmlHttp.responseText;
		if(receivedText=="User Name does not exist")
			{
			alert("Invalid UserName");
			document.ForgotPassword.reset();
			}
		else
			{
			/*The receivedText is parsed to retrieve both the Security Qn and answer*/
			secureqn=receivedText.substring(0,receivedText.indexOf('&'));
			secureans=receivedText.substring(receivedText.indexOf('&')+1,receivedText.length);
			document.getElementById("secureQn").value=secureqn;
			}
		}
		/*check the User input for answer ans the retrieved security answer*/
		function checkAnswer()
		{
		 var userans=document.ForgotPassword.secureAns.value;
		 secureans=trimAll(secureans);
		var l1=userans.length;
		var l2=secureans.length;
		 if(l1!=l2)
		 alert("Incorrect");
		 if(userans==secureans)
		 {
		 alert("Correct..go on to change Your Password");
		 document.getElementById("reset").style.visibility="visible";
		 }
	  }
	  /*The receied answer is trimmed to remove leading and trailing White Spaces*/
	 function trimAll(sString) 
		{ 
			while (sString.substring(0,1) == ' ') 		
				{ 	
				sString = sString.substring(1, sString.length); 	
				} 
			while (sString.substring(sString.length-1, sString.length) == ' ') 
				{ 	
				sString = sString.substring(0,sString.length-1); 	
				} 
			return sString; 
		} 
	/* validates ForgotPassword Form*/
	function validate_formForgot()
		{
			var pass=document.ForgotPassword.PassWord.value;
			var repass=document.ForgotPassword.repass.value;
			if(pass!=repass)
				{
				  alert("Passwords not matching..");
				  return false;
				 }
			if((pass=="")||(repass==""))
			 	{
			 	alert("Enter Required Fields");
       			 return false;
			 	}
			 if(pass.length<6)
				{
				alert("Password has to be minimum six character");
				return false;
				}
			 else
		   return true;
		}

	function changeBorderHideDiv(text,id)
		{
		changeBorderNoError(text);
		hideDiv(id);
		} 