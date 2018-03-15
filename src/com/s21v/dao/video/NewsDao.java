package com.s21v.dao.video;

import java.util.ArrayList;

import com.s21v.vo.video.Channel;
import com.s21v.vo.video.News;

public interface NewsDao {
	// ���ĳ����Ŀ�������������
	ArrayList<News> getLastNews4Channel(int channelId, int count);
	
	// ��ҳ��ȡ����
	ArrayList<News> getNewsByPage(int channelId, int count, int offset);	//limit count offset offset
	
	// ����й��������ŵ�������� , ����������Ŀ�»����������ţ����Դ˴�ר�Ų��Ҿ������ŵ���Ƶ, channelIdΪ20
	ArrayList<News> getCpdNews(int count);
	
	// ��ҳ����й��������ţ�channelIdΪ20
	ArrayList<News> getCpdNewsByPage(int count, int offset);
	
	// ��þ����������������������ţ�channelIdΪ20
	ArrayList<News> getNotCpdNews(int count);
	
	// ��ҳ��þ����������������������ţ�channelIdΪ20
	ArrayList<News> getNotCpdNewsByPage(int count, int offset);
}
