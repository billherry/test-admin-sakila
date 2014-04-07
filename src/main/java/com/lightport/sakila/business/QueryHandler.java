package com.lightport.sakila.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lightport.sakila.dataconnection.FilterInfo;
import com.lightport.sakila.dataconnection.JdbcHelper;

public class QueryHandler {

	private Log log;
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
	private Set<String> columns;

	public QueryHandler(Map<String, String> map, Connection connection, String tableName, List<FilterInfo> filters)
			throws Exception {

		this.connection = connection;
		this.filters = filters;
		this.tableName = tableName;
		this.start = map.get("start");
		this.limit = map.get("limit");
		this.sortColumn = map.get("sort");
		this.sortDirection = map.get("dir");

		initColumns();
		buildQueryStart();
		queryParser();
	}

	private void initColumns() throws Exception {
		columns = JdbcHelper.getColumnNameList(tableName);
		log = LogFactory.getLog(Class.class);
		log.info(this.columns);
	}

	private void buildQueryStart() {
		selectBuilder.append((String.format(" SELECT * FROM %s ", this.tableName)));
		countBuilder.append((String.format(" SELECT COUNT(*) FROM %s ", this.tableName)));
	}

	private void queryParser() throws Exception {
			validWhere();
			validOrdeyBy();
			validLimitOffset();
	}

	private void validWhere() {
		if (!filters.isEmpty()) {
			addWhereToBuilder(countBuilder);
			addWhereToBuilder(selectBuilder);
		}
	}

	private void validLimitOffset() {
		int limit = Integer.parseInt(this.limit);
		int start = Integer.parseInt(this.start);
		if (limit > 0 && start >= 0) {
			selectBuilder.append(String.format("LIMIT %s , %s ", start, limit));
		}
	}

	private void validOrdeyBy() throws Exception {
		if (columns.contains(this.sortColumn)) {
			if (sortDirection.equals("DESC")) {
				selectBuilder.append(String.format("ORDER BY %s %s ", sortColumn, sortDirection));
			} else {
				selectBuilder.append(String.format("ORDER BY %s ASC ", sortColumn));
			}
		}
 else if (sortColumn != null && !sortColumn.isEmpty()) {
			IllegalArgumentException exception = new IllegalArgumentException();
			throw exception;
		}
	}

	private void addWhereToBuilder(StringBuilder builder) {
		StringBuilder whereString = new StringBuilder();
		whereString.append("WHERE ");
		List<String> list = new ArrayList<>();
		for (FilterInfo info : filters) {
			list.add(String.format(" %s %s ? ", info.getColumn(), info.getComparsion()));
		}
		String join = StringUtils.join(list, " AND ");
		whereString.append(join);
		builder.append(whereString.toString());
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
