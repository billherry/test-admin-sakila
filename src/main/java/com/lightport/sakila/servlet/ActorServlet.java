package com.lightport.sakila.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		int pageLimit = Integer.parseInt(request.getParameter("limit"));
		int pageStart = Integer.parseInt(request.getParameter("start"));
		String column = request.getParameter("sort");
		String asc = request.getParameter("dir");

		ActorServletContext actorServletContext = new ActorServletContext(pageLimit, pageStart, column, asc);
		try {
			actorServletContext.createConnection();
		} catch (Exception e) {
			Log log = LogFactory.getLog(Class.class);
			log.error(e.getMessage(), e);
		}
		JSONObject jsonObject = actorServletContext.getJsonObject();

		writer.printf("%s", jsonObject);
		writer.flush();
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

	private void createJsonForResponse() {

	}

}
