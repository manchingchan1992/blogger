package com.blogger.app.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blogger.app.dao.ArticleDao;
import com.blogger.app.entity.Article;



public class ArticleServiceImp implements ArticleService{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private ArticleDao articleDao;

    @Transactional (readOnly = true)
    public List<Article> getArticleList(){
    	return articleDao.getArticleList();
    }
    
    @Transactional (readOnly = true)
    public Article getArticleByName(String articlename){
    	return articleDao.getArticleByName(articlename);
    }
    
    @Transactional (readOnly = true)
    public Article getArticleById(Integer articleid){
    	return articleDao.getArticleById(articleid);
    }
    @Transactional(readOnly = false)
    public void saveArticle(Article article){
    	if(article != null){
    		if (article.isNew()) {
    			articleDao.addArticle(article);
    		} else {
    			articleDao.saveArticle(article);
    		}
    	}
    }
    @Transactional(readOnly = false)
    public void deleteArticle(Integer id){
    	articleDao.deleteArticle(id);
    }
    
}
