package com.blogger.app.handler;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.app.entity.Category;
import com.blogger.app.service.CategoryService;
import com.blogger.app.util.RequestGateway;
import com.blogger.app.util.UrlRouteMapping;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CategoryHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryHandler.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private CategoryService categoryService;
	

	// list page
	@RequestMapping(value = UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION)
	public @ResponseBody List<Category> showAllCategory() {
		List<Category> categoryList = categoryService.getCategoryList();
		return categoryList;
    }
	
	@RequestMapping(value = UrlRouteMapping.CATEGORYHANDLER_SAVE_ACTION, method = RequestMethod.POST)
	public Category saveCategory(@RequestBody Category category) {
		logger.info("save category");
		categoryService.saveCategory(category);
//		HttpHeaders responseHeaders = new HttpHeaders();
//	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
//	    return new ResponseEntity<Category>(category, responseHeaders, HttpStatus.CREATED);
		return category;
	}
	
	@RequestMapping(value = UrlRouteMapping.CATEGORYHANDLER_SELECT_ACTION+"{id}")
	public @ResponseBody Category selectCategory(@PathVariable("id") int id) {
		Category category = categoryService.getCategoryById(id);
		return category;
    }
	
	@RequestMapping(value = UrlRouteMapping.CATEGORYHANDLER_DELETE_ACTION+"{id}")
	public @ResponseBody Integer deleteCategory(@PathVariable("id") int id) {
		logger.info("Delete category: id:"+id);
		categoryService.deleteCategory(id);
		return RequestGateway.STATUS_SUCCESS;
    }
}
