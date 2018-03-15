package com.s21v.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.s21v.impl.UserDaoProxy;
import com.s21v.vo.User;

/**
 * Servlet implementation class UserDBServlet
 */
@WebServlet("/UserDBServlet")
public class UserDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDBServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("m");	//查找用户
		if("query".equals(method)) {
			String phoneNum = request.getParameter("pn");
			String password = request.getParameter("p");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				User user = userDaoProxy.findUser(phoneNum, password);
				if (user != null) {
					response.setHeader("status", "success");
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append(jsonUserByUser(user));
				} else {
					response.getWriter().append("fail");
					response.setHeader("status", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("fail");
				response.setHeader("status", "fail");
			} finally {
				userDaoProxy.closeDbconnection();
			}
		} else if("queryByPlatformId".equals(method)) {	//按平台搜索
			String platformId = request.getParameter("pId");
			String platformName = request.getParameter("pName");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				User result = userDaoProxy.findUserByName4Platform(platformId, platformName);
				if (result != null) {
					response.setHeader("status", "success");
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append(jsonUserByUser(result));
					
				} else {
					response.getWriter().append("fail");
					response.setHeader("status", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("fail");
				response.setHeader("status", "fail");
			} finally {
				userDaoProxy.closeDbconnection();
			}	
		} else if("register".equals(method)) {	//注册
			String phoneNum = request.getParameter("pn");
			String password = request.getParameter("p");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				User result = userDaoProxy.add(phoneNum, password);
				if (result != null) {
					response.setHeader("status", "success");
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append(jsonUserByUser(result));
				} else {
					response.getWriter().append("fail");
					response.setHeader("status", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("fail");
				response.setHeader("status", "fail");
			} finally {
				userDaoProxy.closeDbconnection();
			}
		} else if("registerWithPlatformInfo".equals(method)) {
			String phoneNum = request.getParameter("pn");
			String password = request.getParameter("p");
			String platformName = request.getParameter("pfn");
			String platformId = request.getParameter("pfId");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				User result = userDaoProxy.add(phoneNum, password, platformName, platformId);
				if(result != null) {
					response.setHeader("status", "success");
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append(jsonUserByUser(result));
				} else {
					response.getWriter().append("fail");
					response.setHeader("status", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("fail");
				response.setHeader("status", "fail");
			} finally {
				userDaoProxy.closeDbconnection();
			}
		} else if("updateQQByPn".equals(method)) {
			String phoneNum = request.getParameter("pn");
			String platformQQId = request.getParameter("pfId");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				int result = userDaoProxy.updateQQByPhoneNum(phoneNum, platformQQId);
				if(result > 0) {
					User user = userDaoProxy.findUserByName4Platform(platformQQId, "QQ");
					if(user != null) {
						response.setHeader("status", "success");
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append(jsonUserByUser(user));
					} else {
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append("没有找到该用户");
						response.setHeader("status", "fail");
					}
				} else {
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append("没有找到该用户");
					response.setHeader("status", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.getWriter().append("没有找到该用户");
				response.setHeader("status", "fail");
			} finally {
				userDaoProxy.closeDbconnection();
			}
		} else if("updateWeChatByPn".equals(method)) {
			String phoneNum = request.getParameter("pn");
			String platformWeChatId = request.getParameter("pfId");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				int result = userDaoProxy.updateWeChatByPhoneNum(phoneNum, platformWeChatId);
				if(result > 0) {
					User user = userDaoProxy.findUserByName4Platform(platformWeChatId, "WeChat");
					if(user != null) {
						response.setHeader("status", "success");
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append(jsonUserByUser(user));
					} else {
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append("没有找到该用户");
						response.setHeader("status", "fail");
					}
				} else {
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append("没有找到该用户");
					response.setHeader("status", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.getWriter().append("服务器错误");
				response.setHeader("status", "fail");
			} finally {
				userDaoProxy.closeDbconnection();
			}
		}  else if("updateSinaWeiboByPn".equals(method)) {
			String phoneNum = request.getParameter("pn");
			String platformSinaWeiboId = request.getParameter("pfId");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				int result = userDaoProxy.updateSinaWeiboByPhoneNum(phoneNum, platformSinaWeiboId);
				if(result > 0) {
					User user = userDaoProxy.findUserByName4Platform(platformSinaWeiboId, "SinaWeibo");
					if(user != null) {
						response.setHeader("status", "success");
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append(jsonUserByUser(user));
					} else {
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append("没有找到该用户");
						response.setHeader("status", "fail");
					}
				} else {
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.getWriter().append("没有找到该用户");
					response.setHeader("status", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.getWriter().append("服务器错误");
				response.setHeader("status", "fail");
			} finally {
				userDaoProxy.closeDbconnection();
			}
		} else if("updatePassword".equals(method)) {
			String phoneNum = request.getParameter("pn");
			String password = request.getParameter("p");
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			try {
				if(userDaoProxy.updatePasswordByPhoneNum(phoneNum, password) > 0) {
					User user = userDaoProxy.findUser(phoneNum, password);
					if(user != null) {
						response.setHeader("status", "success");
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append(jsonUserByUser(user));
					} else {
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.getWriter().append("没有找到该用户");
						response.setHeader("status", "fail");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.getWriter().append("服务器错误");
				response.setHeader("status", "fail");
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
	
	private String jsonUser(int id, String nickname, String phoneNum, String password) {
		Gson gson = new Gson();
		return gson.toJson(new User(id, nickname, password, phoneNum));
	}
	
	private String jsonUserByUser(User user) {
		Gson gson = new Gson();
		return gson.toJson(user);
	}

}
