package com.s21v.vo.video;

public class Channel {
	private int id;
	private String name;
	private int parentChannelId;
	private String url;
	private String subject_img_url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentChannelId() {
		return parentChannelId;
	}
	public void setParentChannelId(int parentChannelId) {
		this.parentChannelId = parentChannelId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSubject_img_url() {
		return subject_img_url;
	}
	public void setSubject_img_url(String subject_img_url) {
		this.subject_img_url = subject_img_url;
	}
	
	public Channel() {
		super();
	}
	
	public Channel(int id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}
	
}
