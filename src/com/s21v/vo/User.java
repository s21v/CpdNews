package com.s21v.vo;

public class User {
	private int id;
	private String name;	//�ǳ�
	private String password;	//����
	private String phoneNum;	//�ֻ���
	private String platformQQId;	//������ƽ̨ QQ ID
	private String platformWeChatId;	//΢��ID
	private String platformSinaWeiboId;	//΢��ID
	
	public User() {
		super();
	}	

	public User(int id, String name, String password, String phoneNum, String platformQQId, String platformWeChatId,
			String platformSinaWeiboId) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phoneNum = phoneNum;
		this.platformQQId = platformQQId;
		this.platformWeChatId = platformWeChatId;
		this.platformSinaWeiboId = platformSinaWeiboId;
	}

	public User(int id, String name, String password, String phoneNum) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phoneNum = phoneNum;
	}

	
	public User(String name, String password, String phoneNum) {
		super();
		this.name = name;
		this.password = password;
		this.phoneNum = phoneNum;
	}

	public int getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPlatformQQId() {
		return platformQQId;
	}

	public void setPlatformQQId(String platformQQId) {
		this.platformQQId = platformQQId;
	}

	public String getPlatformWeChatId() {
		return platformWeChatId;
	}

	public void setPlatformWeChatId(String platformWeChatId) {
		this.platformWeChatId = platformWeChatId;
	}

	public String getPlatformSinaWeiboId() {
		return platformSinaWeiboId;
	}

	public void setPlatformSinaWeiboId(String platformSinaWeiboId) {
		this.platformSinaWeiboId = platformSinaWeiboId;
	}
	
}
