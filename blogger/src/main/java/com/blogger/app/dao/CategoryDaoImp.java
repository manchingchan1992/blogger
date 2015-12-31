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
import com.blogger.app.util.HandlerException;
import com.blogger.app.entity.Category;




public class CategoryDaoImp extends CustomHibernateDaoSupport implements CategoryDao{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Category> getCategoryList() throws HandlerException {
		logger.info("Getting List of categories!");
		try {
			List categoryList = getHibernateTemplate().find("FROM Category ORDER BY id ASC");
			logger.info("categoryList.size:"+categoryList.size());
	        return categoryList;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception :" + e.getMessage());
			throw new HandlerException("000",e.getMessage());
		}
	}
	
	public Category getCategoryById(Integer id) throws HandlerException {
		logger.info("Getting an category by id!id:"+id);
        String query="select categoryid, categoryname, password, email, enabled , expired from categorys where categoryid=?";
        if (id == null)
        	return null;
        try {
        	List categoryList = getHibernateTemplate().find("FROM Category where id = ?", id);
        	logger.info("categoryList.size:"+categoryList.size());
        	Category category = (Category)categoryList.get(0);
            return category;
        }
        catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception :" + e.getMessage());
			throw new HandlerException("000",e.getMessage());
		}
	}
	
	public Category getCategoryByName(String name) throws HandlerException {
		Category category = (Category)getHibernateTemplate().find("FROM categorys where categoryid = ?", name);
        return category;
	}
	
	public void saveCategory(Category category) throws HandlerException {
        logger.info("Saving Category");
        try {
			getHibernateTemplate().update(category);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception :" + e.getMessage());
			throw new HandlerException("000",e.getMessage());
		}
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
	
	public void addCategory(Category category) throws HandlerException {
		logger.info("Adding Category");
		try {
			Date today = new Date();
			category.setCreateDate(today);
			getHibernateTemplate().save(category);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception :" + e.getMessage());
			throw new HandlerException("000",e.getMessage());
		}
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
	
	public void deleteCategory(String id) throws HandlerException {
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
