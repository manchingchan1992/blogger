package com.blogger.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import java.security.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.blogger.app.util.CustomHibernateDaoSupport;
import com.blogger.app.entity.Category;




public class CategoryDaoImp extends CustomHibernateDaoSupport implements CategoryDao{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
	public List<Category> getCategoryList(){
		logger.info("Getting List of categories!");
        List categoryList = getHibernateTemplate().find("FROM Category ORDER BY id ASC");
		logger.info("categoryList.size:"+categoryList.size());
        return categoryList;
	}
	
	public Category getCategoryById(String id){
		logger.info("Getting an category by id!");
        String query="select categoryid, categoryname, password, email, enabled , expired from categorys where categoryid=?";
        Category category = (Category)getHibernateTemplate().find("FROM categorys where categoryid = ?", id);
        return category;
	}
	
	public Category getCategoryByName(String name){
		Category category = (Category)getHibernateTemplate().find("FROM categorys where categoryid = ?", name);
        return category;
	}
	
	public void saveCategory(Category category) {
        logger.info("Saving Category");
        
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date today = new Date();
        //String now = sdf.format(today);
        //String now = (new java.util.Date()).toString();
//        String query = "update categorys set categoryname=:categoryname, password=:password, email=:email, enabled=:enabled, expired=:expired where categoryid=:categoryid";
//        int count = getSimpleJdbcTemplate().update(query,
//            new MapSqlParameterSource().addValue("categoryid", category.getId())
//            	.addValue("categoryname", category.getCategoryname())
//                .addValue("password", category.getPassword())
//                .addValue("email", category.getEmail())
//                .addValue("enabled", category.isEnabled()) 
//                .addValue("expired", category.isExpired()) );
//        		
//        logger.info("Save Category, Rows affected: " + count);
    }
	
	public void addCategory(Category category){
		logger.info("Adding Category");
		String query = "insert into categorys(categoryname, password, email, enabled, expired) values(?, md5(?), ?, ?, ?)";
		String query2 = "insert into categorys_x_groups(categoryid, groupid) values(?, '1')";
//
//		
//		logger.info("####CategoryDao " + category.getCategoryname());
//		logger.info("####CategoryDao " + category.getEmail());
//		logger.info("####CategoryDao " + category.isEnabled());
//		logger.info("####CategoryDao " + category.getPassword());
//		logger.info("####CategoryDao " + category.isExpired());
//		
//		/*
//		int count = getSimpleJdbcTemplate().update(query,
//	            new MapSqlParameterSource().addValue("categoryname", category.getCategoryname())
//	                .addValue("password", category.getPassword())
//	            	//.addValue("password", "123456")
//	                .addValue("email", category.getEmail())
//	                .addValue("enabled", category.isEnabled()), category.getPassword() );
//	    */
//		
//		int count = getSimpleJdbcTemplate().update(query, category.getCategoryname(), category.getPassword(), category.getEmail(), category.isEnabled(), category.isExpired());
//		
//		if(count == 1)
//		{
//			category.setId(getCategoryByName(category.getCategoryname()).getId());
//			logger.info("######New category'd Id = " + category.getId());
//			getSimpleJdbcTemplate().update(query2,category.getId());
//		}
		
//	    logger.info("Add Category, Rows affected: " + count);
	}
	
	public void deleteCategory(String id) {
        logger.info("Deleting Category");
        
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date today = new Date();
        //String now = sdf.format(today);
        //String now = (new java.util.Date()).toString();
//        String query1 = "delete from categorys_x_groups where categoryid=?";
//        String query2 = "delete from categorys where categoryid=?";
//        logger.info("#######"+query1 + " id = " + id);
//        try{
//        	getSimpleJdbcTemplate().update(query1,id);
//        	getSimpleJdbcTemplate().update(query2,id);
//        }
//        catch(Exception e){
//        	e.getMessage();
//        }
  
        //logger.info("Delete Category, Rows affected: " + count);
    }
	
	
}
