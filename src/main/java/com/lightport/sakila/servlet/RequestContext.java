package com.lightport.sakila.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestContext {

	private ActorHandler actorhandler;

	public RequestContext(HttpServletRequest request) throws Exception {
		Map<String, String[]> parameterMap = request.getParameterMap();
		initActorHandler(parameterMap);
	}

	private void initActorHandler(Map<String, String[]> parameterMap) throws Exception {
		actorhandler = new ActorHandler(parameterMap);
	}

	public String getResponse() {
		return String.format("%s", actorhandler.getJsonObject());
	}
}
