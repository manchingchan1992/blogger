package com.blogger.app.action.admin;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.blogger.app.entity.Category;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CategoryAction {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryAction.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	private RestTemplate restTemplate;
	
	public void setRestTemplate(RestTemplate restTemplate){
		this.restTemplate = restTemplate;
	}
	
	// list page
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String showAllCategory(Model model, HttpServletRequest request) {
		logger.debug("showAllCategory()");
		List<Category> categoryList = restTemplate.getForObject(request.getContextPath()+"/handler/categoryHandler/list", List.class);
				
		model.addAttribute("categoryList", categoryList);
		return "admin/category/list";

	}
}
