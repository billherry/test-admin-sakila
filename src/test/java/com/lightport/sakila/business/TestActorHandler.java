package com.lightport.sakila.business;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestActorHandler {

	@Test
	public void testGetResultSet() throws Exception {
	}

	private void outResultSet(ResultSet rs) throws SQLException {
		while (rs.next()) {
			String em = rs.getString("first_name");
			System.out.println(em);
		}
	}
}
