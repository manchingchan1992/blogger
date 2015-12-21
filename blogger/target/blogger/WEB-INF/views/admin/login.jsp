<%@ include file="/WEB-INF/views/include/setting.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Remote Reception System Login</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/rrStyle.css"/>" media="screen" />
<script type="text/javascript" src="<s:url value="/js/jquery_144.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/uiEffects.js"/>"></script>
<script type="text/javascript">
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function resetform(){
	document.f.username.value="";
	document.f.password.value="";
}

function submitform(){
	document.f.submit();
}

function letternumber(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) || 
	    (key==9) || (key==13) || (key==27) )
	   return true;

	// alphas and numbers
	else if ((("abcdefghijklmnopqrstuvwxyz0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}

function upper(ustr)
{

    var str=ustr.value;
    ustr.value=str.toUpperCase();
}

function lower(ustr)
{

    var str=ustr.value;
    ustr.value=str.toLowerCase();
}
</script>
</head>

<body onload="document.f.username.focus();">
<table width="564" border="0" align="center" cellpadding="0" cellspacing="0" class="formPos">
  <tr>
    <td align="left" valign="middle">
    <div class="loginBkgd">
    <div class="loginForm">
   	<form name="f" action="<c:url value='/j_spring_security_check'/>" method="post">    
      <table width="300" border="0" cellspacing="0" cellpadding="6">
        <tr>
          <td><spring:message code="login.loginName"/>:&nbsp;
            <label for="select"></label>
            <label for="textfield"></label></td>
          <td width="232"><label for="username"></label>
            <!-- <input type="text" name="username" id="username" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/> -->
            <input type="text" name="username" id="username" onkeyup="lower(this)" onKeyPress="return letternumber(event)"/>
            </td>
        </tr>
        <tr>
          <td><spring:message code="login.password"/>:&nbsp;</td>
          <td><label for="password"></label>
            <input type="password" name="password" id="password" /></td>
        </tr>
        <!--  
        <tr>
          <td colspan="2" align="left" valign="middle"><input type="checkbox" name="forgetPswd" id="forgetPswd" />
            Don't ask for my password for two weeks</td>
        </tr>
        -->
        <tr>
          <td colspan="2" align="center" valign="middle"><input type="reset" name="Reset" id="Reset" value="Reset" /> &nbsp;
          	<input type="submit" name="Submit" id="Submit" value="Submit" />           
          </td>
        </tr>
      </table>
      <input type="hidden" 
                     name="${_csrf.parameterName}" value="${_csrf.token}" />
      
     </form>
      <div class="msg_login">
      	<c:if test="${not empty param.login_error}">	      
	        Your login attempt was not successful, try again.<br/><br/>
	        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.	     
	    </c:if>
      </div>
      </div>
    </div>
    </td>
  </tr>
</table>
</body>
</html>
