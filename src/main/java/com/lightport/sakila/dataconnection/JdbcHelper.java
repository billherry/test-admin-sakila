package com.lightport.sakila.dataconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JdbcHelper {
	private static Connection conn;
	private static Statement st;
	private ResultSet rs;
	private static Log log;
	
	public static void openConnect() throws Exception {
		
			log = LogFactory.getLog(Class.class);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","root");
			st = conn.createStatement();
			log.info("connection succes");
			
	}
	public ResultSet getResultSet(String query){
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
