package com.lightport.sakila.servlet;

import java.sql.ResultSet;
import java.util.List;
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
	private QueryHandler queryHandler;
	private Map<String, String> map;
	private List<FilterInfo> filters;

	public ActorHandler(Map<String, String> map, List<FilterInfo> filters) throws Exception {
		this.map = map;
		this.filters = filters;
		initQueryHandler();
	}

	private void initQueryHandler() throws Exception {
		jdbcHelper.openConnect();
		queryHandler = new QueryHandler(map, jdbcHelper.conn, TABLE_NAME, filters);
		ResultSet resultSet = queryHandler.getResultSet();
		jsonObject = jsonHelper.getJsonObject(resultSet);
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}

}
