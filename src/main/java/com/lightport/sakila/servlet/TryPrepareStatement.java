package com.lightport.sakila.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lightport.sakila.dataconnection.JdbcHelper;

public class TryPrepareStatement {

	PreparedStatement selectActors = null;
	private Connection connection;
	private String tableName;
	private String selectQuery;

	public void Invoke(String tableName) throws Exception {
		this.tableName = tableName;
		JdbcHelper jdbcHelper = new JdbcHelper();
		jdbcHelper.openConnect();
		connection = jdbcHelper.getConection();
	}

	public ResultSet selectSatement(int start, int limit) throws Exception {
		selectQuery = String.format("SELECT * FROM %s ", this.tableName);
		selectActors = connection.prepareStatement(selectQuery);
		if (start >= 0 && limit > 0)
			createLimitedResultset(start, limit);
		return selectActors.executeQuery();
	}

	private void createLimitedResultset(int start, int limit) throws Exception {
		selectActors = selectActors.getConnection().prepareStatement(selectQuery + "LIMIT ? , ?");
		selectActors.setInt(1, start);
		selectActors.setInt(2, limit);
	}
	
	public ResultSet createFilterdResultset() {
		return null;
	}
}
