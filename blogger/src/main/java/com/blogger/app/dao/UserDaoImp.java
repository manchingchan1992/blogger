package com.blogger.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import java.security.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.blogger.app.util.CustomHibernateDaoSupport;
import com.blogger.app.entity.User;




public class UserDaoImp extends CustomHibernateDaoSupport implements UserDao{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<User> getUserList(){
		logger.info("Getting List of users!");
        String query="select userid, username, password, email, enabled , expired from users order by username asc";
        List users = getHibernateTemplate().find("FROM users ORDER BY userid ASC");
        return users;
	}
	
	public User getUserById(String id){
		logger.info("Getting an user by id!");
        String query="select userid, username, password, email, enabled , expired from users where userid=?";
        User user = (User)getHibernateTemplate().find("FROM users where userid = ?", id);
        return user;
	}
	
	public User getUserByName(String name){
		logger.info("Getting an user by name!");
        String query="select * FROM users u left join authorities a on a.userid = u.userid where u.username = ? ";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			User user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("userid"));
				user.setLoginName(rs.getString("username"));
				user.setEnabled(rs.getInt("enabled"));
				user.setPostCode(rs.getString("authority"));
			}
			while (rs.next()) {
				user.setPostCode(user.getPostCode()+","+rs.getString("authority"));
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void saveUser(User user) {
        logger.info("Saving User");
        
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date today = new Date();
        //String now = sdf.format(today);
        //String now = (new java.util.Date()).toString();
//        String query = "update users set username=:username, password=:password, email=:email, enabled=:enabled, expired=:expired where userid=:userid";
//        int count = getSimpleJdbcTemplate().update(query,
//            new MapSqlParameterSource().addValue("userid", user.getId())
//            	.addValue("username", user.getUsername())
//                .addValue("password", user.getPassword())
//                .addValue("email", user.getEmail())
//                .addValue("enabled", user.isEnabled()) 
//                .addValue("expired", user.isExpired()) );
//        		
//        logger.info("Save User, Rows affected: " + count);
    }
	
	public void addUser(User user){
		logger.info("Adding User");
		String query = "insert into users(username, password, email, enabled, expired) values(?, md5(?), ?, ?, ?)";
		String query2 = "insert into users_x_groups(userid, groupid) values(?, '1')";
//
//		
//		logger.info("####UserDao " + user.getUsername());
//		logger.info("####UserDao " + user.getEmail());
//		logger.info("####UserDao " + user.isEnabled());
//		logger.info("####UserDao " + user.getPassword());
//		logger.info("####UserDao " + user.isExpired());
//		
//		/*
//		int count = getSimpleJdbcTemplate().update(query,
//	            new MapSqlParameterSource().addValue("username", user.getUsername())
//	                .addValue("password", user.getPassword())
//	            	//.addValue("password", "123456")
//	                .addValue("email", user.getEmail())
//	                .addValue("enabled", user.isEnabled()), user.getPassword() );
//	    */
//		
//		int count = getSimpleJdbcTemplate().update(query, user.getUsername(), user.getPassword(), user.getEmail(), user.isEnabled(), user.isExpired());
//		
//		if(count == 1)
//		{
//			user.setId(getUserByName(user.getUsername()).getId());
//			logger.info("######New user'd Id = " + user.getId());
//			getSimpleJdbcTemplate().update(query2,user.getId());
//		}
		
//	    logger.info("Add User, Rows affected: " + count);
	}
	
	public void deleteUser(String id) {
        logger.info("Deleting User");
        
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date today = new Date();
        //String now = sdf.format(today);
        //String now = (new java.util.Date()).toString();
//        String query1 = "delete from users_x_groups where userid=?";
//        String query2 = "delete from users where userid=?";
//        logger.info("#######"+query1 + " id = " + id);
//        try{
//        	getSimpleJdbcTemplate().update(query1,id);
//        	getSimpleJdbcTemplate().update(query2,id);
//        }
//        catch(Exception e){
//        	e.getMessage();
//        }
  
        //logger.info("Delete User, Rows affected: " + count);
    }
	
	
}
