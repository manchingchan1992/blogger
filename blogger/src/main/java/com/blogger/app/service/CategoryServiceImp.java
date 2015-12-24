package com.blogger.app.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.blogger.app.dao.CategoryDao;
import com.blogger.app.entity.Category;



public class CategoryServiceImp implements CategoryService{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
    
    public List<Category> getCategoryList(){
    	return categoryDao.getCategoryList();
    }
    
    public Category getCategoryByName(String categoryname){
    	return categoryDao.getCategoryByName(categoryname);
    }
    
    public Category getCategoryById(Integer categoryid){
    	return categoryDao.getCategoryById(categoryid);
    }
    
    public void createCategory(Category newcategory){
//    	if(newcategory != null){
//    		logger.info("#####CategoryManager: " + newcategory.getId());
//    		logger.info("#####CategoryManager: " + newcategory.getCategoryname());
//    		logger.info("#####CategoryManager: " + newcategory.getEmail());
//    		logger.info("#####CategoryManager: " + newcategory.getPassword());
//    		categoryDao.addCategory(newcategory);
//    	}
    }
    
    public void saveCategory(Category category){
    	if(category != null){
    		if (getCategoryById(category.getId())==null) {
    			categoryDao.addCategory(category);
    		} else {
    			categoryDao.saveCategory(category);
    		}
    	}
    }
    
    public void deleteCategory(String id){
    	categoryDao.deleteCategory(id);
    }
    
}
