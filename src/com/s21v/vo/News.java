package com.s21v.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * ����ģ����
 * @author s21v
 */
public class News {
	private String news_id;		//����ID
	private String channel_id;	//��ĿID
	private String homePageTitle;	//��Ŀҳ����
	private String contentPageTitle;	//����ҳ����
	private String pub_time;	//����ʱ��
	private String source;		//��Դ
	private String author;		//����
	private String poster;		//���α༭
	private String content;		//��������
	private String url;			//���ŵ�url����
	private String picUrls;	//�����е�ͼƬ����
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getHomePageTitle() {
		return homePageTitle;
	}
	public void setHomePageTitle(String homePageTitle) {
		this.homePageTitle = homePageTitle;
	}
	public String getContentPageTitle() {
		return contentPageTitle;
	}
	public void setContentPageTitle(String contentPageTitle) {
		this.contentPageTitle = contentPageTitle;
	}
	public String getPub_time() {
		return pub_time;
	}
	public void setPub_time(String pub_time) {
		this.pub_time = pub_time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPicUrls() {
		return picUrls;
	}
	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}
	@Override
	public String toString() {
		return "News [news_id=" + news_id + ", channel_id=" + channel_id + ", homePageTitle=" + homePageTitle
				+ ", contentPageTitle=" + contentPageTitle + ", pub_time=" + pub_time + ", source=" + source
				+ ", author=" + author + ", poster=" + poster + ", content=" + content + ", url=" + url + ", picUrls="
				+ picUrls + "]";
	}
	
}
