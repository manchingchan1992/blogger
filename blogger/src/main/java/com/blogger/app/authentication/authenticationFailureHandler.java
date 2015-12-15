package com.blogger.app.authentication;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;



public class authenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(authenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		logger.info("onAuthenticationFailure ..."); 

		
		ApplicationContext context = (ApplicationContext)request.getAttribute("context"); 
		
//		UserManager userManager = (UserManager)context.getBean("userManager");
//		AuditLogService auditLogService = (AuditLogService)context.getBean("auditLogService");
//		AuditPropertyBean auditPropertyBean = (AuditPropertyBean)context.getBean("auditPropertyBean");
//		GlobalParameters globalParameters = (GlobalParameters)context.getBean("globalParameters");
		
		String status = null;
				
//		if ( exception instanceof BadCredentialsException ) {
//
//				status = auditPropertyBean.getStatus_incorrect_password();
//				
//				tools.lockAccount( request.getParameter("j_username"), 
//								userManager, auditLogService, auditPropertyBean, globalParameters.getNumberOfAuthTrials() );
//		}
//		
//		if ( exception instanceof DisabledException ) {
//			status = auditPropertyBean.getStatus_account_locked();
//		}

		if ( status!=null ) {
			try {
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date today = new Date();
				String now = sdf.format(today);
//				DateUtil du = new DateUtil();
//				AuditLog log = new AuditLog();
//				log.setName(request.getParameter("j_username"));
//				log.setAction(auditPropertyBean.getLogin());
//				log.setStatus(status);
//				log.setActiontime(du.parseTimestamp(now));
//			
//				auditLogService.saveAuditLog(log);
			      //auditLogManager.insertValue( request.getParameter("j_username"), auditPropertyBean.getLogin(), status, now);
			} catch (Exception e) {			
			}			
		}

		super.onAuthenticationFailure(request, response, exception);
	}

}
