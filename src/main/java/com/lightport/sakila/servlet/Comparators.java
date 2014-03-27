package com.lightport.sakila.servlet;

public enum Comparators{
	EQUALS(" = "),
	GREATER_THAN(" > "),
	LESS_THAN(" < "),
	STRING(" LIKE ");
	
	private Comparators(final String text)
	{
		this.text = text;
	}
	
	private final String text;
	
	public String toString(){
		return text;
	}
}
