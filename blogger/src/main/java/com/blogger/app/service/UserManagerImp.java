package com.blogger.app.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.blogger.app.entity.User;
import com.blogger.app.dao.UserDao;



public class UserManagerImp implements UserManager{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
    
    public List<User> getUserList(){
    	return userDao.getUserList();
    }
    
    public User getUserByName(String username){
    	return userDao.getUserByName(username);
    }
    
    public User getUserById(String userid){
    	return userDao.getUserById(userid);
    }
    
    public void createUser(User newuser){
//    	if(newuser != null){
//    		logger.info("#####UserManager: " + newuser.getId());
//    		logger.info("#####UserManager: " + newuser.getUsername());
//    		logger.info("#####UserManager: " + newuser.getEmail());
//    		logger.info("#####UserManager: " + newuser.getPassword());
//    		userDao.addUser(newuser);
//    	}
    }
    
    public void updateUser(User user){
    	if(user != null){
    		userDao.saveUser(user);
    	}
    }
    
    public void deleteUser(String id){
    	userDao.deleteUser(id);
    }
    
}
