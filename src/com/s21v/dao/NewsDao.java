package com.s21v.dao;

import java.util.ArrayList;
import com.s21v.vo.News;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

public interface NewsDao {
	
	//��ҳ��ѯ�ƶ���Ŀ�µ�����
	ArrayList<News> searchNews(String channel_id, int limitNum, int offset) throws Exception;
	
}
