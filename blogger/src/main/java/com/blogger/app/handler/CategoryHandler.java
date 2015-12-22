package com.blogger.app.handler;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// list page
	@RequestMapping(value = "/handler/category/list")
	public List<Category> showAllCategory() {
		List<Category> categoryList = categoryService.getCategoryList();
		return categoryList;
    }
}
