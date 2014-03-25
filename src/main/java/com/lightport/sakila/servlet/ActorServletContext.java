package com.lightport.sakila.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.dataconnection.JsonHelper;

public class ActorServletContext {

	private Log log = LogFactory.getLog(Class.class);
	private int pageLimit;
	private int pageStart;
	private JSONObject jsonObject;
	private String columnName;
	private String asc;

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public ActorServletContext(int pageLimit, int pageStart, String column,
			String asc) {
		this.pageLimit = pageLimit;
		this.pageStart = pageStart;
		this.columnName = column;
		this.asc = asc;
	}

	public void createConnection() {
		try {
			JdbcHelper jdbcHelper = new JdbcHelper();
			jdbcHelper.openConnect();
			JsonHelper jsonHelper = new JsonHelper(jdbcHelper);
			if (columnName != null)
			getJsonSortedQuery(jsonHelper);
			else
				getJsonQuery(jsonHelper);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void getJsonSortedQuery(JsonHelper jsonHelper) throws Exception {
		jsonObject = jsonHelper
.getJsonObject("select * from actor order by "
				+ columnName + " "
						+ asc.toUpperCase() + " LIMIT "
						+ pageStart + "," + pageLimit);
	}

	private void getJsonQuery(JsonHelper jsonHelper) throws Exception {
		jsonObject = jsonHelper.getJsonObject("SELECT * FROM actor LIMIT "
				+ pageStart + "," + pageLimit);
	}
}
