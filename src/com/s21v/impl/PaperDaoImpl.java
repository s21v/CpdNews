package com.s21v.impl;

import com.mysql.jdbc.Connection;
import com.s21v.dao.PaperDao;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.vo.Paper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaperDaoImpl implements PaperDao {
    private Connection conn;

    public PaperDaoImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public ArrayList<Paper> searchPaperInRangeByType(String type, String endDate, int duration) {
        String sql = "select * from szb where szb.type=? and szb.date>?- interval ? day order by szb.date desc, szb.number asc";
        ArrayList<Paper> result = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, endDate);
            preparedStatement.setInt(3, duration);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new ArrayList<>();
                do {
                    Paper paper = new Paper(resultSet.getString(resultSet.findColumn("date")),
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
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            for (Paper paper : paperDaoImpl.searchPaperInRangeByType("szb", format.format(new Date()), 7))
                System.out.println(paper);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
