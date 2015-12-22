package com.blogger.app.handler;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogger.app.entity.Category;
import com.blogger.app.service.CategoryService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CategoryHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryHandler.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private CategoryService categoryService;
	

	// list page
	@RequestMapping(value = "/handler/categoryHandler/list")
	public @ResponseBody List<Category> showAllCategory() {
		List<Category> categoryList = categoryService.getCategoryList();
		return categoryList;
    }
}
