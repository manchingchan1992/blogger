package com.blogger.app.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blogger.app.dao.CategoryDao;
import com.blogger.app.entity.Category;



public class CategoryServiceImp implements CategoryService{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private CategoryDao categoryDao;

    @Transactional (readOnly = true)
    public List<Category> getCategoryList(){
    	return categoryDao.getCategoryList();
    }
    
    @Transactional (readOnly = true)
    public Category getCategoryByName(String categoryname){
    	return categoryDao.getCategoryByName(categoryname);
    }
    
    @Transactional (readOnly = true)
    public Category getCategoryById(Integer categoryid){
    	return categoryDao.getCategoryById(categoryid);
    }
    @Transactional(readOnly = false)
    public void createCategory(Category newcategory){
//    	if(newcategory != null){
//    		logger.info("#####CategoryManager: " + newcategory.getId());
//    		logger.info("#####CategoryManager: " + newcategory.getCategoryname());
//    		logger.info("#####CategoryManager: " + newcategory.getEmail());
//    		logger.info("#####CategoryManager: " + newcategory.getPassword());
//    		categoryDao.addCategory(newcategory);
//    	}
    }
    @Transactional(readOnly = false)
    public void saveCategory(Category category){
    	if(category != null){
    		if (category.isNew()) {
    			categoryDao.addCategory(category);
    		} else {
    			categoryDao.saveCategory(category);
    		}
    	}
    }
    @Transactional(readOnly = false)
    public void deleteCategory(String id){
    	categoryDao.deleteCategory(id);
    }
    
}
