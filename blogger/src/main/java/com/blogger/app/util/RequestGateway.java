package com.blogger.app.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RequestGateway{
	private static final Logger logger = LoggerFactory.getLogger(RequestGateway.class);
	static final String CSRF_PARAM_NAME = "_csrf";

	@Autowired
	private RestTemplate restTemplate;
	
	public Object sendRequest(HttpServletRequest request, HttpEntity<String> requestEntity, String requestURL, Class<?> className, Object modelToBeSubmit, HttpMethod method){
		HttpHeaders requestHeaders = restTemplHeaderBuilder(requestEntity, request);
		HttpEntity entity = new HttpEntity(modelToBeSubmit,requestHeaders);
		ResponseEntity response = restTemplate.exchange(
			    requestURL,
			    method,
			    entity,
			    className);
		return response.getBody();
	}
	
	public HttpHeaders restTemplHeaderBuilder(HttpEntity<String> requestEntity,HttpServletRequest request ){
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
	
	public String getServerAbsolutePath(HttpServletRequest request){
		return request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             request.getServerPort() + request.getContextPath(); 
	}
}