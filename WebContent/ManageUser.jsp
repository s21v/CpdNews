<%@ page import="java.util.ArrayList"%>
<%@ page import="com.s21v.vo.User"%>
<%@ page import="com.s21v.impl.UserDaoProxy"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户管理</title>
</head>

<style>
	.item {text-align:center}
	.toolbar {width:132px}
	img {margin-right:10px;margin-left:10px;}
</style>

<%!
	void deleteUser(JspWriter out , int id) {
		UserDaoProxy userDaoProxy = new UserDaoProxy();
		try {
			if(userDaoProxy.deleteById(id)) {
				out.write("<script type='text/javaScript'>window.location.reload;</script>");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
%>

<body>
	<table border=1 width=100%>
	    <caption><b><h2>当前用户</h2></b></caption>
		<tr><th>用户ID</th><th>用户名</th><th>密码</th><th>操作</th></tr>
		<%
			UserDaoProxy userDaoProxy = new UserDaoProxy();
			ArrayList<User> data = new ArrayList<User>();
			data = userDaoProxy.findALL();
			for(User user : data) { 
				out.write("<tr>");
				out.write("<td class=\"item\">"+user.getId()+"</td>");
				out.write("<td class=\"item\">"+user.getName()+"</td>");
				out.write("<td class=\"item\">"+user.getPassword()+"</td>");
				out.write("<td class=\"toolbar\"><img src=\"imgs\\add.png\"/>"+
					"<img src=\"imgs\\delete.png\"/>"+
					"<img src=\"imgs\\modify.png\"/></td>");
				out.write("</tr>");
			}
		%>
	</table>
</body>
</html>