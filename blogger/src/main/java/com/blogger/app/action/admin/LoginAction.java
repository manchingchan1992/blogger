package com.blogger.app.action.admin;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginAction {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public String home() {
		return "admin/login";
	}
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String submitLogin() {
		return "admin/home";
	}
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String index() {
		return "admin/home";
	}
}
