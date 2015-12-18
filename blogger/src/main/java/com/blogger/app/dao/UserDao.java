package com.blogger.app.dao;

import java.util.List;

import com.blogger.app.entity.User;




public interface UserDao {

	public List<User> getUserList();
	
	public User getUserByName(String username);
	
	public User getUserById(String id);
	
	public void saveUser(User user);
	
	public void addUser(User user);
	
	public void deleteUser(String id);
}
