package com.lightport.sakila.servlet;

public class FilterInfo {
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
	// where feltételnek megfelelő szerkezet Pl: actor_id < 50
	public String toString() {
		return String.format("%s %s '%s' ", this.column, this.comparsion, this.value);
	}
}
