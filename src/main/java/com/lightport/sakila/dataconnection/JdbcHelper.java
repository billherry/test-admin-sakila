package com.lightport.sakila.dataconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcHelper {
	private static DataSource dataSource;
	private Connection connection;
	private static Map<String, List<String>> columnName;

	static
	{
		columnName = new HashMap<String, List<String>>();
	}

	static public List<String> getColumnNameList(String tableName) throws Exception {
		return columnName.get(tableName);
	}

	static public void addTableColumns(String tableName) throws Exception {
		ResultSetMetaData actorMetadata = getMetadata(tableName);
		int columnCount = actorMetadata.getColumnCount();
		List<String> tmpColumns = new ArrayList<>();
		for (int i = 0; i < columnCount; i++) {
			tmpColumns.add(actorMetadata.getColumnLabel(i + 1));
		}
		columnName.put(tableName, tmpColumns);
	}

	private static ResultSetMetaData getMetadata(String tableName) throws Exception, SQLException {
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " LIMIT 0,1");
		ResultSetMetaData actorMetadata = resultSet.getMetaData();
		return actorMetadata;
	}

	public static DataSource getDataSource() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		dataSource = (DataSource) envContext.lookup("jdbc/Sakila");
		return dataSource;
	}
}
