package com.blogger.app.action.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blogger.app.entity.Category;
import com.blogger.app.entity.User;
import com.blogger.app.util.HandlerException;
import com.blogger.app.util.MainExceptionHandler;
import com.blogger.app.util.UrlRouteMapping;
import com.blogger.app.validator.CategoryFormValidator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CategoryAction {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryAction.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	CategoryFormValidator categoryFormValidator;
	
	@Autowired
	private MappingJackson2HttpMessageConverter jsonConverter;
	
	// list page
	@RequestMapping(value = UrlRouteMapping.CATEGORY_LIST_ACTION, method = RequestMethod.GET)
	public String showAllCategory(Model model, HttpServletRequest request) {
		String requestURL = UrlRouteMapping.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION;
		logger.debug("showAllCategory():"+requestURL);
		try {
	    	List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);
	    	logger.info("categoryList size:"+categoryList.size());
			model.addAttribute("categoryList", categoryList);
	    }
	    catch (HttpStatusCodeException e) {
	        MainExceptionHandler.handleJsonHandlerError(model, e);
	    }
		return UrlRouteMapping.CATEGORY_LIST_URL;

	}

	// show add user form
	@RequestMapping(value = UrlRouteMapping.CATEGORY_SHOW_FORM_ACTION, method = RequestMethod.GET)
	public String showAddCategoryForm(Model model) {
		logger.debug("showAddCategoryForm()");
		Category category = new Category();
		model.addAttribute("categoryForm", category);
		return UrlRouteMapping.CATEGORY_FORM_URL;
	}
	
		
		// save or update user
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = UrlRouteMapping.CATEGORY_SAVE_ACTION, method = RequestMethod.POST)
		public String saveCategory(@ModelAttribute("categoryForm") Category category,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpEntity<String> requestEntity) {

			logger.debug("saveCategory(): {}",category);
			categoryFormValidator.validate(category, result);
			if (result.hasErrors()) {
				return UrlRouteMapping.CATEGORY_FORM_URL;
			} else {
				HttpSession session = request.getSession();		
			    User user = (User)session.getAttribute("USERBEAN");
			    category.setCreateUser(user.getLoginName());
				String requestURL = UrlRouteMapping.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_SAVE_ACTION;
				try {
					logger.debug("requestEntity.getBody:"+requestEntity.getBody());
					logger.debug("requestEntity.getheader:"+requestEntity);
					HttpHeaders requestHeaders = new HttpHeaders();
					HttpHeaders headers = requestEntity.getHeaders();
					requestHeaders.add(HttpHeaders.COOKIE, headers.getFirst(HttpHeaders.COOKIE));
					logger.debug("requestHeaders.Cookie:"+requestHeaders.getFirst(HttpHeaders.COOKIE));
					requestHeaders.setContentType(MediaType.APPLICATION_JSON);
					HttpEntity<Category> categoryEntity = new HttpEntity<Category>(category,requestHeaders);
					ResponseEntity<Category> categoryResponse = restTemplate.exchange(
						    requestURL,
						    HttpMethod.POST,
						    categoryEntity,
						    Category.class);
					category = categoryResponse.getBody();
//					category = restTemplate.postForObject(requestURL,category, Category.class);
					redirectAttributes.addFlashAttribute("css", "success");
					if(category.isNew()){
					  redirectAttributes.addFlashAttribute("msg", "Category added successfully!");
					}else{
					  redirectAttributes.addFlashAttribute("msg", "Category updated successfully!");
					}
			    }
			    catch (HttpStatusCodeException e) {
			        MainExceptionHandler.handleJsonHandlerError(redirectAttributes, e);
			    }
				
				
				// POST/REDIRECT/GET
				return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;

				// POST/FORWARD/GET
				// return "user/list";

			}

		}
}
