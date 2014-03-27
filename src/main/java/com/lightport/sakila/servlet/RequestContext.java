package com.lightport.sakila.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestContext {

	private HttpServletRequest request;
	private ActorHandler actorhandler;

	public RequestContext(HttpServletRequest request) throws Exception {
		this.request = request;
		Map<String, String[]> parameterMap = request.getParameterMap();
		initActorHandler(parameterMap);
	}

	public void Invoke() throws Exception {
	}

	private void initActorHandler(Map<String, String[]> parameterMap) throws Exception {
		actorhandler = new ActorHandler(parameterMap);
	}

	public String getResponse() {
		return String.format("%s", actorhandler.getJsonObject());
	}
}
