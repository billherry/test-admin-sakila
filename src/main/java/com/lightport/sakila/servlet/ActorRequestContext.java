package com.lightport.sakila.servlet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class ActorRequestContext {

	private Map<String, String[]> requestMap;
	private Map<String, String> paramsMap;

	public ActorRequestContext(HttpServletRequest request) throws Exception {
		paramsMap = new HashMap<String, String>();
		requestMap = request.getParameterMap();
	}

	public Map<String, String> getRequestParameters() {
		for (Entry<String, String[]> paramsArray : requestMap.entrySet()) {
			String key = paramsArray.getKey();
			String value = paramsArray.getValue()[0];
			paramsMap.put(key, value);
		}
		return this.paramsMap;
	}
}
