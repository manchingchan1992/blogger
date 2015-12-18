package com.blogger.app.service;

import java.io.Serializable;
import java.util.List;





public interface AdminManager extends Serializable{
	public List getMenuList(String postCode);
}