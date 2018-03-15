package com.s21v.dao;

import java.util.ArrayList;
import com.s21v.vo.News;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

public interface NewsDao {
	
	//分页查询制定栏目下的新闻
	ArrayList<News> searchNews(String channel_id, int limitNum, int offset) throws Exception;
	
}
