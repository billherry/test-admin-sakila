package com.lightport.sakila.servlet;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.dataconnection.JsonHelper;

public class ActorHandler {

	private JdbcHelper jdbcHelper;
	private JSONObject jsonObject;
	private QueryHandler queryHandler;

	public ActorHandler(HttpServletRequest request) throws Exception {
		jdbcConnect();
		String queryFromParams = getQueryFromParams(request);
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

	private String getQueryFromParams(HttpServletRequest request) {
		queryHandler = new QueryHandler(request, "actor");
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
