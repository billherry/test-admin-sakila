package com.lightport.sakila.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;


public class QueryHandler {

	private String start;
	private String limit;
	private String query;
	StringBuilder stringBuilder;
	PreparedStatement prepStatment;
	ResultSet resultSet;
	

	public ResultSet getResultSet() throws Exception {
		System.out.println(prepStatment);
		return prepStatment.executeQuery();
	}

	public QueryHandler(Map<String, String> map, Connection connection, String tableName) throws Exception {

		start = map.get("start");
		limit = map.get("limit");

		stringBuilder = new StringBuilder();
		buildQueryString(String.format("SELECT * FROM %s ", tableName));
		initQuery();
		query = stringBuilder.toString();
		prepStatment = connection.prepareStatement(query);
	}

	private void initQuery() throws Exception {
		if (!start.equals("null") && !limit.equals("null")) {
			buildQueryString(pagingQuery());
			prepStatment.setInt(1, Integer.parseInt(start));
		}
	}

	private String pagingQuery() throws Exception {
		return "LIMIT ? , ? ";
		
	}

	private void buildQueryString(String queryPart) {
		stringBuilder.append(queryPart);
	}
}
