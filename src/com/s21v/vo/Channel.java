package com.s21v.vo;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Channel {
	private String id;
	private String name;
	
	public Channel(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + "]";
	}

	public static void main(String[] args){
		Channel c1 = new Channel("1", "要闻");
		System.out.println(c1.toJson());
		Channel c2 = new Channel("2", "公安");
		Channel c3 = new Channel("3", "时政");		
		//---------------------------- list类型 json的读取
		ArrayList<Channel> list = new ArrayList<Channel>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		Gson gson = new Gson();
		String arrayJsonString = gson.toJson(list);
		System.out.println(arrayJsonString);
		ArrayList<Channel> result = gson.fromJson(arrayJsonString, new TypeToken<ArrayList<Channel>>(){}.getType());
		for(Channel channel : result){
			System.out.println(channel);
		}
		//-------------------------------
	}
} 
