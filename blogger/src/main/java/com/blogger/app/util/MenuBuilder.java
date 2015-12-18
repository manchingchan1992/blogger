package com.blogger.app.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.blogger.app.entity.Menu;


public class MenuBuilder {
	
	protected static Logger logger = Logger.getLogger("MenuBuilder");
	
	public static ArrayList buildTree(List menuList) {
		ArrayList<Menu> tree = new ArrayList();
		
		if( menuList.size() == 0) {
			return null;
		}
		
		for (int i=0; i<menuList.size(); i++) {
			Menu menu = (Menu)menuList.get(i);
			logger.info("i :" + i + "menu.toString() :" + menu.toString());
			if ( menu.getMenuHead() == null || menu.getMenuHead().compareTo("") == 0 ) {
				tree.add(menu);
			} else {
				for (int j=0; j<tree.size(); j++ ) {
					addMenuItem(tree.get(j), menu);	
				}
			}
		}
		
		return tree;
	}
	
	public static void addMenuItem(Menu menuItem, Menu newItem) {
		logger.info("inside addMenuItem");
		if ( menuItem.getMenuId().compareTo(newItem.getMenuHead()) == 0 ) {
			logger.info("menu id = menu head");
			menuItem.addSubmenu(newItem);
			return;
		}
		
		for (int i=0; i<menuItem.getSubmenu().size(); i++) {
			Menu subMenu = menuItem.getSubmenu().get(i);
			addMenuItem(subMenu, newItem);
		}
	}


}
