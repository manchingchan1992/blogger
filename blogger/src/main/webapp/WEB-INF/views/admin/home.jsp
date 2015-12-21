<%@ include file="/WEB-INF/views/include/setting.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"  import="java.sql.*"%>
<%@ page language="java" import="java.util.*" %>

<!-- page header -->
<jsp:include page="/WEB-INF/views/admin/header.jsp" flush="true" >
   <jsp:param name="navStr"	value="Welcome to CMS" />
</jsp:include>
<!-- start of Body -->
<jsp:include page="/WEB-INF/views/admin/message.jsp" flush="true"/>
<p style="width: 100%;line-height:25px; text-align:center;color: red; font-size: 25 px; height: 50%">觀迎進入CMS<br />流動平台(如手機,平板電腦)請按左上角展開目錄</p>

<!-- End of Body -->
<jsp:include page="/WEB-INF/views/admin/footer.jsp" flush="true" />