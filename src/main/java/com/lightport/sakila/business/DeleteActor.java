package com.lightport.sakila.business;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeleteActor {
	
	private static Log logger;

	private static void invokeDeleteQuery(Connection connection, String setDeleteQuery,int id) throws Exception {
		PreparedStatement prepareStatement = connection.prepareStatement(setDeleteQuery);		
		prepareStatement.setInt(1, id);
		logger.info(prepareStatement);
		prepareStatement.execute();
	}

	private static String setDeleteQuery() {
		return "DELETE FROM actor WHERE actor_id = ?";
	}

	public static void Invoke(int id, Connection connection) throws Exception {
		logger = LogFactory.getLog(Class.class);
		String setDeleteQuery = setDeleteQuery();
		invokeDeleteQuery(connection,setDeleteQuery, id);
	}
	
}
