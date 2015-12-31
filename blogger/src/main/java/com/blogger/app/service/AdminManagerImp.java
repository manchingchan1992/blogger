package com.blogger.app.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blogger.app.dao.AdminDao;



public class AdminManagerImp implements AdminManager{
	
	@Autowired
	private AdminDao adminDao;

	@Transactional (readOnly = true)
	public List getMenuList(String postCode) {
		return adminDao.getMenuList(postCode);
	}
}