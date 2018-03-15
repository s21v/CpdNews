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
import com.s21v.impl.VideoChannelDaoImpl;
import com.s21v.vo.video.Channel;

/**
 * Servlet implementation class VideoChannelDBServlet
 */
@WebServlet("/VideoChannelDBServlet")
public class VideoChannelDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoChannelDBServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			String method = request.getParameter("m");
			if ("getAllChannel".equals(method)) {
				VideoChannelDaoImpl daoImpl = new VideoChannelDaoImpl(dbc.getConn());
				ArrayList<Channel> channels = daoImpl.getVideoChannel();
				if (channels != null) {
					Gson gson = new Gson();
					response.setStatus(200);
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append(gson.toJson(channels));
				} else {
					response.sendError(500, "不能找到视频栏目");
				}
			} else if ("getAllSubject".equals(method)) {
				VideoChannelDaoImpl daoImpl = new VideoChannelDaoImpl(dbc.getConn());
				ArrayList<Channel> channels = daoImpl.getVideoSubject();
				if (channels != null) {
					Gson gson = new Gson();
					response.setStatus(200);
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append(gson.toJson(channels));
				} else 
					response.sendError(500, "不能找到视频专题");
			} else if ("getLastSubject".equals(method)) {
				VideoChannelDaoImpl daoImpl = new VideoChannelDaoImpl(dbc.getConn());
				Channel result = daoImpl.getLastSubject();
				Gson gson = new Gson();
				response.setStatus(200);
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().append(gson.toJson(result));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendError(500, "发生异常");
		} finally {
			if (dbc != null) {
				dbc.close();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
