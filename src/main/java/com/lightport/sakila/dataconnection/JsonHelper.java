package com.lightport.sakila.dataconnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;


public class JsonHelper {

	public JSONObject getJsonObject(String query) {		
		JSONObject jsonObject = new JSONObject();
		try {
			
			JdbcHelper jdbcHelper = new JdbcHelper();
			ResultSet resultSet = jdbcHelper.getResultSet(query);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			HashMap<String, Object> map;
			ArrayList<HashMap<String, Object>> list = new ArrayList<>();
			
			while(resultSet.next()){
				 map = new HashMap<>();
				 for (int i = 1; i < columnCount; i++) {
					String key = metaData.getColumnName(i);
					Object value = resultSet.getObject(i);
					map.put(key, value);
				}
				 list.add(map);
			}			
			jsonObject.put("actors", list);		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return jsonObject;
	}

}
