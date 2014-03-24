package com.lightport.sakila.dataconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcHelper {
	private static Connection conn;
	private static Statement st;
	private ResultSet rs;

	public static void openConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","root");
			st = conn.createStatement();
			System.out.println("connection succes");
			} catch (Exception e) {			
		}
	}
	public ResultSet getResultSet(String query){
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
}
