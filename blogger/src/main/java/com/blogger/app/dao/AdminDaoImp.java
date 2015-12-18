package com.blogger.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.security.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.blogger.app.util.CustomHibernateDaoSupport;
import com.blogger.app.entity.User;




public class AdminDaoImp extends CustomHibernateDaoSupport implements AdminDao{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
	
	public List getMenuList(String postCode) {
		// TODO Auto-generated method stub
		logger.info("Getting List of Menu!");
        List menuList = getHibernateTemplate().find(" FROM Menu WHERE security_code like ? and status = 'A' ORDER BY menu_head, sort_order", "%"+postCode+"%");
        return menuList;
	}
	
}
