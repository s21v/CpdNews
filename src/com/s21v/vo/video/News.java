package com.s21v.vo.video;

public class News {
	private int id;
	private String title;
	private String newsUrl;
	private String source;
	private String author;
	private String publishTime;
	private String thumbIconUrl;
	private String videoUrl;
	private String content;
	private int channelId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNewsUrl() {
		return newsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
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
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getThumbIconUrl() {
		return thumbIconUrl;
	}
	public void setThumbIconUrl(String thumbIconUrl) {
		this.thumbIconUrl = thumbIconUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	public News() {
		super();
	}
	
	public News(int id, String title, String newsUrl, String thumbIconUrl, String videoUrl, int channelId) {
		super();
		this.id = id;
		this.title = title;
		this.newsUrl = newsUrl;
		this.thumbIconUrl = thumbIconUrl;
		this.videoUrl = videoUrl;
		this.channelId = channelId;
	}
	
}
