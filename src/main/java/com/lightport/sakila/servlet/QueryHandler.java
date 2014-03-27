package com.lightport.sakila.servlet;

import java.util.List;
import java.util.Map;

public class QueryHandler {

	private List<FilterInfo> filters;
	private String limit;
	private String start;
	private String table;

	public QueryHandler(Map<String, String[]> parameterMap, String table) {
		limit = parameterMap.get("limit")[0];
		start = parameterMap.get("start")[0];
		this.table = table;
	}

	private void addFilter(FilterInfo filterInfo) {
		this.filters.add(filterInfo);
	}
	
	public String getQueryString() {
		String ret = String.format("SELECT * FROM %s LIMIT %s,%s ", table, start, limit);
		return ret;
	}

	public String getTotalCountQuery() {
		String ret = String.format("SELECT COUNT(*) FROM %s;", table);
		return ret;
	}
}
