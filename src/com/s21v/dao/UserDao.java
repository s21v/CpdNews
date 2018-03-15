package com.s21v.dao;

import java.util.ArrayList;

import com.s21v.vo.User;

public interface UserDao {
	int add(User user) throws Exception;	//曾加用户
	int add(ArrayList<User> users) throws Exception;
	
	ArrayList<User> findALL() throws Exception ;	//查找用户
	
	boolean deleteById(int id) throws Exception ;	//删除用户
	
	boolean updateByid(String username, String password, int id) throws Exception ;	//更改用户
	
	User findUser(String phoneNum, String password) throws Exception ;
	
	//查询第三方登录的id是否在数据库中,存在就返回用户
	User findUserByName4Platform(String platformId, String platformName) throws Exception;
	
	User add(String phoneNum, String password) throws Exception;
	
	//手机号、密码、平台信息 来添加用户
	User add(String phoneNum, String password, String platformName, String platformId) throws Exception;
	
	//更新指定手机号的用户的第三方平台信息
	int updateQQByPhoneNum(String phoneNum, String platformQQId) throws Exception;
	
	int updateWeChatByPhoneNum(String phoneNum, String platformWeChatId) throws Exception;
	
	int updateSinaWeiboByPhoneNum(String phoneNum, String platformSinaWeiboId) throws Exception;
	
	int updatePasswordByPhoneNum(String phoneNum, String password) throws Exception;
	
}
