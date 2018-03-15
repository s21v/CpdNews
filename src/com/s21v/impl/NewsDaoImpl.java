package com.s21v.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.Connection;
import com.s21v.dao.NewsDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.News;

public class NewsDaoImpl implements NewsDao{
	private Connection con;

	public NewsDaoImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public ArrayList<News> searchNews(String channel_id, int limitNum, int offset) throws Exception {
		String sql = "select * from news where channel_id=? order by pub_time desc limit ? offset ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, channel_id);
		statement.setInt(2, limitNum);
		statement.setInt(3, offset);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			ArrayList<News> result = new ArrayList<>();
			do {
				News news = new News();
				news.setNews_id(resultSet.getString(resultSet.findColumn("news_id")));
				news.setChannel_id(resultSet.getString(resultSet.findColumn("channel_id")));
				news.setHomePageTitle(resultSet.getString(resultSet.findColumn("homepage_title")));
				news.setContentPageTitle(resultSet.getString(resultSet.findColumn("contentPage_title")));
				news.setPub_time(resultSet.getString(resultSet.findColumn("pub_time")));
				news.setSource(resultSet.getString(resultSet.findColumn("source")));
				news.setAuthor(resultSet.getString(resultSet.findColumn("author")));
				news.setPoster(resultSet.getString(resultSet.findColumn("postter")));
				news.setContent(resultSet.getString(resultSet.findColumn("content")));
				news.setUrl(resultSet.getString(resultSet.findColumn("url")));
				news.setPicUrls(resultSet.getString(resultSet.findColumn("pics")));
				result.add(news);
			} while(resultSet.next());
			return result;
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			DataBaseConnection DBConn = new DataBaseConnection();
			NewsDaoImpl dao = new NewsDaoImpl(DBConn.getConn());
			Gson gson = new Gson();
			ArrayList<News> result1 = dao.searchNews("10216144", 5, 0);
			ArrayList<News> fromGson1 = gson.fromJson(gson.toJson(result1), new TypeToken<ArrayList<News>>(){}.getType());
			for(News item : fromGson1) {
				System.out.println(item);
				System.out.println(item.getPicUrls());
			}
			DBConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
