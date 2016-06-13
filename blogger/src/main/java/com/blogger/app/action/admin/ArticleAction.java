package com.blogger.app.action.admin;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;

import com.blogger.app.entity.Article;
import com.blogger.app.entity.Category;
import com.blogger.app.util.MainExceptionHandler;
import com.blogger.app.util.RequestGateway;
import com.blogger.app.util.UrlRouteMapping;

@Controller
public class ArticleAction {
	private static final Logger logger = LoggerFactory.getLogger(ArticleAction.class);

	@Autowired
	private RequestGateway requestGateway;

	@Autowired
	private MainExceptionHandler exceptionHandler;


	// list page
	@RequestMapping(value = UrlRouteMapping.ARTICLE_LIST_ACTION, method = RequestMethod.GET)
	public String showAllArticle(Model model, HttpServletRequest request, HttpEntity<String> requestEntity) {

		try {
			String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.ARTICLE_HANDLER_LIST_ACTION;
			logger.debug("showAllArticle():"+requestURL);
			Article[] articleList  = (Article[]) requestGateway.sendRequest(request, requestEntity, requestURL, Article[].class, null, HttpMethod.GET);
			logger.info("articleList size:"+articleList.length);
			model.addAttribute("articleList", articleList);
		}
		catch (HttpStatusCodeException e) {
			exceptionHandler.handleJsonHandlerError(model, e);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Failed! Error:"+e1.getMessage());
		}
		return UrlRouteMapping.ARTICLE_LIST_URL;

	}

	private void populateDefaultModel(Model model, HttpServletRequest request, HttpEntity<String> requestEntity) {
		try {
			String requestURL = requestGateway.getServerAbsolutePath(request)+UrlRouteMapping.CATEGORYHANDLER_LIST_ACTION;
			logger.debug("populateDefaultModel():"+requestURL);
			Category[] responseList  = (Category[]) requestGateway.sendRequest(request, requestEntity, requestURL, Category[].class, null, HttpMethod.GET);

			//		    	List<Category> categoryList = restTemplate.getForObject(requestURL, List.class);
			logger.info("responseList size:"+responseList.length);
			Map<Integer, String> categoryList = new LinkedHashMap<Integer, String>();
			for (Category category : responseList){
				categoryList.put(category.getId(), category.getName()+" - "+category.getCname());
			}
			model.addAttribute("categoryList", categoryList);
		}
		catch (HttpStatusCodeException e) {
			exceptionHandler.handleJsonHandlerError(model, e);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Failed! Error:"+e1.getMessage());
		}
	}

	// show add user form
	@RequestMapping(value = UrlRouteMapping.ARTICLE_SHOW_FORM_ACTION, method = RequestMethod.GET)
	public String showAddArticleForm(Model model, HttpServletRequest request, HttpEntity<String> requestEntity) {
		logger.debug("showAddArticleForm()");
		Article article = new Article();
		populateDefaultModel(model, request, requestEntity);
		model.addAttribute("articleForm", article);
		return UrlRouteMapping.ARTICLE_FORM_URL;
	}
}
