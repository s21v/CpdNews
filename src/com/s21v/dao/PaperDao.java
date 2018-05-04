package com.s21v.dao;

import com.s21v.vo.Paper;

import java.util.ArrayList;

public interface PaperDao {
    //  获得一段时间内的报纸版面信息
    ArrayList<Paper> searchPaperInRangeByType(String type, String endDate, int duration);
}
