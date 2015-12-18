package com.blogger.app.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.blogger.app.entity.Menu;
import com.blogger.app.entity.User;
import com.blogger.app.service.AdminManager;
import com.blogger.app.service.UserManager;
import com.blogger.app.util.MenuBuilder;



public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		// TODO Auto-generated method stub

		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		request.setAttribute("context", context);
		
		logger.info("username: " + request.getParameter("username"));
		logger.info("password: " + request.getParameter("password"));

		if ( request.getParameter("username")==null || 
			 request.getParameter("password")==null ) {
			throw new inputValidationException( context.getMessage("error.missing_information", new String[0], Locale.US) );
		}

		if ( request.getParameter("username").equals("") ) {
			throw new inputValidationException( context.getMessage("error.missing_username", new String[0], Locale.US) );
		}

		if ( request.getParameter("password").equals("") ) {
			throw new inputValidationException( context.getMessage("error.missing_password", new String[0], Locale.US) );
		}

//		UserManager userManager = (UserManager)context.getBean("userManager");
//		PasswordHistoryManager passwordHistoryManager = (PasswordHistoryManager)context.getBean("passwordHistoryManager");  		
//		AuditLogService auditLogService = (AuditLogService)context.getBean("auditLogService");  
//		AuditPropertyBean auditPropertyBean = (AuditPropertyBean)context.getBean("auditPropertyBean");
//		GlobalParameters globalParameters = (GlobalParameters)context.getBean("globalParameters");
				
		Authentication res = null; 
			
		try {
				res = super.attemptAuthentication(request, response);
		} catch (BadCredentialsException e) {
				throw new BadCredentialsException( context.getMessage("error.bad_credential", new String[] {}, Locale.US)); 
		} catch (DisabledException e) {
				throw new DisabledException( context.getMessage("error.account_disabled", new String[] {}, Locale.US)); 
		}
		UserManager userManager = (UserManager)context.getBean("userManager");
		User user = userManager.getUserByName(request.getParameter("username"));
	    HttpSession session = request.getSession();		

	    session.setAttribute("USERBEAN",user);
		AdminManager adminManager = (AdminManager)context.getBean("adminManager");
		if (user != null){
			List menuList = adminManager.getMenuList(user.getPostCode());
			logger.info("menuList.size() :" + menuList.size());
			ArrayList<Menu> menuTree = MenuBuilder.buildTree(menuList);
			session.setAttribute("MenuList",menuTree);
		}
//		boolean isPasswordExpired = tools.isPasswordExpired(request.getParameter("username"), userManager, passwordHistoryManager, auditPropertyBean, globalParameters.getNumberOfDaysForChangePassword());

//		System.out.println( "isPasswordExpired: " + isPasswordExpired );
//		
//		if ( isPasswordExpired ) {
//			try {
//				response.sendRedirect("changepassword");
//			} catch (IOException e) {
//			}
//			
//			return null;
//		}
		
		return res; 		
	}
}
