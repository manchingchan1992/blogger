package com.blogger.app.util;

import javax.servlet.http.HttpServletRequest;

public class UrlRouteMapping {
	
	public final static String CATEGORY_FORM_URL = "admin/category/categoryform";
	public final static String CATEGORY_LIST_URL = "admin/category/list";
	public final static String CATEGORY_LIST_ACTION = "/admin/category";
	public final static String CATEGORY_SHOW_FORM_ACTION = "/admin/category/add";
	public final static String CATEGORY_SAVE_ACTION = "/admin/category/save";
	public final static String CATEGORYHANDLER_LIST_ACTION = "handler/categoryHandler/list";
	public final static String CATEGORYHANDLER_SAVE_ACTION = "handler/categoryHandler/save";

	
	public static String getServerAbsolutePath(HttpServletRequest request){
		return request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             request.getServerPort() + request.getContextPath()+"/"; 
	}
}