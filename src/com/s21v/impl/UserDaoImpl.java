package com.s21v.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.s21v.dao.UserDao;
import com.s21v.vo.User;

public class UserDaoImpl implements UserDao {
	private Connection con;
	
	public UserDaoImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public int add(User user) throws Exception{
		String sql = "insert into user (name,password,phoneNum) values (?,?,?);";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, user.getName());
		stat.setString(2, user.getPassword());
		stat.setString(3, user.getPhoneNum());
		int update = stat.executeUpdate();
		return update;
	}

	@Override
	public int add(ArrayList<User> users) throws Exception {
		int result = 0;
		for(User user : users)
			result += add(user);
		return result;
	}

	@Override
	public ArrayList<User> findALL() throws Exception {
		String sql = "select * from user;";
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		ArrayList<User> result = new ArrayList<>();
		while(resultSet.next()) {
			User user = new User(resultSet.getInt(resultSet.findColumn("id")),
					resultSet.getString(resultSet.findColumn("name")),
					resultSet.getString(resultSet.findColumn("password")),
					resultSet.getString(resultSet.findColumn("phoneNum")),
					resultSet.getString(resultSet.findColumn("platformQQId")),
					resultSet.getString(resultSet.findColumn("platformWeChatId")),
					resultSet.getString(resultSet.findColumn("platformSinaWeiboId")));
			result.add(user);
		}
		return result;
	}

	@Override
	public boolean deleteById(int id) throws Exception {
		String sql = "delete from user where id="+id+";";
		Statement statement = con.createStatement();
		int result = statement.executeUpdate(sql);
		return result > 0;
	}

	@Override
	public boolean updateByid(String username, String password, int id) throws Exception {
		String sql = "update user set name=\""+username+"\",password=\""+password+"\" where id="+id+";";
		Statement statement = con.createStatement();
		int result = statement.executeUpdate(sql);
		return result > 0;
	}

	@Override
	public User findUser(String phoneNum, String password) throws Exception {
		String sql = "select * from user where phoneNum=? and password=?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, phoneNum);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			User result = new User();
			result.setName(resultSet.getString("name"));
			result.setPassword(resultSet.getString("password"));
			result.setPhoneNum(resultSet.getString("phoneNum"));
			return result;
		}
		return null;
	}

	@Override
	public User findUserByName4Platform(String platformId, String platformName) throws Exception {
		String sql = null;
		switch (platformName) {
		case "QQ":
			sql = "select * from user where platformQQId=?";
			break;
		case "WeChat" :
			sql = "select * from user where platformWeChatId=?";
			break;
		case "SinaWeibo" :
			sql = "select * from user where platformSinaWeiboId=?";
			break;
		default:
			break;
		}
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, platformId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			User user = new User();
			user.setName(resultSet.getString("name"));
			user.setPassword(resultSet.getString("password"));
			user.setPhoneNum(resultSet.getString("phoneNum"));
			return user;
		}
		return null;
	}

	@Override
	public User add(String phoneNum, String password) throws Exception {
		User user = new User("手机用户"+Math.abs(phoneNum.hashCode()), password, phoneNum);
		String sql = "insert into user (name, phoneNum, password) values (?,?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getPhoneNum());
		statement.setString(3, user.getPassword());
		if (statement.executeUpdate() > 0)
			return user;
		else 
			return null;
	}

	@Override
	public User add(String phoneNum, String password, String platformName, String platformId) throws Exception {
		User user = new User("手机用户"+Math.abs(phoneNum.hashCode()), password, phoneNum);
		String sql = null;
		switch (platformName) {
		case "QQ":
			sql = "insert into user (name, phoneNum, password, platformQQId) values (?,?,?,?)";
			user.setPlatformQQId(platformId);
			break;
		case "WeChat":
			sql = "insert into user (name, phoneNum, password, platformWeChatId) values (?,?,?,?)";
			user.setPlatformWeChatId(platformId);
			break;
		case "SinaWeibo":
			sql = "insert into user (name, phoneNum, password, platformSinaWeiboId) values (?,?,?,?)";
			user.setPlatformSinaWeiboId(platformId);
			break;
		default:
			break;
		}
		if(sql != null) {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getPhoneNum());
			statement.setString(3, user.getPassword());
			statement.setString(4, platformId);
			if(statement.executeUpdate() > 0)
				return user;
		}	
		return null;
	}

	@Override
	public int updateQQByPhoneNum(String phoneNum, String platformQQId) throws Exception {
		String sql = "update user set platformQQId = ? where phoneNum = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, platformQQId);
		preparedStatement.setString(2, phoneNum);
		return preparedStatement.executeUpdate();
	}

	@Override
	public int updateWeChatByPhoneNum(String phoneNum, String platformWeChatId) throws Exception {
		String sql = "update user set platformWeChatId = ? where phoneNum = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, platformWeChatId);
		preparedStatement.setString(2, phoneNum);
		return preparedStatement.executeUpdate();
	}

	@Override
	public int updateSinaWeiboByPhoneNum(String phoneNum, String platformSinaWeiboId) throws Exception {
		String sql = "update user set platformSinaWeiboId = ? where phoneNum = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, platformSinaWeiboId);
		preparedStatement.setString(2, phoneNum);
		return preparedStatement.executeUpdate();
	}

	@Override
	public int updatePasswordByPhoneNum(String phoneNum, String password) throws Exception {
		String sql = "update user set password = ? where phoneNum = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, phoneNum);
		return preparedStatement.executeUpdate();
	}	
	
	
}
