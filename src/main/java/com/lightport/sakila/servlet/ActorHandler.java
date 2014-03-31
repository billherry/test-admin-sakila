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
	private final QueryHandler queryHandler;

	// JsonObject-et állít össze és QuerytHandler-t hív;
	public ActorHandler(Map<String, String> map, List<FilterInfo> filters) throws Exception {
		jdbcHelper.openConnect();
		// SELECT query
		queryHandler = new QueryHandler(map, jdbcHelper.conn, TABLE_NAME, filters);
		String query = queryHandler.getQuery();
		ResultSet resultSet = jdbcHelper.getResultSet(query);
		// count Query
		String countQuery = queryHandler.getCountQuery();
		ResultSet countResultset = jdbcHelper.getResultSet(countQuery);
		int jsonCount = setJsonCount(countResultset);
		// Json beállítása
		jsonObject = jsonHelper.getJsonObject(resultSet);
		jsonHelper.setJsonItemCount(jsonCount, jsonObject);
	}

	private int setJsonCount(ResultSet rs) throws Exception {
		rs.last();
		return rs.getInt(1);
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}

}
