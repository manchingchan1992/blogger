package com.blogger.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.blogger.app.entity.Category;
import com.blogger.app.util.UrlRouteMapping;

public class ClientTest{
	public static void main(String[] args){
		 RestTemplate restTemplate = new RestTemplate();
//		 final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//			final HttpClient httpClient = HttpClientBuilder.create()
//			                                               .setRedirectStrategy(new LaxRedirectStrategy())
//			                                               .build();
//			factory.setHttpClient(httpClient);
//			restTemplate.setRequestFactory(factory);
		 List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		 converters.add(new MappingJackson2HttpMessageConverter());
		 restTemplate.setMessageConverters(converters);
		 
		 String requestURL = "http://localhost:8080/blogger"+UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION;
			List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);

		 Category category = new Category();
		 category.setName("Music");
		 category.setCname("Music");
		requestURL = "http://localhost:8080/blogger"+UrlRouteMapping.CATEGORYHANDLER_SAVE_ACTION;
		
		ResponseEntity<Category> response =  restTemplate.postForEntity(requestURL, category, Category.class);
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());

	}
}