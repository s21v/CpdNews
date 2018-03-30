package com.s21v.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.impl.VideoNewsDaoImpl;
import com.s21v.vo.video.News;

/**
 * Servlet implementation class VideoNewsDBServlet
 */
@WebServlet("/VideoNewsDBServlet")
public class VideoNewsDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoNewsDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBaseConnection dataBaseConnection = null;
		try {
			dataBaseConnection = new DataBaseConnection();
			String method = request.getParameter("m");
			//查询最新的视频新闻
			if ("last".equals(method)) {
				int channelId = Integer.parseInt(request.getParameter("cid"));
				int limitCount = Integer.parseInt(request.getParameter("count"));
				VideoNewsDaoImpl dao = new VideoNewsDaoImpl(dataBaseConnection.getConn());
				ArrayList<News> result = dao.getLastNews4Channel(channelId, limitCount);
				if (result != null) {
					Gson gson = new Gson();
					response.setStatus(200);
					response.setHeader("Content-type", "text/html;charset=UTF-8"); 
					response.getWriter().append(gson.toJson(result));
				} else {
					response.sendError(500, "数据库获取不到视频新闻");
				}
			} else if ("getCpdNews".equals(method)) {
				int limitCount = Integer.parseInt(request.getParameter("count"));
				VideoNewsDaoImpl dao = new VideoNewsDaoImpl(dataBaseConnection.getConn());
				ArrayList<News> result = dao.getCpdNews(limitCount);
				if (result != null) {
					Gson gson = new Gson();
					response.setStatus(200);
					response.setHeader("Content-type", "text/html;charset=UTF-8"); 
					response.getWriter().append(gson.toJson(result));
				} else {
					response.sendError(500, "数据库获取不到视频新闻");
				}
			} else if ("getNotCpdNews".equals(method)) {
				int limitCount = Integer.parseInt(request.getParameter("count"));
				VideoNewsDaoImpl dao = new VideoNewsDaoImpl(dataBaseConnection.getConn());
				ArrayList<News> result = dao.getNotCpdNews(limitCount);
				if (result != null) {
					Gson gson = new Gson();
					response.setStatus(200);
					response.setHeader("Content-type", "text/html;charset=UTF-8"); 
					response.getWriter().append(gson.toJson(result));
				} else {
					response.sendError(500, "数据库获取不到视频新闻");
				}
			} else if ("getNewsByPage".equals(method)) {
				int channelId = Integer.parseInt(request.getParameter("cid"));
				int page = Integer.parseInt(request.getParameter("curPage"));
				int count = Integer.parseInt(request.getParameter("count"));
				VideoNewsDaoImpl dao = new VideoNewsDaoImpl(dataBaseConnection.getConn());
				ArrayList<News> result = dao.getNewsByPage(channelId, count, (page-1)*count);
				if (result != null) {
					Gson gson = new Gson();
					response.setStatus(200);
					response.setHeader("Content-type", "text/html;charset=UTF-8");
					response.getWriter().append(gson.toJson(result));
				} else {
					response.sendError(500, "数据库获取不到视频新闻");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendError(500, "发生异常");
		} finally {
			if (dataBaseConnection != null) {
				dataBaseConnection.close();
			}
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
