package com.s21v.dao.video;

import java.util.ArrayList;
import com.s21v.vo.video.Channel;

public interface ChannelDao {
	//获取有效的视频栏目信息	
	ArrayList<Channel> getVideoChannel();
	//获取视频专题栏目	
	ArrayList<Channel> getVideoSubject();
	//获得最新的专题栏目
	Channel getLastSubject();

}
