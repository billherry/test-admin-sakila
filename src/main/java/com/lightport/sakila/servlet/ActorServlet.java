package com.lightport.sakila.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

@WebServlet("/ActorServlet")
public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			ActorRequestContext actorRequestContext = new ActorRequestContext(request);
			Map<String, String> parametersMap = actorRequestContext.getRequestParameters();
			List<FilterInfo> filters = actorRequestContext.getFilters();
			ActorHandler actorHandler = new ActorHandler(parametersMap, filters);
			JSONObject jsonObject = actorHandler.getJsonObject();
			writer.println(jsonObject);

			writer.flush();
			writer.close();

		} catch (Exception e) {
			Log log = LogFactory.getLog(Class.class);
			log.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}
}
