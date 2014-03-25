package com.lightport.sakila.dataconnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class JsonHelper {

	private JdbcHelper jdbc;

	public JsonHelper(JdbcHelper jdbc) {
		this.jdbc = jdbc;

	}

	public JSONObject getJsonObject(String query) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		ResultSet resultSet = jdbc.getResultSet(query);
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();

		while (resultSet.next()) {
			HashMap<String, Object> map = mapFromResultSet(columnCount,
					metaData, resultSet);
			list.add(map);
		}
		int total = calculateTotal();
		jsonObject .put("total", total);
		jsonObject.put("actors", list);

		return jsonObject;
	}

	private HashMap<String, Object> mapFromResultSet(int columnCount,
			ResultSetMetaData metaData, ResultSet resultSet)
			throws SQLException {
		HashMap<String, Object> map = new HashMap<>();
		for (int i = 1; i <= columnCount; i++) {
			String key = metaData.getColumnName(i);
			Object value = resultSet.getObject(i);
			map.put(key, value);
		}
		return map;
	}

	private int calculateTotal() throws Exception, SQLException {
		ResultSet totalResultSet = getResultSet("SELECT COUNT(*) FROM actor;");
		totalResultSet.last();
		int total = totalResultSet.getInt(1);
		return total;
	}
	
	public ResultSet getResultSet(String query) throws Exception{
		ResultSet resultSet = null;
		if (jdbc.isConnected()) {
			resultSet = jdbc.getResultSet(query);
		}else{
			jdbc.openConnect();
			resultSet = jdbc.getResultSet(query);
		}		
		return resultSet;
	}

}
