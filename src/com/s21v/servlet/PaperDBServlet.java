package com.s21v.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.impl.PaperDaoImpl;
import com.s21v.vo.Paper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/PaperDBServlet")
public class PaperDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseConnection dataBaseConnection = null;
        try {
            dataBaseConnection = new DataBaseConnection();
            String method = req.getParameter("m");
            if ("getPaperByRange".equals(method)) {
                String type = req.getParameter("type");
                String date = req.getParameter("date");
                int duration = Integer.parseInt(req.getParameter("duration"));
                PaperDaoImpl paperDaoImpl = new PaperDaoImpl(dataBaseConnection.getConn());
                ArrayList<Paper> result = paperDaoImpl.searchPaperInRangeByType(type, date, duration);
                for (Paper paper: result) {
                    System.out.println(paper);
                }
                if (result != null) {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                    resp.setStatus(200);
                    resp.setHeader("Content-type", "text/html;charset=UTF-8");
                    resp.getWriter().append(gson.toJson(result));
                } else {
                    resp.setHeader("Content-type", "text/html;charset=UTF-8");
                    resp.sendError(500, "数据库获取不到版面信息");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
