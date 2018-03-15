package com.s21v.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mysql.jdbc.Connection;
import com.s21v.dao.video.ChannelDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.video.Channel;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class VideoChannelDaoImpl implements ChannelDao{
	private Connection conn;
	
	public VideoChannelDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public static void main(String[] args) {
		DataBaseConnection conn = null;
		try {
		    conn = new DataBaseConnection();
			VideoChannelDaoImpl channelDaoImpl = new VideoChannelDaoImpl(conn.getConn());
			ArrayList<Channel> channels = channelDaoImpl.getVideoChannel();
			Gson gson = new Gson();
			String jsonStr = gson.toJson(channels);
			System.out.println(jsonStr);
			ArrayList<Channel> channels2 = channelDaoImpl.getVideoSubject();
			String jsonStr2 = gson.toJson(channels2);
			System.out.println(jsonStr2);
			Channel subject = channelDaoImpl.getLastSubject();
			String jsonStr3 = gson.toJson(subject);
			System.out.println(jsonStr3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}

	}

	@Override
	public ArrayList<Channel> getVideoChannel() {
		String sql = "select * from video_channel where id not in (select distinct parent_channel_id from video_channel where parent_channel_id is not null) "
				+ "and (subject_img_url is null)";
		ArrayList<Channel> result = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			result = new ArrayList<Channel>();
			while (resultSet.next()) {
				Channel channel = new Channel(resultSet.getInt(resultSet.findColumn("id")),
						resultSet.getString(resultSet.findColumn("name")),
						resultSet.getString(resultSet.findColumn("url")));
				channel.setParentChannelId(resultSet.getInt(resultSet.findColumn("parent_channel_id")));
				result.add(channel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Channel> getVideoSubject() {
		String sql = "select * from video_channel where subject_img_url is not null";
		ArrayList<Channel> result = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			result = new ArrayList<>();
			while (resultSet.next()) {
				Channel channel = new Channel(resultSet.getInt(resultSet.findColumn("id")),
						resultSet.getString(resultSet.findColumn("name")),
						resultSet.getString(resultSet.findColumn("url")));
				channel.setParentChannelId(resultSet.getInt(resultSet.findColumn("parent_channel_id")));
				channel.setSubject_img_url(resultSet.getString(resultSet.findColumn("subject_img_url")));
				result.add(channel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Channel getLastSubject() {
		String sql = "select * from video_channel order by id desc limit 1";
		Channel result = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				result = new Channel(resultSet.getInt(resultSet.findColumn("id")),
						resultSet.getString(resultSet.findColumn("name")),
						resultSet.getString(resultSet.findColumn("url")));
				result.setParentChannelId(resultSet.getInt(resultSet.findColumn("parent_channel_id")));
				result.setSubject_img_url(resultSet.getString(resultSet.findColumn("subject_img_url")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
