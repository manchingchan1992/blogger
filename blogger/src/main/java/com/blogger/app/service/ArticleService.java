package com.blogger.app.service;

import java.io.Serializable;
import java.util.List;

import com.blogger.app.entity.Article;
import com.blogger.app.util.HandlerException;



public interface ArticleService extends Serializable{

	public void saveArticle(Article article) throws HandlerException;
	
	public void deleteArticle(Integer id) throws HandlerException;
	
	public List<Article> getArticleList() throws HandlerException;
	
	public Article getArticleByName(String articlename)throws HandlerException ;
	
	public Article getArticleById(Integer articleid)throws HandlerException;
}
