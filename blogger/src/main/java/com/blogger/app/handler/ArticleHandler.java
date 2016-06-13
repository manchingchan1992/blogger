package com.blogger.app.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.app.entity.Article;
import com.blogger.app.service.ArticleService;
import com.blogger.app.util.UrlRouteMapping;

@RestController
public class ArticleHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleHandler.class);
	@Autowired
	private ArticleService articleService;
	
	// list page
		@RequestMapping(value = UrlRouteMapping.ARTICLE_HANDLER_LIST_ACTION)
		public @ResponseBody List<Article> showAllCategory() {
			List<Article> categoryList = articleService.getArticleList();
			return categoryList;
	    }
}