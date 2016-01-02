package com.blogger.app.dao;

import java.util.List;

import com.blogger.app.entity.Category;
import com.blogger.app.util.HandlerException;




public interface CategoryDao {

	public List<Category> getCategoryList() throws HandlerException;
	
	public Category getCategoryByName(String categoryname) throws HandlerException;
	
	public Category getCategoryById(Integer id) throws HandlerException;
	
	public void saveCategory(Category category) throws HandlerException;
	
	public void addCategory(Category category) throws HandlerException;
	
	public void deleteCategory(Integer id) throws HandlerException;
}
