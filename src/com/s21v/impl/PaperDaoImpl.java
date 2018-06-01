package com.s21v.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.Connection;
import com.s21v.dao.PaperDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.Paper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PaperDaoImpl implements PaperDao {
    private Connection conn;

    public PaperDaoImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public ArrayList<Paper> searchPaperInRangeByType(String type, String endDate, int duration) {
        System.out.println("type:"+type+",endDate:"+endDate+",duration:"+duration);
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String sql = "select * from szb where szb.type=? and szb.date<=? and szb.date>? - interval ? day order by szb.date desc, szb.number asc";
        ArrayList<Paper> result = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, endDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setInt(4, duration);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new ArrayList<>();
                do {
                    Paper paper = new Paper(resultSet.getDate(resultSet.findColumn("date")),
                            resultSet.getString(resultSet.findColumn("number")),
                            resultSet.getString(resultSet.findColumn("name")),
                            resultSet.getString(resultSet.findColumn("type")),
                            resultSet.getString(resultSet.findColumn("xmlPath")),
                            resultSet.getString(resultSet.findColumn("imgPath")),
                            resultSet.getBoolean(resultSet.findColumn("isWide")),
                            resultSet.getString(resultSet.findColumn("thumbPath")),
                            resultSet.getString(resultSet.findColumn("fullImgPath")));
                    result.add(paper);
                } while (resultSet.next());
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            DataBaseConnection conn = new DataBaseConnection();
            PaperDaoImpl paperDaoImpl = new PaperDaoImpl(conn.getConn());
            String inputdate = "2018-05-03";

            ArrayList<Paper> paperList = paperDaoImpl.searchPaperInRangeByType("szb", inputdate, 7);
            Date lastDate = paperList.get(paperList.size() - 1).getDate();

            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(lastDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
            String currentDate = format.format(calendar.getTime());
            System.out.println(currentDate);

            paperList = paperDaoImpl.searchPaperInRangeByType("szb", currentDate, 7);
            for (Paper paper: paperList) {
                System.out.println(paper);
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            System.out.println(gson.toJson(paperList));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
