package com.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static String url = "jdbc:mysql://localhost:3306/db_elm?useSSL=false&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "123";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void free(ResultSet rs,Statement stat,Connection conn) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stat != null)
					stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn != null) 
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
