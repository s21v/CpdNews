package com.s21v.dao;

import java.util.ArrayList;

import com.s21v.vo.User;

public interface UserDao {
	int add(User user) throws Exception;	//�����û�
	int add(ArrayList<User> users) throws Exception;
	
	ArrayList<User> findALL() throws Exception ;	//�����û�
	
	boolean deleteById(int id) throws Exception ;	//ɾ���û�
	
	boolean updateByid(String username, String password, int id) throws Exception ;	//�����û�
	
	User findUser(String phoneNum, String password) throws Exception ;
	
	//��ѯ��������¼��id�Ƿ������ݿ���,���ھͷ����û�
	User findUserByName4Platform(String platformId, String platformName) throws Exception;
	
	User add(String phoneNum, String password) throws Exception;
	
	//�ֻ��š����롢ƽ̨��Ϣ ������û�
	User add(String phoneNum, String password, String platformName, String platformId) throws Exception;
	
	//����ָ���ֻ��ŵ��û��ĵ�����ƽ̨��Ϣ
	int updateQQByPhoneNum(String phoneNum, String platformQQId) throws Exception;
	
	int updateWeChatByPhoneNum(String phoneNum, String platformWeChatId) throws Exception;
	
	int updateSinaWeiboByPhoneNum(String phoneNum, String platformSinaWeiboId) throws Exception;
	
	int updatePasswordByPhoneNum(String phoneNum, String password) throws Exception;
	
}
