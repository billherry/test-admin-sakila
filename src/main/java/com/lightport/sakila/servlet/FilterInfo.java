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
	public String getColumnName() {
		return column;
	}

	public String getType() {
		return type;
	}

	public String getComparsion() {
		if(comparsion.equals("gt"))
			comparsion = Comparators.GREATER_THAN.toString();
		else if(comparsion.equals("lt"))
			comparsion = Comparators.LESS_THAN.toString();
		else if (comparsion.equals("eq"))
			comparsion = Comparators.EQUALS.toString();
		
		return comparsion;
	}

	public String getValue() {		
		if(type.equals("string"))
		this.value = "'"+this.value+"'";
		
		return this.value;
	}
}
