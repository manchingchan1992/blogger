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
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;



public class authenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(authenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		ApplicationContext context = (ApplicationContext)request.getAttribute("context");		
		
//		AuditLogService auditLogService = (AuditLogService)context.getBean("auditLogService");
//		AuditPropertyBean auditPropertyBean = (AuditPropertyBean)context.getBean("auditPropertyBean");
		
		logger.info( "onAuthenticationSuccess ..." );

		try {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date today = new Date();
			String now = sdf.format(today);
//			DateUtil du = new DateUtil();
//			AuditLog log = new AuditLog();
//			log.setName(authentication.getName());
//			log.setAction(auditPropertyBean.getLogin());
//			log.setStatus(auditPropertyBean.getStatus_login_success());
//			log.setActiontime(du.parseTimestamp(now));
//			
//			auditLogService.saveAuditLog(log);
		
		      // auditLogManager.insertValue(authentication.getName(), auditPropertyBean.getLogin(), auditPropertyBean.getStatus_login_success(), now);
		} catch (Exception e) {			
		}
		      
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
