package com.lightport.sakila.business;

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
	private String tableName;
	

	public ResultSet getResultSet() throws Exception {
		System.out.println(prepStatment);
		return prepStatment.executeQuery();
	}

	public QueryHandler(Map<String, String> map, Connection connection, String tableName) throws Exception {

		this.tableName = tableName;
		start = map.get("start");
		limit = map.get("limit");

		stringBuilder = new StringBuilder();
		
		buildSelectQueryString();
		specializationSelectQuery();
		
		query = stringBuilder.toString();
		prepStatment = connection.prepareStatement(query);
	}

	private void specializationSelectQuery () throws Exception {
		if (!start.equals("null") && !limit.equals("null")) {
			stringBuilder.append("LIMIT ? , ? ");			
		}
	}

	private void buildSelectQueryString() {
		stringBuilder.append((String.format("SELECT * FROM %s ", tableName)));
	}

	public PreparedStatement getPreparedStatement() {
		return this.prepStatment;		
	}
}
