<html>
<body onLoad="document.Search.SearchKey.focus();">
Welcome ${user.getUserName()}
<p align="right"><a href="ControllerServlet?SUBMIT=LOGOUT&UserName=${user.getUserName()}">LOG OUT</a>
</p>
<script type="text/javascript">

var xmlHttp=null;
function GetSuggestion(str)
{
if (str.length==0)
  { 
  document.getElementById("txtHint").innerHTML="";
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
var url="XmlController?UserName=${user.getUserName()}&SUBMIT=XML&key="+str+"&cat="+get_radio_value();
xmlHttp.open("POST",url,false);
xmlHttp.send(null);
document.getElementById("txtHint").innerHTML=xmlHttp.responseText;
}
function get_radio_value()
{
for (var i=0; i < document.Search.SearchCat.length; i++)
   {
   if (document.Search.SearchCat[i].checked)
      {
      var rad_val = document.Search.SearchCat[i].value;
      return rad_val;
      }
   }
}
function allowOnlyNumeric(text,e)
	{	
		var key;
		var z=text.value;
		if(window.event)
		key=window.event.keyCode;
		if((key>=65 && key<=97)||(key==8))
		{			
			
			GetSuggestion(z);
			document.Search.SearchKey.focus();
			changeBorderNoError(text)
			return true;
		}
		else 
		{		
			text.value=z.substring(0,z.length-1);
			document.Search.SearchKey.focus();
			changeBorderError(text);
			return false;
		}
	}
function aler()
{
alert("key pressded");
}

</script>
SEARCH PAGE
<form name="Search" action="ControllerServlet" method="post">
<br><input type="text" style="display:none" name="UserName" value=${user.getUserName()}>
<br><input type="radio" name="SearchCat" value="FullName" checked>Search By Name
<br><input type="radio" name="SearchCat" value="Books">Search By Books
<br><br>
Enter Search Key <input type="text" name="SearchKey"  title="Enter Search Key here!!!" onkeyup="return allowOnlyNumeric(Search.SearchKey,event)">
<input type="submit" name="SUBMIT" value="SEARCH PROFILE">
<p>Suggestions: <span id="txtHint"></span>
<input type="text" style="display:none" name="UserName" value=${user.getUserName()}>
<input type="text" style="display:none" name="caller" value="Login">
<br><br><br>
<p align="left">
	<input type="SUBMIT" name="SUBMIT" value="GO TO HOME"></p>
</form>
<br><br><br>
</body>
</html>