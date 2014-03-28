package com.lightport.sakila.dataconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JdbcHelper {
	public Connection conn;
	private Statement st;
	private ResultSet rs;
	private Log log;

	public Connection getConection() {
		return this.conn;
	}

	public void openConnect(String type, String host, String port, String database, String username, String pass)
			throws Exception {
		log = LogFactory.getLog(Class.class);
		Class.forName("com.mysql.jdbc.Driver");
		String connectionString = String.format("jdbc:%s://%s:%s/%s", type, port, host, database);
		conn = DriverManager.getConnection(connectionString, username, pass);
		st = conn.createStatement();
		log.info("connection succes");
	}

	public void openConnect() throws Exception {
		log = LogFactory.getLog(Class.class);
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "root");
		st = conn.createStatement();
		log.info("connection succes");
	}

	public ResultSet getResultSet(String query) {
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return rs;
	}

	public boolean isConnected() {
		return conn != null;
	}
}
