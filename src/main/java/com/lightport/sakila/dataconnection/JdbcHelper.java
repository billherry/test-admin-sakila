package com.lightport.sakila.dataconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JdbcHelper {
	public Connection conn;
	private Log log;

	public void openConnect(String type, String host, String port, String database, String username, String pass)
			throws Exception {
		log = LogFactory.getLog(Class.class);
		Class.forName("com.mysql.jdbc.Driver");
		String connectionString = String.format("jdbc:%s://%s:%s/%s", type, port, host, database);
		conn = DriverManager.getConnection(connectionString, username, pass);
		log.info("connection succes");
	}

	public void openConnect() throws Exception {
		log = LogFactory.getLog(Class.class);
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "root");
		log.info("connection succes");
	}

	public Connection getConection() {
		return this.conn;
	}

	public ResultSetMetaData getTableMetadata(String tableName) throws Exception {
		PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM "+tableName);
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.getMetaData();
	}
}
