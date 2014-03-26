package com.lightport.sakila.servlet;

import org.json.JSONObject;

import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.dataconnection.JsonHelper;

public class ActorServletContext {

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

	public void createConnection() throws Exception {
			JdbcHelper jdbcHelper = new JdbcHelper();
			jdbcHelper.openConnect();
			JsonHelper jsonHelper = new JsonHelper(jdbcHelper);

		if (columnName == null || asc == null)
			createJson(jsonHelper);
		else
			createSortedJson(jsonHelper);
	}

	private void createSortedJson(JsonHelper jsonHelper) throws Exception {
		jsonObject = jsonHelper
.getJsonObject("select * from actor order by "
				+ columnName + " "
						+ asc.toUpperCase() + " LIMIT "
						+ pageStart + "," + pageLimit);
	}

	private void createJson(JsonHelper jsonHelper) throws Exception {
		jsonObject = jsonHelper.getJsonObject("SELECT * FROM actor LIMIT "
				+ pageStart + "," + pageLimit);
	}
}
