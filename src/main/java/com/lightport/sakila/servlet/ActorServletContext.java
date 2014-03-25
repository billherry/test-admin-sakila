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

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public ActorServletContext(int pageLimit, int pageStart) {
		this.pageLimit = pageLimit;
		this.pageStart = pageStart;
	}

	public void createConnection() {
		try {
			JdbcHelper jdbcHelper = new JdbcHelper();
			jdbcHelper.openConnect();
			JsonHelper jsonHelper = new JsonHelper(jdbcHelper);
			jsonObject = jsonHelper
					.getJsonObject("SELECT * FROM actor LIMIT " + pageStart
							+ "," + pageLimit + "");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
