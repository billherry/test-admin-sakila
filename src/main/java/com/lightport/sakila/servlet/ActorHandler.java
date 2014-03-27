package com.lightport.sakila.servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.json.JSONObject;

import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.dataconnection.JsonHelper;

public class ActorHandler {

	private JdbcHelper jdbcHelper;
	private JSONObject jsonObject;
	private QueryHandler queryHandler;

	public ActorHandler(Map<String, String[]> parameterMap) throws Exception {
		jdbcConnect();
		String queryFromParams = getQueryFromParams(parameterMap);
		ResultSet resultSet = getResultSet(queryFromParams);
		JsonHelper jsonHelper = new JsonHelper();
		jsonObject = jsonHelper.getJsonObject(resultSet);
		setJsonTotalCount();
	}

	private void jdbcConnect() throws Exception {
		jdbcHelper = new JdbcHelper();
		jdbcHelper.openConnect();
	}

	private ResultSet getResultSet(String query) {
		return jdbcHelper.getResultSet(query);
	}

	private String getQueryFromParams(Map<String, String[]> parameterMap) {
		queryHandler = new QueryHandler(parameterMap, "actor");
		return queryHandler.getQueryString();
	}

	private void setJsonTotalCount() throws SQLException {
		String query = queryHandler.getTotalCountQuery();
		ResultSet resultSet = jdbcHelper.getResultSet(query);
		resultSet.last();
		String total = resultSet.getString(1);
		jsonObject.put("total", total);
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}

}
