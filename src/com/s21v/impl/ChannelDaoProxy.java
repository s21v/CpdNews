package com.s21v.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import com.s21v.dao.ChannelDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.Channel;

public class ChannelDaoProxy implements ChannelDao{
	private DataBaseConnection dbc;
	private ChannelDaoImpl channelDaoImpl;

	public ChannelDaoProxy() {
		try {
			dbc = new DataBaseConnection();
			channelDaoImpl = new ChannelDaoImpl(dbc.getConn());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeDbconnection() {
		if(dbc != null)
			dbc.close();
	}
	
	@Override
	public ArrayList<Channel> searchAllChannel() throws Exception {
		return channelDaoImpl.searchAllChannel();
	}
	
}
