package com.s21v.dao.video;

import java.util.ArrayList;

import com.s21v.vo.video.Channel;
import com.s21v.vo.video.News;

public interface NewsDao {
	// 获得某个栏目的最近几条新闻
	ArrayList<News> getLastNews4Channel(int channelId, int count);
	
	// 分页获取新闻
	ArrayList<News> getNewsByPage(int channelId, int count, int offset);	//limit count offset offset
	
	// 获得中国警务新闻的最近几条 , 警务新闻栏目下还有其他新闻，所以此处专门查找警务新闻的视频, channelId为20
	ArrayList<News> getCpdNews(int count);
	
	// 分页获得中国警务新闻，channelId为20
	ArrayList<News> getCpdNewsByPage(int count, int offset);
	
	// 获得警务新闻其他非联播类新闻，channelId为20
	ArrayList<News> getNotCpdNews(int count);
	
	// 分页获得警务新闻其他非联播类新闻，channelId为20
	ArrayList<News> getNotCpdNewsByPage(int count, int offset);
}
