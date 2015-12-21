<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String message = (String) request.getAttribute("Message");
if (message != null) { 
%>
	<table id="tableMessage" width="100%">
		<tr><td>
    		<img src="home/images/spacer.gif" width="20" height="10" border="0" alt="">
    		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td><ul><li>(<%=message%>) &nbsp;<bean:message key="<%=message%>"/></li></ul></td></tr>
    		</table>
		</td></tr>
	</table>
	<img src="home/images/spacer.gif" width="10" height="2" border="0" alt="">
<% } %>

<%
String errMessage = (String) request.getAttribute("ErrorMessage");
if (errMessage != null) { 
%>
	<table id="tableErrorMessage" width="100%">
		<tr><td>
    		<img src="home/images/spacer.gif" width="20" height="10" border="0" alt="">
    		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td><ul><li>(<%=errMessage%>) &nbsp;<bean:message key="<%=errMessage%>"/></li></ul></td></tr>
    		</table>
		</td></tr>
	</table>
	<img src="home/images/spacer.gif" width="10" height="2" border="0" alt="">
<% } %>