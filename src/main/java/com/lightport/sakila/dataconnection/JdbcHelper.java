package com.lightport.sakila.dataconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcHelper {
	private static DataSource dataSource;
	private static Map<String, Set<String>> columnName = new HashMap<String, Set<String>>();

	public static Set<String> getColumnNameList(String tableName) throws Exception {
		return columnName.get(tableName);
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void addTableColumns(String tableName) throws Exception {
		ResultSetMetaData actorMetadata = getMetadata(tableName);
		int columnCount = actorMetadata.getColumnCount();
		Set<String> tmpColumns = new HashSet<>();
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

	public static void initDataSource(String context) throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		dataSource = (DataSource) envContext.lookup(context);
	}
}
