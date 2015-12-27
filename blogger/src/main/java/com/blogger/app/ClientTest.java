package com.blogger.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.blogger.app.entity.Category;
import com.blogger.app.util.HandlerException;
import com.blogger.app.util.UrlRouteMapping;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ClientTest{
	public static void main(String[] args){
		 RestTemplate restTemplate = new RestTemplate();
//		 final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//			final HttpClient httpClient = HttpClientBuilder.create()
//			                                               .setRedirectStrategy(new LaxRedirectStrategy())
//			                                               .build();
//			factory.setHttpClient(httpClient);
//			restTemplate.setRequestFactory(factory);
		 MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		 List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		 converters.add(new MappingJackson2HttpMessageConverter());
		 restTemplate.setMessageConverters(converters);
		 
		 String requestURL = "http://localhost:8080/blogger"+UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION;
		 HttpHeaders headers = new HttpHeaders();
		    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		    HttpEntity<String> request = new HttpEntity<String>(headers);
		    try {
		    	List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);
		    	System.out.println("categoryList size:"+categoryList.size());

		    }
		    catch (HttpStatusCodeException e) {
		        String responseBody = e.getResponseBodyAsString();
		        String statusText = e.getStatusText();
		        try {
					HandlerException ex = jsonConverter.getObjectMapper().readValue(e.getResponseBodyAsByteArray(),HandlerException.class);
					System.out.println("error code:"+ex.getErrCode());
					System.out.println("error msg:"+ex.getErrMsg());
		        } catch (JsonParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JsonMappingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    System.out.println("responseBody"+responseBody);
			    System.out.println("statusText"+statusText);

		        // log or process either of these...
		        // you'll probably have to unmarshall the XML manually (only 2 fields so easy)
		    }
//		 List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);

//		 Category category = new Category();
//		 category.setName("Music");
//		 category.setCname("Music");
//		requestURL = "http://localhost:8080/blogger"+UrlRouteMapping.CATEGORYHANDLER_SAVE_ACTION;
//		
//		ResponseEntity<Category> response =  restTemplate.postForEntity(requestURL, category, Category.class);
//		System.out.println(response.getHeaders());
//		System.out.println(response.getBody());

	}
}