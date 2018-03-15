package com.s21v.dao;

import java.util.ArrayList;
import com.s21v.vo.Channel;

public interface ChannelDao {

	ArrayList<Channel> searchAllChannel() throws Exception;

}
