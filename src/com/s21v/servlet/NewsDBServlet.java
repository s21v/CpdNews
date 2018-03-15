package com.s21v.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.s21v.dbconn.DataBaseConnection;
import com.s21v.impl.NewsDaoImpl;
import com.s21v.vo.News;

/**
 * Servlet implementation class NewsDBServlet
 */
@WebServlet("/NewsDBServlet")
public class NewsDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String channel_id = request.getParameter("CID");
		int limitNum = Integer.valueOf(request.getParameter("limitNum"));
		int offset = (Integer.valueOf(request.getParameter("page"))-1) * limitNum;
//		System.out.println("limitNum="+limitNum+", offset="+limitNum+", channel_id="+channel_id);
		DataBaseConnection dbc = null;
		try {
			dbc = new DataBaseConnection();
			NewsDaoImpl dao = new NewsDaoImpl(dbc.getConn());
			ArrayList<News> result = dao.searchNews(channel_id, limitNum, offset);
			if (result != null) {
				response.setHeader("status", "success");
				response.setHeader("Content-type", "text/html;charset=UTF-8"); 
				
				Gson gson = new Gson();
				String responseStr = gson.toJson(result);
		
				response.getWriter().append(responseStr);
				synchronized(System.out){
					System.out.println(responseStr);
				}
				
			} else {
				response.setHeader("status", "fail");
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.getWriter().append("新闻列表读取错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setHeader("status", "fail");
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			response.getWriter().append("数据库读取错误");
			e.printStackTrace();
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
