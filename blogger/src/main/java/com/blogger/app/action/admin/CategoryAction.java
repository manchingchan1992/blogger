package com.blogger.app.action.admin;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blogger.app.entity.Category;
import com.blogger.app.entity.User;
import com.blogger.app.util.UrlRouteMapping;
import com.blogger.app.validator.CategoryFormValidator;

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
	
	// list page
	@RequestMapping(value = UrlRouteMapping.CATEGORY_LIST_ACTION, method = RequestMethod.GET)
	public String showAllCategory(Model model, HttpServletRequest request) {
		String requestURL = UrlRouteMapping.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION;
		logger.debug("showAllCategory():"+requestURL);
		List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);
		logger.debug("showAllCategory():categoryList"+(categoryList!= null));
		logger.info("categoryList.size:"+categoryList.size());

		model.addAttribute("categoryList", categoryList);
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
				final RedirectAttributes redirectAttributes, HttpServletRequest request) {

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
					ResponseEntity<Category> response =  restTemplate.postForEntity(requestURL, category, Category.class);
					// Add message to flash scope
					HttpStatus status = response.getStatusCode();
					if (status.is2xxSuccessful()){
						redirectAttributes.addFlashAttribute("css", "success");
						if(category.isNew()){
						  redirectAttributes.addFlashAttribute("msg", "Category added successfully!");
						}else{
						  redirectAttributes.addFlashAttribute("msg", "Category updated successfully!");
						}
					}
					else {
						redirectAttributes.addFlashAttribute("css", "danger");
						redirectAttributes.addFlashAttribute("msg", "Category update failed! error Code:"+status.value());
					}
					
				}
				catch (Exception e){
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Category update failed!");
				}
				
				
				// POST/REDIRECT/GET
				return "redirect:"+UrlRouteMapping.CATEGORY_LIST_ACTION;

				// POST/FORWARD/GET
				// return "user/list";

			}

		}
}
