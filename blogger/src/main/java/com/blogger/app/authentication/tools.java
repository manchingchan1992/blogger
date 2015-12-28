package com.hkt.authentication;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hkt.bean.AuditPropertyBean;
import com.hkt.entity.AuditLog;
import com.hkt.entity.PasswordHistory;
import com.hkt.entity.User;
import com.hkt.service.AuditLogService;
import com.hkt.service.PasswordHistoryManager;
import com.hkt.service.UserManager;


public class tools {

	public static void lockAccount(String username, UserManager userManager, AuditLogService auditLogService, AuditPropertyBean auditPropertyBean, int numberOfAuthTrials) {
		
		//List<AuditLog> list = auditLogManager.searchValue("", username, "", "", "actiontime", "desc");
		
		List<AuditLog> list = auditLogService.searchAuditLogByUsername(username);
		boolean locked[] = new boolean[numberOfAuthTrials-1];
		int index = 0;
		
		for (int i=0; i<list.size(); i++) {
			AuditLog entry = list.get(i);

			if ( index >=locked.length )
				break;
			
			if ( ( entry.getAction().equals(auditPropertyBean.getLogin()) ||
				   entry.getAction().equals(auditPropertyBean.getChange_password()) )
					&&  
			     !entry.getStatus().equals(auditPropertyBean.getStatus_system_busy()) ) {
				if ( entry.getStatus().equals(auditPropertyBean.getStatus_incorrect_password()) )
					locked[index] = true;
				else 
					locked[index] = false;
				index++;
			}

			System.out.println( "ENTRY: " + entry.getAction() + ", " + entry.getActiontime() + ", " + 
					            entry.getId() + ", " + entry.getName() + ", " + entry.getStatus() ); 
		}

		for (int i=0; i<locked.length; i++) {
			System.out.println("LOCK: " + locked[i]);
		}
		
		for (int i=0; i<locked.length; i++) {
			if (!locked[i])
				return;
		}

		User u = userManager.getUserByName(username);		
		u.setEnabled(false);
		
		userManager.updateUser(u);		
	}
	
	public static boolean isPasswordExpired(String username, UserManager userManager, PasswordHistoryManager passwordHistoryManager, AuditPropertyBean auditPropertyBean, int numberOfDaysForChangePassword) {

		User u = userManager.getUserByName(username);		

		if (u.isExpired()) 
			return true;
		
		String last_changepassword_time = null ;
		List<PasswordHistory> list = passwordHistoryManager.getPasswordHistoryByUserid(Integer.toString(u.getId()));
		if(list.size()>0){
			for (int i=0; i<1; i++) {
				PasswordHistory entry = list.get(i);
				System.out.print("######last password modification date = " + entry.getTimestamp());
				if ( !entry.getTimestamp().isEmpty()) {
					last_changepassword_time = entry.getTimestamp();	
					break;
				}
			}
		}
		
		/*
		for (int i=0; i<list.size(); i++) {
			AuditLog entry = list.get(i);
			
			if ( entry.getAction().equals(auditPropertyBean.getChange_password()) && 
				 entry.getStatus().equals(auditPropertyBean.getStatus_success() ) ) {
				last_changepassword_time = entry.getActiontime();	
				break;
			}
		}
		*/
		if (last_changepassword_time==null)
			return true;
		
		System.out.println( "LAST: " + last_changepassword_time );
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		java.util.Date d = new java.util.Date(); 
		
		try {
				d = formatter.parse(last_changepassword_time);
		} catch (Exception e) {			
		}

		System.out.println("DATE: " + d);

		long interval = new java.util.Date().getTime() - d.getTime();
		
		if ( interval > numberOfDaysForChangePassword * 86400000L  )
			return true;
			
		return false;
	}

}
