package com.lightport.sakila.dataconnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class JsonHelper {
	
	JSONObject jsonObject;

	public JSONObject getJsonObject(ResultSet resultSet) throws Exception {
		initJsonObject(resultSet);
		return this.jsonObject;
	}

	private void initJsonObject(ResultSet resultSet) throws Exception {

		jsonObject = new JSONObject();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();

		while (resultSet.next()) {
			HashMap<String, Object> map = mapFromResultSet(columnCount, metaData, resultSet);
			list.add(map);
		}
		jsonObject.put("items", list);
	}

	private HashMap<String, Object> mapFromResultSet(int columnCount, ResultSetMetaData metaData, ResultSet resultSet)
			throws SQLException {
		HashMap<String, Object> map = new HashMap<>();
		for (int i = 1; i <= columnCount; i++) {
			String key = metaData.getColumnName(i);
			Object value = resultSet.getObject(i);
			map.put(key, value);
		}
		return map;
	}
}
