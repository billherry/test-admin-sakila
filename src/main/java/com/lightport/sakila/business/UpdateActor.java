package com.lightport.sakila.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class UpdateActor {
	private static Log logger;
	private static StringBuilder updateQuery;

	private static void invokeUpdateQuery(Connection connection, int id, Map<String, String> map) throws Exception {

		PreparedStatement prepareStatement = connection.prepareStatement(updateQuery.toString());
		int idx = 1;
		for (Entry<String, String> entry : map.entrySet()) {
			if (!entry.getKey().equals("actor_id")) {
				prepareStatement.setString(idx, entry.getKey());
				idx++;
				prepareStatement.setString(idx, entry.getValue());
				idx++;
			}
		}
		System.out.println("Prep Statemana" + prepareStatement);
	}

	private static void setUpdateQuery(Map<String, String> parameterMap) {
		updateQuery = new StringBuilder(" UPDATE actor SET ");

		parameterMap.size();
		List<String> parameterSetQuery = new ArrayList<>();
		for (int i = 0; i < parameterMap.size() - 1; i++) {
			parameterSetQuery.add("? = ? ");
		}
		String join = StringUtils.join(parameterSetQuery, " , ");
		updateQuery.append(join);
		updateQuery.append(" WHERE actor_id = ? ");
		System.out.println(updateQuery.toString());
		updateQuery.toString();
	}

	public static void Invoke(Connection connection, String jsonParameters) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			HashMap map = mapper.readValue(jsonParameters, new TypeReference<HashMap<String, String>>() {
			});
			setUpdateQuery(map);
			invokeUpdateQuery(connection, map);
		} catch (Exception e) {
			Log log = LogFactory.getLog(Class.class);
			log.error("Wrong json parameters name: data" + e.getMessage());
		}

	}
}
