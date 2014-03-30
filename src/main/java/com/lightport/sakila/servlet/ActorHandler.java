package com.lightport.sakila.servlet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.json.JSONObject;

import com.lightport.sakila.business.QueryHandler;
import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.dataconnection.JsonHelper;

public class ActorHandler {

	private JdbcHelper jdbcHelper = new JdbcHelper();
	private JsonHelper jsonHelper = new JsonHelper();
	private JSONObject jsonObject;
	private final String TABLE_NAME = "actor";
	private final QueryHandler queryHandler;

	public ActorHandler(Map<String, String> map) throws Exception {
		jdbcHelper.openConnect();
		queryHandler = new QueryHandler(map, jdbcHelper.conn, TABLE_NAME);
		PreparedStatement preparedStatement = queryHandler.getPreparedStatement();
		ResultSet resultSet = jdbcHelper.getResultSet(preparedStatement);
		jsonObject = jsonHelper.getJsonObject(resultSet);
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}

}
