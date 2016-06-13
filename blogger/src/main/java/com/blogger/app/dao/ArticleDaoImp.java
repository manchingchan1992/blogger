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
import com.blogger.app.entity.Article;




public class ArticleDaoImp extends CustomHibernateDaoSupport implements ArticleDao{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Article> getArticleList() throws HandlerException {
		logger.info("Getting List of articles!");
		try {
			List articleList = getHibernateTemplate().find("FROM Article ORDER BY id ASC");
			logger.info("articleList.size:"+articleList.size());
	        return articleList;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception :" + e.getMessage());
			throw new HandlerException("000",e.getMessage());
		}
	}
	
	public Article getArticleById(Integer id) throws HandlerException {
		logger.info("Getting an article by id!id:"+id);
        String query="select articleid, articlename, password, email, enabled , expired from articles where articleid=?";
        if (id == null)
        	return null;
        try {
        	List articleList = getHibernateTemplate().find("FROM Article where id = ?", id);
        	logger.info("articleList.size:"+articleList.size());
        	Article article = (Article)articleList.get(0);
            return article;
        }
        catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception :" + e.getMessage());
			throw new HandlerException("000",e.getMessage());
		}
	}
	
	public Article getArticleByName(String name) throws HandlerException {
		Article article = (Article)getHibernateTemplate().find("FROM articles where articleid = ?", name);
        return article;
	}
	
	public void saveArticle(Article article) throws HandlerException {
        logger.info("Saving Article");
        try {
        	Date today = new Date();
			article.setUpdateDate(today);
			getHibernateTemplate().update(article);
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
//        String query = "update articles set articlename=:articlename, password=:password, email=:email, enabled=:enabled, expired=:expired where articleid=:articleid";
//        int count = getSimpleJdbcTemplate().update(query,
//            new MapSqlParameterSource().addValue("articleid", article.getId())
//            	.addValue("articlename", article.getArticlename())
//                .addValue("password", article.getPassword())
//                .addValue("email", article.getEmail())
//                .addValue("enabled", article.isEnabled()) 
//                .addValue("expired", article.isExpired()) );
//        		
//        logger.info("Save Article, Rows affected: " + count);
    }
	
	public void addArticle(Article article) throws HandlerException {
		logger.info("Adding Article");
		try {
			Date today = new Date();
			article.setCreateDate(today);
			getHibernateTemplate().save(article);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception :" + e.getMessage());
			throw new HandlerException("000",e.getMessage());
		}
//
//		
//		logger.info("####ArticleDao " + article.getArticlename());
//		logger.info("####ArticleDao " + article.getEmail());
//		logger.info("####ArticleDao " + article.isEnabled());
//		logger.info("####ArticleDao " + article.getPassword());
//		logger.info("####ArticleDao " + article.isExpired());
//		
//		/*
//		int count = getSimpleJdbcTemplate().update(query,
//	            new MapSqlParameterSource().addValue("articlename", article.getArticlename())
//	                .addValue("password", article.getPassword())
//	            	//.addValue("password", "123456")
//	                .addValue("email", article.getEmail())
//	                .addValue("enabled", article.isEnabled()), article.getPassword() );
//	    */
//		
//		int count = getSimpleJdbcTemplate().update(query, article.getArticlename(), article.getPassword(), article.getEmail(), article.isEnabled(), article.isExpired());
//		
//		if(count == 1)
//		{
//			article.setId(getArticleByName(article.getArticlename()).getId());
//			logger.info("######New article'd Id = " + article.getId());
//			getSimpleJdbcTemplate().update(query2,article.getId());
//		}
		
//	    logger.info("Add Article, Rows affected: " + count);
	}
	
	public void deleteArticle(Integer id) throws HandlerException {
        logger.info("Deleting Article");
        try {
    		getHibernateTemplate().bulkUpdate("delete from Article where id=?",id);
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
//        String query1 = "delete from articles_x_groups where articleid=?";
//        String query2 = "delete from articles where articleid=?";
//        logger.info("#######"+query1 + " id = " + id);
//        try{
//        	getSimpleJdbcTemplate().update(query1,id);
//        	getSimpleJdbcTemplate().update(query2,id);
//        }
//        catch(Exception e){
//        	e.getMessage();
//        }
  
        //logger.info("Delete Article, Rows affected: " + count);
    }
	
	
}
