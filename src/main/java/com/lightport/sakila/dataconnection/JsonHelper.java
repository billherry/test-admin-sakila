package com.lightport.sakila.dataconnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class JsonHelper {

	public JSONObject getJsonObject(String query) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		ResultSet resultSet = JdbcHelper.getResultSet(query);
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();		

		HashMap<String, Object> map;
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();

		while (resultSet.next()) {
			map = new HashMap<>();
			for (int i = 1; i <= columnCount; i++) {
				String key = metaData.getColumnName(i);
				Object value = resultSet.getObject(i);
				map.put(key, value);
			}
			list.add(map);
		}
		
		ResultSet totalResultSet = getResultSet("SELECT COUNT(*) FROM actor;");
		totalResultSet.last();
		int total = totalResultSet.getInt(1);
		
		jsonObject .put("total", total);
		jsonObject.put("actors", list);

		return jsonObject;
	}
	
	public ResultSet getResultSet(String query) throws Exception{
		ResultSet resultSet = null;
		if(JdbcHelper.isConnected()) {
			resultSet = JdbcHelper.getResultSet(query);
		}else{
			JdbcHelper.openConnect();
			resultSet = JdbcHelper.getResultSet(query);
		}		
		return resultSet;
	}

}
