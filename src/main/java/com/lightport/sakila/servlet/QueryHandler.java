package com.lightport.sakila.servlet;

import java.util.ArrayList;
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
	private String first_filter;
	private String totalCountQuery;
	private String totalCountQueryPaging;

	public QueryHandler(HttpServletRequest request, String table) {
		limit = request.getParameter("limit");
		start = request.getParameter("start");
		first_filter = request.getParameter("filter[0][field]");
		columnNameForSorting = request.getParameter("sort");
		dirSorting = request.getParameter("dir");
		returnQuery = "SELECT * FROM " + table + " ";
		this.table = table;
		totalCountQueryPaging = "";

		filters = new ArrayList<FilterInfo>();
		if (first_filter != null) {
			addFilter(request, 0);
			addFilterToQuery();
		}

		if (columnNameForSorting != null && dirSorting != null)
			addSortingToQuery();
		addPagingQuery();
	}

	private void addFilterToQuery() {
		totalCountQueryPaging += "WHERE ";
		for (int i = 0; i < filters.size(); i++) {
			totalCountQueryPaging += String.format(" %s %s %s ", filters.get(i).getColumnName(),
					filters.get(i).getComparsion(), filters.get(i).getValue());
			if (i < filters.size() - 1)
				totalCountQueryPaging += "AND ";
		}
		returnQuery += totalCountQueryPaging;
		
		System.out.println(returnQuery);
	}

	private void addSortingToQuery() {
		String sotring = String.format(" ORDER BY %s %s ",
				columnNameForSorting, dirSorting);
		returnQuery += sotring;
	}

	private void addFilter(HttpServletRequest request, int index) {
		String name = request.getParameter("filter[" + index + "][field]");
		if (name != null) {
		String type = request.getParameter("filter[" + index + "][data][type]");
		String value = request.getParameter("filter[" + index
				+ "][data][value]");
		String comparison = request.getParameter("filter[" + index
				+ "][data][comparison]");
		if (type.equals("string"))
			comparison = Comparators.STRING.toString();
		
			FilterInfo filterInfo = new FilterInfo(name, type, comparison,
					value);
			filters.add(filterInfo);
			addFilter(request, ++index);
		}
	}

	private void addPagingQuery() {
		String paging = String.format("LIMIT %s,%s;", start, limit);
		returnQuery += paging;
	}

	public String getTotalCountQuery() {
		totalCountQuery = String.format("SELECT COUNT(*) FROM %s ", table);
		totalCountQuery+=totalCountQueryPaging;
		System.out.println("total: "+totalCountQuery);
		return totalCountQuery;
	}

	public String getQueryString() {
		System.out.println(returnQuery);
		return returnQuery;
	}
}
