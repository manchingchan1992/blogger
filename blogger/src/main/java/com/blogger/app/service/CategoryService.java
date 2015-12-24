package com.blogger.app.service;

import java.io.Serializable;
import java.util.List;

import com.blogger.app.entity.Category;



public interface CategoryService extends Serializable{

	public void createCategory(Category category);
	
	public void saveCategory(Category category);
	
	public void deleteCategory(String id);
	
	public List<Category> getCategoryList();
	
	public Category getCategoryByName(String categoryname);
	
	public Category getCategoryById(Integer categoryid);
}
