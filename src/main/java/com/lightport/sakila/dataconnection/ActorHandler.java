package com.lightport.sakila.dataconnection;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.lightport.sakila.business.QueryHandler;

public class ActorHandler {

	private JdbcHelper jdbcHelper;
	private JsonHelper jsonHelper = new JsonHelper();
	private JSONObject jsonObject;
	private final String TABLE_NAME = "actor";
	private QueryHandler queryHandler;
	private Map<String, String> map;
	private List<FilterInfo> filters;
	private static List<String> actorColumns;

	public ActorHandler(Map<String, String> map, List<FilterInfo> filters) throws Exception {
		this.map = map;
		this.filters = filters;
		jdbcHelper = new JdbcHelper();
		initQueryHandler();
	}

	private void initQueryHandler() throws Exception {
		jdbcHelper.openConnect();
		queryHandler = new QueryHandler(map, jdbcHelper.conn, TABLE_NAME, filters);
		ResultSet selectResultSet = queryHandler.getSelectResultSet();
		ResultSet countResultSet = queryHandler.getCountResultSet();
		jsonObject = jsonHelper.getJsonObject(selectResultSet);
		int jsonCount = jsonCount(countResultSet);
		jsonObject.put("total", jsonCount);
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}

	private int jsonCount(ResultSet countResultSet) throws Exception {
		countResultSet.last();
		return countResultSet.getInt(1);
	}

	public static List<String> getActorCoulomns() {
		return actorColumns;
	}

	public static void setActorCoulomns(List<String> actor_Columns) {
		actorColumns = actor_Columns;
	}
}
