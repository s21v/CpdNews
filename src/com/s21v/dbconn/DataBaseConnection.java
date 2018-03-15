package com.s21v.dbconn;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DataBaseConnection {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWORD = "nji90()OKM";
	private static final String URL = "jdbc:mysql://localhost:3306/cpdnews?useSSL=false";
	private Connection conn;
	
	public DataBaseConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	public Connection getConn() {
		return conn;
	}
	
	
}
