package com.lightport.sakila.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lightport.sakila.dataconnection.FilterInfo;

public class QueryHandler {

	private String start;
	private String limit;
	private String sortColumn;
	private String sortDirection;
	private String tableName;

	private StringBuilder selectBuilder = new StringBuilder();
	private StringBuilder countBuilder = new StringBuilder();
	private PreparedStatement selectPrepStatement;
	private PreparedStatement countPrepStatement;
	private List<FilterInfo> filters;
	private Connection connection;


	public QueryHandler(Map<String, String> map, Connection connection, String tableName, List<FilterInfo> filters)
			throws Exception {

		this.connection = connection;
		this.filters = filters;
		this.tableName = tableName;
		this.start = map.get("start");
		this.limit = map.get("limit");
		this.sortColumn = map.get("sort");
		this.sortDirection = map.get("dir");

		buildQueryStart();
		queryParser();
	}

	private void buildQueryStart() {
		selectBuilder.append((String.format(" SELECT * FROM %s ", this.tableName)));
		countBuilder.append((String.format(" SELECT COUNT(*) FROM %s ", this.tableName)));
	}

	private void queryParser() throws Exception {
		if (!filters.isEmpty()) {
			addWhereToBuilders(countBuilder);
			addWhereToBuilders(selectBuilder);
		}

		if ((sortColumn != null && !sortColumn.isEmpty()) && (sortDirection != null && !sortDirection.isEmpty())) {
			selectBuilder.append(String.format("ORDER BY %s %s ", sortColumn, sortDirection));
		}

		if ((limit != null && !limit.isEmpty()) && (start != null && !start.isEmpty())) {
			selectBuilder.append(String.format("LIMIT %s , %s ", start, limit));
		}
	}

	private void addWhereToBuilders(StringBuilder builder) {
		StringBuilder whereString = new StringBuilder();
		whereString.append("WHERE ");
		List<String> list = new ArrayList<>();
		for (FilterInfo info : filters) {
			list.add(String.format(" %s %s ? ", info.getColumn(), info.getComparsion()));
		}
		String join = stringJoin(list, " AND ");
		whereString.append(join);
		builder.append(whereString.toString());
	}

	private String stringJoin(List<String> list, String append) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i < list.size() - 1){
				builder.append(String.format("%s %s", list.get(i), append));
			} else {
				builder.append(String.format("%s", list.get(i)));
			}
		}
		return builder.toString();
	}

	public ResultSet getSelectResultSet() throws Exception {
		this.selectPrepStatement = this.connection.prepareStatement(selectBuilder.toString());
		setStatement(this.selectPrepStatement);
		System.out.println(this.selectPrepStatement);
		return this.selectPrepStatement.executeQuery();
	}

	public ResultSet getCountResultSet() throws Exception {
		this.countPrepStatement = this.connection.prepareStatement(this.countBuilder.toString());
		setStatement(this.countPrepStatement);
		System.out.println(this.countPrepStatement);
		return countPrepStatement.executeQuery();
	}

	private void setStatement(PreparedStatement statement) throws Exception {
		for (FilterInfo info : this.filters) {
			info.setParameter(statement, filters.indexOf(info) + 1);
		}
	}
}
