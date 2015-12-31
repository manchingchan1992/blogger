package com.blogger.app.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blogger.app.entity.User;
import com.blogger.app.dao.UserDao;



public class UserManagerImp implements UserManager{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private UserDao userDao;

    @Transactional (readOnly = true)
    public List<User> getUserList(){
    	return userDao.getUserList();
    }
    
    @Transactional (readOnly = true)
    public User getUserByName(String username){
    	return userDao.getUserByName(username);
    }
    
    @Transactional (readOnly = true)
    public User getUserById(String userid){
    	return userDao.getUserById(userid);
    }
    @Transactional(readOnly = false)
    public void createUser(User newuser){
//    	if(newuser != null){
//    		logger.info("#####UserManager: " + newuser.getId());
//    		logger.info("#####UserManager: " + newuser.getUsername());
//    		logger.info("#####UserManager: " + newuser.getEmail());
//    		logger.info("#####UserManager: " + newuser.getPassword());
//    		userDao.addUser(newuser);
//    	}
    }
    @Transactional(readOnly = false)
    public void updateUser(User user){
    	if(user != null){
    		userDao.saveUser(user);
    	}
    }
    @Transactional(readOnly = false)
    public void deleteUser(String id){
    	userDao.deleteUser(id);
    }
    
}
