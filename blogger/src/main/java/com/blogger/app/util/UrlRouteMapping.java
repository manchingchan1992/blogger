package com.blogger.app.util;

import javax.servlet.http.HttpServletRequest;

public class UrlRouteMapping {
	
	public static String getServerAbsolutePath(HttpServletRequest request){
		return request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             request.getServerPort() + request.getContextPath()+"/"; 
	}
}