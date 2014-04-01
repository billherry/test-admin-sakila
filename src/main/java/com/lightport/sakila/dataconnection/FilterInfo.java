package com.lightport.sakila.dataconnection;

import java.sql.PreparedStatement;

public class FilterInfo {
	public String getColumn() {
		return column;
	}

	public String getComparsion() {
		return comparsion;
	}

	public String getValue() {
		return value;
	}

	private String column;
	private String type;
	private String comparsion;
	private String value;

	public FilterInfo(String name,String type, String comparsion, String value) {
		this.column = name;
		this.type = type;
		this.comparsion = comparsion;
		this.value = value;
	}
	public void setParameter(PreparedStatement preparedStatement, int position) throws Exception {
		if (this.type.equals("string")) {
			preparedStatement.setString(position, this.value);
		}
 else if (this.type.equals("numeric")) {
			preparedStatement.setInt(position, Integer.parseInt(this.value));
		}
 else if (this.type.equals("date")) {
			//
		}

	}
}
