package com.lightport.sakila.servlet;

import javax.servlet.http.HttpServletRequest;

public class RequestContext {

	private ActorHandler actorhandler;

	public RequestContext(HttpServletRequest request) throws Exception {
		initActorHandler(request);
	}

	private void initActorHandler(HttpServletRequest request) throws Exception {
		actorhandler = new ActorHandler(request);
	}

	public String getResponse() {
		return String.format("%s", actorhandler.getJsonObject());
	}
}
