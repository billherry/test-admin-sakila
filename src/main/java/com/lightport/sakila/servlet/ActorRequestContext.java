package com.lightport.sakila.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.lightport.sakila.dataconnection.FilterInfo;

public class ActorRequestContext {

	private Map<String, String[]> requestMap;
	private Map<String, String> paramsMap;
	List<FilterInfo> filters;

	public ActorRequestContext(HttpServletRequest request) throws Exception {
		filters = new ArrayList<>();
		paramsMap = new HashMap<String, String>();
		requestMap = request.getParameterMap();
		initParams();
		appendWhereQuery(0);
	}

	public List<FilterInfo> getFilters() {
		return this.filters;
	}

	public Map<String, String> getRequestParameters() {
		return this.paramsMap;
	}

	private void initParams() {
		for (Entry<String, String[]> paramsArray : requestMap.entrySet()) {
			String key = paramsArray.getKey();
			String value = paramsArray.getValue()[0];
			paramsMap.put(key, value);
		}
	}

	private void appendWhereQuery(int idx) {
		String format = String.format("filter[%s]", idx);
		String name = paramsMap.get(String.format("%s[field]", format));
		if (name != null && !name.isEmpty()) {
			FilterInfo filterInfo = getFilter(format);
			filters.add(filterInfo);
			appendWhereQuery(++idx);
		}
	}

	private FilterInfo getFilter(String format) {
		String name = paramsMap.get(String.format("%s[field]", format));
		String type = paramsMap.get(String.format("%s[data][type]", format));
		String compValue = paramsMap.get(String.format("%s[data][comparison]", format));
		String comparsion = setFilterCompararion(compValue);
		String value = paramsMap.get(String.format("%s[data][value]", format));
		FilterInfo info = new FilterInfo(name, type, comparsion, value);
		return info;
	}

	private String setFilterCompararion(String compValue) {
		if (compValue == null) {
			return " LIKE ";
		} else if (compValue.equals("eq")) {
			return " = ";
		} else if (compValue.equals("gt")) {
			return " > ";
		} else {
			return " < ";
		}
	}
}
