package com.lightport.sakila.business;

import java.util.Map;

import org.json.JSONObject;

import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.servlet.QueryHandler;

public class ActorHandler {

	private JdbcHelper jdbcHelper = new JdbcHelper();
	private JSONObject jsonObject;
	private final String TABLE_NAME = "actor";
	private final QueryHandler queryHandler;

	public ActorHandler(Map<String, String> map) throws Exception {
		jdbcHelper.openConnect();
		queryHandler = new QueryHandler(map, jdbcHelper.conn, TABLE_NAME);
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}

}
