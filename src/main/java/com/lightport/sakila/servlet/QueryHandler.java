package com.lightport.sakila.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class QueryHandler {

	private List<FilterInfo> filters;
	private String limit;
	private String start;
	private String table;
	private String columnNameForSorting;
	private String dirSorting;
	private String returnQuery;

	public QueryHandler(HttpServletRequest request, String table) {
		limit = request.getParameter("limit");
		start = request.getParameter("start");
		columnNameForSorting = request.getParameter("sort");
		dirSorting = request.getParameter("dir");
		returnQuery = "SELECT * FROM " + table + " ";
		this.table = table;
		addFilter(request);

		if (columnNameForSorting != null && dirSorting != null)
			addSortingToQuery();
		addPagingQuery();
	}

	private void addSortingToQuery() {
		String sotring = String.format(" ORDER BY %s %s ", columnNameForSorting, dirSorting);
		returnQuery += sotring;
	}

	private void addFilter(HttpServletRequest request) {

	}
	
	private void addPagingQuery() {
		String paging = String.format("LIMIT %s,%s;", start, limit);
		returnQuery += paging;
	}

	public String getTotalCountQuery() {
		String ret = String.format("SELECT COUNT(*) FROM %s;", table);
		return ret;
	}
	
	public String getQueryString() {
		System.out.println(returnQuery);
		return returnQuery;
	}
}
