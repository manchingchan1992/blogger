package com.blogger.app.dao;

import java.util.List;

import com.blogger.app.entity.Article;
import com.blogger.app.util.HandlerException;




public interface ArticleDao {

	public List<Article> getArticleList() throws HandlerException;
	
	public Article getArticleByName(String articlename) throws HandlerException;
	
	public Article getArticleById(Integer id) throws HandlerException;
	
	public void saveArticle(Article article) throws HandlerException;
	
	public void addArticle(Article article) throws HandlerException;
	
	public void deleteArticle(Integer id) throws HandlerException;
}
