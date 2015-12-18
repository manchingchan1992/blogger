package com.blogger.app.service;

import java.io.Serializable;
import java.util.List;

import com.blogger.app.dao.AdminDao;



public class AdminManagerImp implements AdminManager{
	
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public List getMenuList(String postCode) {
		return adminDao.getMenuList(postCode);
	}
}