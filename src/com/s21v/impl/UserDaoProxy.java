package com.s21v.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import com.s21v.dao.UserDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.User;
import com.sun.xml.internal.ws.Closeable;

public class UserDaoProxy implements UserDao {
	private DataBaseConnection dbc;
	private UserDaoImpl userDaoImpl;
	
	
	public UserDaoProxy() {
		super();
		try {
			dbc = new DataBaseConnection();
			userDaoImpl = new UserDaoImpl(dbc.getConn());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDbconnection() {
		if(dbc != null)
			dbc.close();
	}
	
	@Override
	public int add(User user) throws Exception {
		int i = userDaoImpl.add(user);
		return i;
	}

	@Override
	public int add(ArrayList<User> users) throws Exception {
		int i = userDaoImpl.add(users);
		return i;
	}

	@Override
	public ArrayList<User> findALL() throws Exception {
		ArrayList<User> iArrayList = userDaoImpl.findALL();
		return iArrayList;
	}

	@Override
	public boolean deleteById(int id) throws Exception {
		boolean result = userDaoImpl.deleteById(id);
		return result;
	}

	@Override
	public boolean updateByid(String username, String password, int id) throws Exception {
		boolean result = userDaoImpl.updateByid(username, password, id);
		return result;
	}

	@Override
	public User findUser(String phoneNum, String password) throws Exception {
		User result = userDaoImpl.findUser(phoneNum, password);
		return result;
	}

	@Override
	public User findUserByName4Platform(String platformId, String platformName) throws Exception {
		User result = userDaoImpl.findUserByName4Platform(platformId, platformName);
		return result;
	}

	@Override
	public User add(String phoneNum, String password) throws Exception {
		User result = userDaoImpl.add(phoneNum, password);
		return result;
	}

	@Override
	public User add(String phoneNum, String password, String platformName, String platformId) throws Exception {
		User result = userDaoImpl.add(phoneNum, password, platformName, platformId);
		return result;
	}

	@Override
	public int updateQQByPhoneNum(String phoneNum, String platformQQId) throws Exception {
		int result = userDaoImpl.updateQQByPhoneNum(phoneNum, platformQQId);
		return result;
	}

	@Override
	public int updateWeChatByPhoneNum(String phoneNum, String platformWeChatId) throws Exception {
		int result = userDaoImpl.updateWeChatByPhoneNum(phoneNum, platformWeChatId);
		return result;
	}

	@Override
	public int updateSinaWeiboByPhoneNum(String phoneNum, String platformSinaWeiboId) throws Exception {
		int result = userDaoImpl.updateSinaWeiboByPhoneNum(phoneNum, platformSinaWeiboId);
		return result;
	}

	@Override
	public int updatePasswordByPhoneNum(String phoneNum, String password) throws Exception {
		return userDaoImpl.updatePasswordByPhoneNum(phoneNum, password);
	}

}
