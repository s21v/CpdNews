package com.s21v.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.s21v.dao.video.NewsDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.video.Channel;
import com.s21v.vo.video.News;

public class VideoNewsDaoImpl implements NewsDao{
	private Connection conn;
	
	public VideoNewsDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public static void main(String[] args) {
		try {
			DataBaseConnection dataBaseConnection = new DataBaseConnection();
			VideoNewsDaoImpl videoNewsDaoImpl = new VideoNewsDaoImpl(dataBaseConnection.getConn());
			ArrayList<News> result = videoNewsDaoImpl.getNotCpdNewsByPage(2, 1);
			Gson gson = new Gson();
			System.out.println(gson.toJson(result));
			System.out.println(result.size());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<News> getLastNews4Channel(int channelId, int count) {
		String sql = "select * from video_news where channel_id = ? order by publish_time desc limit ?";
		ArrayList<News> result = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, channelId);
			preparedStatement.setInt(2, count);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = new ArrayList<>();
				do {
					News news = new News(resultSet.getInt(resultSet.findColumn("id")),
							resultSet.getString(resultSet.findColumn("title")),
							resultSet.getString(resultSet.findColumn("news_url")),
							resultSet.getString(resultSet.findColumn("thumbicon_url")),
							resultSet.getString(resultSet.findColumn("video_url")),
							resultSet.getInt(resultSet.findColumn("channel_id")));
					news.setSource(resultSet.getString(resultSet.findColumn("source")));
					news.setContent(resultSet.getString(resultSet.findColumn("content")));
					news.setAuthor(resultSet.getString(resultSet.findColumn("author")));
					news.setPublishTime(resultSet.getString(resultSet.findColumn("publish_time")));
					result.add(news);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<News> getNewsByPage(int channelId, int count, int offset) {
		String sql = "select * from video_news where channel_id = ? order by publish_time desc limit ? offset ?";
		ArrayList<News> result = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, channelId);
			preparedStatement.setInt(2, count);
			preparedStatement.setInt(3, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = new ArrayList<>();
				do {
					News news = new News(resultSet.getInt(resultSet.findColumn("id")),
							resultSet.getString(resultSet.findColumn("title")),
							resultSet.getString(resultSet.findColumn("news_url")),
							resultSet.getString(resultSet.findColumn("thumbicon_url")),
							resultSet.getString(resultSet.findColumn("video_url")),
							resultSet.getInt(resultSet.findColumn("channel_id")));
					news.setSource(resultSet.getString(resultSet.findColumn("source")));
					news.setContent(resultSet.getString(resultSet.findColumn("content")));
					news.setAuthor(resultSet.getString(resultSet.findColumn("author")));
					news.setPublishTime(resultSet.getString(resultSet.findColumn("publish_time")));
					result.add(news);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<News> getCpdNews(int count) {
		String sql = "select * from video_news where channel_id=20 and title like '%中国警务新闻%' order by publish_time desc limit ?";
		ArrayList<News> result = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, count);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = new ArrayList<>();
				do {
					News news = new News(resultSet.getInt(resultSet.findColumn("id")),
							resultSet.getString(resultSet.findColumn("title")),
							resultSet.getString(resultSet.findColumn("news_url")),
							resultSet.getString(resultSet.findColumn("thumbicon_url")),
							resultSet.getString(resultSet.findColumn("video_url")),
							resultSet.getInt(resultSet.findColumn("channel_id")));
					news.setSource(resultSet.getString(resultSet.findColumn("source")));
					news.setContent(resultSet.getString(resultSet.findColumn("content")));
					news.setAuthor(resultSet.getString(resultSet.findColumn("author")));
					news.setPublishTime(resultSet.getString(resultSet.findColumn("publish_time")));
					result.add(news);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<News> getCpdNewsByPage(int count, int offset) {
		String sql = "select * from video_news where channel_id=20 and title like '%中国警务新闻%' order by publish_time desc limit ? offset ?";
		ArrayList<News> result = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, count);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = new ArrayList<>();
				do {
					News news = new News(resultSet.getInt(resultSet.findColumn("id")),
							resultSet.getString(resultSet.findColumn("title")),
							resultSet.getString(resultSet.findColumn("news_url")),
							resultSet.getString(resultSet.findColumn("thumbicon_url")),
							resultSet.getString(resultSet.findColumn("video_url")),
							resultSet.getInt(resultSet.findColumn("channel_id")));
					news.setSource(resultSet.getString(resultSet.findColumn("source")));
					news.setContent(resultSet.getString(resultSet.findColumn("content")));
					news.setAuthor(resultSet.getString(resultSet.findColumn("author")));
					news.setPublishTime(resultSet.getString(resultSet.findColumn("publish_time")));
					result.add(news);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<News> getNotCpdNews(int count) {
		String sql = "select * from video_news where channel_id=20 and title not like '%中国警务新闻%' order by publish_time desc limit ?";
		ArrayList<News> result = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, count);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = new ArrayList<>();
				do {
					News news = new News(resultSet.getInt(resultSet.findColumn("id")),
							resultSet.getString(resultSet.findColumn("title")),
							resultSet.getString(resultSet.findColumn("news_url")),
							resultSet.getString(resultSet.findColumn("thumbicon_url")),
							resultSet.getString(resultSet.findColumn("video_url")),
							resultSet.getInt(resultSet.findColumn("channel_id")));
					news.setSource(resultSet.getString(resultSet.findColumn("source")));
					news.setContent(resultSet.getString(resultSet.findColumn("content")));
					news.setAuthor(resultSet.getString(resultSet.findColumn("author")));
					news.setPublishTime(resultSet.getString(resultSet.findColumn("publish_time")));
					result.add(news);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<News> getNotCpdNewsByPage(int count, int offset) {
		String sql = "select * from video_news where channel_id=20 and title not like '%中国警务新闻%' order by publish_time desc limit ? offset ?";
		ArrayList<News> result = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, count);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = new ArrayList<>();
				do {
					News news = new News(resultSet.getInt(resultSet.findColumn("id")),
							resultSet.getString(resultSet.findColumn("title")),
							resultSet.getString(resultSet.findColumn("news_url")),
							resultSet.getString(resultSet.findColumn("thumbicon_url")),
							resultSet.getString(resultSet.findColumn("video_url")),
							resultSet.getInt(resultSet.findColumn("channel_id")));
					news.setSource(resultSet.getString(resultSet.findColumn("source")));
					news.setContent(resultSet.getString(resultSet.findColumn("content")));
					news.setAuthor(resultSet.getString(resultSet.findColumn("author")));
					news.setPublishTime(resultSet.getString(resultSet.findColumn("publish_time")));
					result.add(news);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
