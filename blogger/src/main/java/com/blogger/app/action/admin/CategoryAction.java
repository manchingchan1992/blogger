package com.blogger.app.action.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blogger.app.entity.Category;
import com.blogger.app.entity.User;
import com.blogger.app.util.HandlerException;
import com.blogger.app.util.MainExceptionHandler;
import com.blogger.app.util.RequestGateway;
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
	CategoryFormValidator categoryFormValidator;

	@Autowired
	private RequestGateway requestGateway;

	@Autowired
	private MainExceptionHandler exceptionHandler;

	// list page
	@RequestMapping(value = UrlRouteMapping.CATEGORY_LIST_ACTION, method = RequestMethod.GET)
	public String showAllCategory(Model model, HttpServletRequest request, HttpEntity<String> requestEntity) {

		try {
			String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION;
			logger.debug("showAllCategory():"+requestURL);
			Category[] categoryList  = (Category[]) requestGateway.sendRequest(request, requestEntity, requestURL, Category[].class, null, HttpMethod.GET);

			//	    	List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);
			logger.info("categoryList size:"+categoryList.length);
			model.addAttribute("categoryList", categoryList);
		}
		catch (HttpStatusCodeException e) {
			exceptionHandler.handleJsonHandlerError(model, e);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Failed! Error:"+e1.getMessage());
		}
		return UrlRouteMapping.CATEGORY_LIST_URL;

	}

	// show add user form
	@RequestMapping(value = UrlRouteMapping.CATEGORY_SHOW_FORM_ACTION, method = RequestMethod.GET)
	public String showAddCategoryForm(Model model, HttpServletRequest request, HttpEntity<String> requestEntity) {
		logger.debug("showAddCategoryForm()");
		Category category = new Category();
		populateDefaultModel(model, request, requestEntity);
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
			if(category.isNew()){
				category.setCreateUser(user.getLoginName());
			}
			else 
				category.setUpdateUser(user.getLoginName());

			try {
				//					HttpHeaders requestHeaders = UrlRouteMapping.restTemplHeaderBuilder(requestEntity, request);
				//					HttpEntity<Category> categoryEntity = new HttpEntity<Category>(category,requestHeaders);
				//					ResponseEntity<Category> categoryResponse = restTemplate.exchange(
				//						    requestURL,
				//						    HttpMethod.POST,
				//						    categoryEntity,
				//						    Category.class);
				//					category = categoryResponse.getBody();
				String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_SAVE_ACTION;
				category = (Category)requestGateway.sendRequest(request, requestEntity, requestURL, Category.class, category, HttpMethod.POST);
				//					category = restTemplate.postForObject(requestURL,category, Category.class);
				redirectAttributes.addFlashAttribute("css", "success");
				if(category.isNew()){
					redirectAttributes.addFlashAttribute("msg", "Category added successfully!");
				}else{
					redirectAttributes.addFlashAttribute("msg", "Category updated successfully!");
				}
			}
			catch (HttpStatusCodeException e) {
				exceptionHandler.handleJsonHandlerError(redirectAttributes, e);
			}
			catch (Exception e1) {
				e1.printStackTrace();
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Failed! Error:"+e1.getMessage());
			}

			// POST/REDIRECT/GET
			return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;

			// POST/FORWARD/GET
			// return "user/list";

		}

	}

	// show update form
	@RequestMapping(value = UrlRouteMapping.CATEGORY_SHOW_UPDATE_FORM_ACTION, method = RequestMethod.GET)
	public String showUpdateCategoryForm(@PathVariable("id") int id, Model model, HttpServletRequest request, HttpEntity<String> requestEntity
			,final RedirectAttributes redirectAttributes) {

		logger.debug("showUpdateCategoryForm() : {}", id);
		try {
			String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_SELECT_ACTION+id;
			Category category = (Category)requestGateway.sendRequest(request, requestEntity, requestURL, Category.class, null, HttpMethod.GET);
			populateDefaultModel(model, request, requestEntity);
			model.addAttribute("categoryForm", category);
		}
		catch (HttpStatusCodeException e) {
			exceptionHandler.handleJsonHandlerError(redirectAttributes, e);
			return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;
		}
		catch (Exception e1) {
			e1.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Failed! Error:"+e1.getMessage());
			return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;
		}

		return UrlRouteMapping.CATEGORY_FORM_URL;

	}

	private void populateDefaultModel(Model model, HttpServletRequest request, HttpEntity<String> requestEntity) {
		try {
			String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION;
			logger.debug("populateDefaultModel():"+requestURL);
			Category[] responseList  = (Category[]) requestGateway.sendRequest(request, requestEntity, requestURL, Category[].class, null, HttpMethod.GET);

			//		    	List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);
			logger.info("responseList size:"+responseList.length);
			Map<Integer, String> categoryList = new LinkedHashMap<Integer, String>();
			for (Category category : responseList){
				categoryList.put(category.getId(), category.getName()+" - "+category.getCname());
			}
			model.addAttribute("categoryList", categoryList);
		}
		catch (HttpStatusCodeException e) {
			exceptionHandler.handleJsonHandlerError(model, e);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Failed! Error:"+e1.getMessage());
		}
	}

	// show category
	@RequestMapping(value = UrlRouteMapping.CATEGORY_SELECT_ACTION, method = RequestMethod.GET)
	public String showCategory(@PathVariable("id") int id, Model model, HttpServletRequest request, HttpEntity<String> requestEntity
			,final RedirectAttributes redirectAttributes) {

		logger.debug("showCategory() id: {}", id);

		try {
			String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_SELECT_ACTION+id;
			Category category = (Category)requestGateway.sendRequest(request, requestEntity, requestURL, Category.class, null, HttpMethod.GET);
			if (category == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Category not found");
			}
			model.addAttribute("category", category);
		}
		catch (HttpStatusCodeException e) {
			exceptionHandler.handleJsonHandlerError(redirectAttributes, e);
			return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;
		}
		catch (Exception e1) {
			e1.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Failed! Error:"+e1.getMessage());
			return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;
		}

		return UrlRouteMapping.CATEGORY_SHOW_ACTION;
	}

	// delete category
	@RequestMapping(value = UrlRouteMapping.CATEGORY_DELETE_ACTION, method = RequestMethod.POST)
	public String deleteCategory(@PathVariable("id") int id,  HttpServletRequest request, HttpEntity<String> requestEntity,
			final RedirectAttributes redirectAttributes) {

		logger.debug("deleteCategory() : {}", id);

		try {
			String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_DELETE_ACTION+id;
			Integer returnCode = (Integer)requestGateway.sendRequest(request, requestEntity, requestURL, Integer.class, null, HttpMethod.POST);
			if (returnCode.equals(RequestGateway.STATUS_SUCCESS)){
				redirectAttributes.addFlashAttribute("css", "success");
				redirectAttributes.addFlashAttribute("msg", "Category is deleted!");
			}
			else {
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Category fail to delete!");
			}
		}
		catch (HttpStatusCodeException e) {
			exceptionHandler.handleJsonHandlerError(redirectAttributes, e);
			return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;
		}
		catch (Exception e1) {
			e1.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Failed! Error:"+e1.getMessage());
			return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;
		}



		return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;

	}
}
