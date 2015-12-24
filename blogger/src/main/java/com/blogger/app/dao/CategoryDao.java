package com.blogger.app.dao;

import java.util.List;

import com.blogger.app.entity.Category;




public interface CategoryDao {

	public List<Category> getCategoryList();
	
	public Category getCategoryByName(String categoryname);
	
	public Category getCategoryById(Integer id);
	
	public void saveCategory(Category category);
	
	public void addCategory(Category category);
	
	public void deleteCategory(String id);
}
