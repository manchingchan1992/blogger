package com.blogger.app.action.admin;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.blogger.app.entity.Category;
import com.blogger.app.util.UrlRouteMapping;

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
	
	// list page
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String showAllCategory(Model model, HttpServletRequest request) {
		String requestURL = UrlRouteMapping.getServerAbsolutePath(request)+"handler/categoryHandler/list";
		logger.debug("showAllCategory():"+requestURL);
		List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);
		logger.debug("showAllCategory():categoryList"+(categoryList!= null));
		logger.info("categoryList.size:"+categoryList.size());

		model.addAttribute("categoryList", categoryList);
		return "admin/category/list";

	}
}
