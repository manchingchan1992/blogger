package com.blogger.app.service;

import java.io.Serializable;
import java.util.List;

import com.blogger.app.entity.Category;
import com.blogger.app.util.HandlerException;



public interface CategoryService extends Serializable{

	public void createCategory(Category category) throws HandlerException;
	
	public void saveCategory(Category category) throws HandlerException;
	
	public void deleteCategory(String id) throws HandlerException;
	
	public List<Category> getCategoryList() throws HandlerException;
	
	public Category getCategoryByName(String categoryname)throws HandlerException ;
	
	public Category getCategoryById(Integer categoryid)throws HandlerException;
}
