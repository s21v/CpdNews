package com.s21v.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.s21v.dao.ChannelDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.Channel;

public class ChannelDaoImpl implements ChannelDao {
	private Connection con;
	
	public ChannelDaoImpl(Connection con) {
		this.con = con;
	}
	
	public static void main(String[] args) {
		try {
			DataBaseConnection DBConn = new DataBaseConnection();
			ChannelDaoImpl dao = new ChannelDaoImpl(DBConn.getConn());
			ArrayList<Channel> result = dao.searchAllChannel();
			Gson gson = new Gson();
			System.out.println(gson.toJson(result));
			DBConn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Channel> searchAllChannel() throws SQLException {
		String sql = "select * from news_channel order by id asc";
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		ArrayList<Channel> result = new ArrayList<Channel>();
		while(resultSet.next()){
			result.add(new Channel(resultSet.getString(resultSet.findColumn("id")), resultSet.getString(resultSet.findColumn("name"))));
		}
		return result;
	}

}
