<%@ include file="/WEB-INF/views/include/setting.jsp" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.blogger.app.entity.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.blogger.app.entity.Menu" %>
<%
	
String navStr = (String)request.getParameter("navStr");
	if (navStr == null)
		navStr = "";
	
	String userName = "";
	String regionDescription = "";
	User userbeaninfo = (User) request.getSession().getAttribute("USERBEAN");
	if (userbeaninfo != null) {
		userName = userbeaninfo.getLoginName();
		//regionDescription = userbeaninfo.getRegionDescription();
	}
	
	String onload = (String) request.getParameter("onload");
%>
<html>
	<head>
	<title><spring:message code="cms.title"/></title>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- <link href="./css/mainStyles.css" rel="stylesheet" type="text/css">-->
	<!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.css" rel="stylesheet">
	
	<!-- MetisMenu CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">


    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath()%>/resources/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script language=JavaScript>
		function mhHover(tbl, idx, cls)	{
			var t, d;
			if (document.getElementById)
				t = document.getElementById(tbl);
			else
				t = document.all(tbl);
			if (t == null) return;
			if (t.getElementsByTagName)
				d = t.getElementsByTagName("TD");
			else
				d = t.all.tags("TD");
			if (d == null) return;
			if (d.length <= idx) return;
			d[idx].className = cls;
		}
	</script>
	<!-- jQuery Version 1.11.0 -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1.js"></script>	
	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	<!-- <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" /> --> 
</head>
<body data-floating-nav="tb">
<div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="show-sidebar">
					<i class="fa fa-bars fa-lg"></i>
				</a><a class="navbar-brand" href="#" style="padding: 15px 10px 15px 10px;"><spring:message code="cms.title"/></a>
            </div>
        
            <!-- Top Menu Items -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                	<a href="MainAction"><spring:message code="cms.home"/></a>
                </li>
                <li class="dropdown">
                	<a href="#" target="_blank"><spring:message code="cms.instruction"/></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%= userName %>,<%= regionDescription %> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/logout"><i class="fa fa-fw fa-power-off"></i> <spring:message code="cms.logout"/></a>
                        </li>
                    </ul>
                </li>
            </ul>
             </div>   

			<%!
	
	public String makeMenu(ArrayList beanList,  int level) {
		//String [] finalLevel = {"a","b","c","d","e"};
		String temp = "";
		for (int i = 0; i < beanList.size(); i++) {
			Menu subMenu = (Menu)beanList.get(i);
			if (!subMenu.getMenuCommand().equals("") && !subMenu.getMenuCommand().contains("MainAction")) { 
				temp += "<li><a href='"+subMenu.getMenuCommand()+"'><i class='fa fa-fw fa-file'></i> "+subMenu.getMenuName()+"</a></li>";
			}
			
		
		}
		return temp;
			}
		
%>
		<div class="navbar-default sidebar" role="navigation">
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="sidebar-nav">
                    <ul class="nav" id="side-menu">
<%
ArrayList<Menu> menulist = null;
if (request.getSession().getAttribute("MenuList") != null) {
	menulist=(ArrayList) request.getSession().getAttribute("MenuList");
}
	if (menulist != null) {
	for (int i=0; i<menulist.size(); i++) {
		Menu bean = (Menu) menulist.get(i);
		if (bean != null) {
			Menu temproot=null;
			temproot = bean;
			%>
			
                <li>
			 		<a href="#"><i class="fa fa-sitemap fa-fw"></i> <%=temproot.getMenuName()%> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
						<%=makeMenu(bean.getSubmenu(),  1)%>	
						</ul>
				</li>
    <%			
		}
	}
	} 
%>	            
				</ul>
			</div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
		 </nav>

	<div class="page-wrapper" style="overflow-x: auto;">
		<div class="container-fluid">
			<div id="header" >
			</div>
			<div class="col-md-12 heading-title">
				<div><%=navStr%></div>
			</div>
			<div class="divider"></div>
   