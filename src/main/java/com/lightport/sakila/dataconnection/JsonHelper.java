package com.lightport.sakila.dataconnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JsonHelper {
	
	public static JSONObject getJsonObject(ResultSet resultSet) throws Exception {
		JSONObject mapFormresultSet = mapFormresultSet(resultSet);
		return mapFormresultSet;
	}

	private static JSONObject mapFormresultSet(ResultSet resultSet) throws Exception {
		JSONObject jsonObject = new JSONObject();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		ArrayList<Map<String, Object>> list = new ArrayList<>();

		while (resultSet.next()) {
			Map<String, Object> map = mapFromResultSet(columnCount, metaData, resultSet);
			list.add(map);
		}
		jsonObject.put("items", list);
		return jsonObject;
	}
	
	public static void setJsonItemCount(int items, JSONObject jsonObject) {
		jsonObject.put("total", items);
		System.out.println(jsonObject);
	}

	private static Map<String, Object> mapFromResultSet(int columnCount, ResultSetMetaData metaData, ResultSet resultSet)
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
