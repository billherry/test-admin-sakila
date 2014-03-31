package com.lightport.sakila.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.lightport.sakila.servlet.FilterInfo;

public class QueryHandler {

	private String start;
	private String limit;
	private String sortColumn;
	private String sortDirection;
	StringBuilder selectBuilder;
	String countQuery;
	PreparedStatement prepStatment;
	private String tableName;
	private List<FilterInfo> filters;
	private String firstFilter;
	private StringBuilder whereBuilder;

	public QueryHandler(Map<String, String> map, Connection connection, String tableName, List<FilterInfo> filters)
			throws Exception {

		this.filters = filters;
		this.tableName = tableName;
		start = map.get("start");
		limit = map.get("limit");
		sortColumn = map.get("sort");
		sortDirection = map.get("dir");
		firstFilter = map.get("filter[0][field]");

		selectBuilder = new StringBuilder();
		whereBuilder = new StringBuilder();
		

		buildQueryStart();
		specializationSelectQuery();
		prepStatment = connection.prepareStatement(this.selectBuilder.toString());
		System.out.println("prepSt: " + prepStatment);
	}

	private void specializationSelectQuery () throws Exception {
		if (firstFilter != null && !firstFilter.isEmpty()) {
			addWhereToQuery();
		}

		if ((sortColumn != null && !sortColumn.isEmpty()) && (sortDirection != null && !sortDirection.isEmpty())) {
			selectBuilder.append(String.format("ORDER BY %s %s ", sortColumn, sortDirection));
		}

		if ((limit != null && !limit.isEmpty()) && (start != null && !start.isEmpty())) {
			selectBuilder.append(String.format("LIMIT %s , %s ", start, limit));
		}
	}

	private void addWhereToQuery() {
		StringBuilder whereString = new StringBuilder();
		whereString.append("WHERE ");
		List<String> list = new ArrayList<String>();
		for (FilterInfo info : filters) {
			list.add("? ? ?");
		}
		String join = StringUtils.join(list, " AND ");
		whereString.append(join);
		this.whereBuilder.append(whereString.toString());
		this.selectBuilder.append(whereString.toString());
	}

	private void buildQueryStart() {
		selectBuilder.append((String.format(" SELECT * FROM %s ", this.tableName)));
		whereBuilder.append((String.format(" SELECT COUNT(*) FROM %s ", this.tableName)));
	}

	public String getCountQuery() {
		return this.whereBuilder.toString();
	}

	public String getQuery() {
		return this.selectBuilder.toString();
	}

	public ResultSet getResultSet() throws Exception {
		for (FilterInfo info : this.filters) {
			info.setParameter(prepStatment, filters.indexOf(info)+1);
		}
		return prepStatment.executeQuery();
	}
}
