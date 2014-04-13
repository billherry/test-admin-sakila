package com.lightport.sakila.business;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

public class UpdateActor {
	private static Log logger;
	
	private static void invokeUpdateQuery(Connection connection, String setDeleteQuery,int id) throws Exception {
		
	}

	private static String setUpdateQuery( Map<String, String> parameterMap) {
		StringBuilder updateQuery= new StringBuilder("UPDATE actor SET");
		
		return updateQuery.toString();
	}


	public static void Invoke(Connection connection, String[] parameterValues) {
		Map<String,String> parameterMap = setMap(parameterValues);
		setUpdateQuery(parameterMap);
	}

	private static HashMap<String, String> setMap(String[] parameterValues) {
		
		return null;
	}
}
