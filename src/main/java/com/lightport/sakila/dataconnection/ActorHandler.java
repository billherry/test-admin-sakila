package com.lightport.sakila.dataconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.lightport.sakila.business.QueryHandler;

public class ActorHandler {

	private JSONObject jsonObject;
	private final String TABLE_NAME = "actor";
	private QueryHandler queryHandler;
	private Map<String, String> map;
	private List<FilterInfo> filters;
	private Connection connection;
	private Log log;

	public ActorHandler(Map<String, String> map, List<FilterInfo> filters, Connection connection) throws Exception {
		log = LogFactory.getLog(Class.class);

		this.map = map;
		this.filters = filters;
		this.connection = connection;
		initQueryHandler();
	}

	private void initQueryHandler() throws Exception {
		log.info(String.format("ActorHandler ParameterMap:%s Filters: %s Connection:%s", map, filters, connection));
		queryHandler = new QueryHandler(map, connection, TABLE_NAME, filters);
		ResultSet selectResultSet = queryHandler.getSelectResultSet();
		ResultSet countResultSet = queryHandler.getCountResultSet();
		jsonObject = JsonHelper.getJsonObject(selectResultSet);
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
}
