package com.s21v.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.s21v.impl.ChannelDaoProxy;
import com.s21v.vo.Channel;

/**
 * Servlet implementation class ChannelDBServlet
 */
@WebServlet("/ChannelDBServlet")
public class ChannelDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChannelDBServlet() {
		super();
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("m");
		if ("version".equals(method)) {	//获得栏目列表版本
			try{
				FileInputStream fis = new FileInputStream(getServletContext().getRealPath("ChannelVersion.txt"));
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				String version = "";
				while((hasRead  = fis.read(buffer)) != -1) {
					version += new String(buffer, 0 ,hasRead);
				}
				fis.close();
				response.setHeader("status", "success");
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().append(version);
			} catch (Exception e) {
				response.setHeader("status", "fail");
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().append("栏目版本读取错误");
			}
		} else if ("queryAllChannel".equals(method)) {	//获得当前所有栏目
			ChannelDaoProxy channelDaoProxy = new ChannelDaoProxy();
			try {
				ArrayList<Channel> result = channelDaoProxy.searchAllChannel();
				if (result != null && result.size() == 0) {
					throw new Exception();
				} else {
					response.setHeader("status", "success");
					response.setHeader("Content-type", "text/html;charset=UTF-8");
					Gson gson = new Gson();
					response.getWriter().append(gson.toJson(result));
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setHeader("status", "fail");
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().append("数据库读取错误");
			} finally {
				channelDaoProxy.closeDbconnection();
			}
		} else if ("channelInfo".equals(method)) {
			try{
				FileInputStream fis = new FileInputStream(getServletContext().getRealPath("ChannelVersion.txt"));
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				String version = "";
				while((hasRead  = fis.read(buffer)) != -1) {
					version += new String(buffer, 0 ,hasRead);
				}
				fis.close();
				response.setHeader("status", "success");
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setHeader("channelVersion", version);
				ChannelDaoProxy channelDaoProxy = new ChannelDaoProxy();
				try {
					ArrayList<Channel> result = channelDaoProxy.searchAllChannel();
					if (result != null && result.size() == 0) {
						throw new Exception();
					} else {
						Gson gson = new Gson();
						response.getWriter().append(gson.toJson(result));
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.setHeader("status", "fail");
					response.setHeader("Content-type", "text/html;charset=UTF-8");
					response.getWriter().append("数据库读取错误");
				} finally {
					channelDaoProxy.closeDbconnection();
				}
			} catch (Exception e) {
				response.setHeader("status", "fail");
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().append("栏目版本读取错误");
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
