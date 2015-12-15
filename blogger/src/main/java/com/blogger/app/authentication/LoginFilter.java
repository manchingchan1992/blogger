package com.blogger.app.authentication;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		// TODO Auto-generated method stub

		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		request.setAttribute("context", context);
		
		logger.info("j_username: " + request.getParameter("j_username"));
		logger.info("j_password: " + request.getParameter("j_password"));

		if ( request.getParameter("j_username")==null || 
			 request.getParameter("j_password")==null ) {
			throw new inputValidationException( context.getMessage("error.missing_information", new String[0], Locale.US) );
		}

		if ( request.getParameter("j_username").equals("") ) {
			throw new inputValidationException( context.getMessage("error.missing_username", new String[0], Locale.US) );
		}

		if ( request.getParameter("j_password").equals("") ) {
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

//		boolean isPasswordExpired = tools.isPasswordExpired(request.getParameter("j_username"), userManager, passwordHistoryManager, auditPropertyBean, globalParameters.getNumberOfDaysForChangePassword());

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
