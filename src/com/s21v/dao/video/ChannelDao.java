package com.s21v.dao.video;

import java.util.ArrayList;
import com.s21v.vo.video.Channel;

public interface ChannelDao {
	//��ȡ��Ч����Ƶ��Ŀ��Ϣ	
	ArrayList<Channel> getVideoChannel();
	//��ȡ��Ƶר����Ŀ	
	ArrayList<Channel> getVideoSubject();
	//������µ�ר����Ŀ
	Channel getLastSubject();

}
