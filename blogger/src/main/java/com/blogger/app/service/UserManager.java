package com.blogger.app.service;

import java.io.Serializable;
import java.util.List;

import com.blogger.app.entity.User;



public interface UserManager extends Serializable{

	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(String id);
	
	public List<User> getUserList();
	
	public User getUserByName(String username);
	
	public User getUserById(String userid);
}
