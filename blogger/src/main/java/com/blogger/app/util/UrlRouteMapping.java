package com.blogger.app.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class UrlRouteMapping {
	
	public final static String CATEGORY_FORM_URL = "admin/category/categoryform";
	public final static String CATEGORY_LIST_URL = "admin/category/list";
	public final static String CATEGORY_LIST_ACTION = "/admin/category";
	public final static String CATEGORY_SHOW_FORM_ACTION = "/admin/category/add";
	public final static String CATEGORY_SAVE_ACTION = "/admin/category/save";
	public final static String CATEGORY_SHOW_UPDATE_FORM_ACTION = "/admin/category/{id}/update";
	public final static String CATEGORY_SELECT_ACTION = "/admin/category/{id}";
	public final static String CATEGORY_SHOW_ACTION = "/admin/category/show";
	public final static String CATEGORY_DELETE_ACTION = "/admin/category/{id}/delete";


	public final static String CATEGORYHANDLER_LIST_ACTION = "/handler/categoryHandler/list";
	public final static String CATEGORYHANDLER_SAVE_ACTION = "/handler/categoryHandler/save";
	public final static String CATEGORYHANDLER_SELECT_ACTION = "/handler/categoryHandler/select/";
	public final static String CATEGORYHANDLER_DELETE_ACTION = "/handler/categoryHandler/delete/";

	public final static String ARTICLE_LIST_ACTION = "/admin/article";
	public final static String ARTICLE_LIST_URL = "admin/article/list";
	public final static String ARTICLE_SHOW_FORM_ACTION = "/admin/article/add";
	public final static String ARTICLE_FORM_URL = "admin/article/articleform";

	public final static String ARTICLE_HANDLER_LIST_ACTION = "/handler/articleHandler/list";


	
	public static final String CSRF_PARAM_NAME = "_csrf";
	private static final Logger logger = LoggerFactory.getLogger(UrlRouteMapping.class);

	
	public static String getServerAbsolutePath(HttpServletRequest request){
		return request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             request.getServerPort() + request.getContextPath(); 
	}
	
	public static HttpHeaders restTemplHeaderBuilder(HttpEntity<String> requestEntity,HttpServletRequest request ){
		logger.debug("requestEntity.getBody:"+requestEntity.getBody());
		logger.debug("requestEntity.getheader:"+requestEntity);
		HttpHeaders requestHeaders = new HttpHeaders();
		HttpHeaders headers = requestEntity.getHeaders();
		String csrf_token = request.getParameter(CSRF_PARAM_NAME);
		if (csrf_token !=null && csrf_token.trim().length()>0){
			logger.debug("CSRF_PARAM_NAME:"+request.getParameter(CSRF_PARAM_NAME));
			requestHeaders.add("X-CSRF-TOKEN",request.getParameter(CSRF_PARAM_NAME));
		}
		requestHeaders.add(HttpHeaders.COOKIE, headers.getFirst(HttpHeaders.COOKIE));
		logger.debug("requestHeaders.Cookie:"+requestHeaders.getFirst(HttpHeaders.COOKIE));
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		return requestHeaders;
	}
}