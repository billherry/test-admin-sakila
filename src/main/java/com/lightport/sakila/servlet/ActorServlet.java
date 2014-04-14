package com.lightport.sakila.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.lightport.sakila.business.DeleteActor;
import com.lightport.sakila.business.UpdateActor;
import com.lightport.sakila.dataconnection.ActorHandler;
import com.lightport.sakila.dataconnection.FilterInfo;
import com.lightport.sakila.dataconnection.JdbcHelper;

@WebServlet("/ActorServlet")
public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			String initParameter = config.getServletContext().getInitParameter("datasource");
			JdbcHelper.initDataSource(initParameter);
			JdbcHelper.addTableColumns("actor");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		String out = "";
		try {
			DataSource dataSource = JdbcHelper.getDataSource();
			Connection connection = dataSource.getConnection();

			// query, select, insert, update, delete
			String action = request.getPathInfo().replace("/", "");
			if (action != null && !action.isEmpty()) {
				runDmlQuery(action, connection, request);
			}

			ActorRequestContext actorRequestContext = new ActorRequestContext(request);
			Map<String, String> parametersMap = actorRequestContext.getRequestParameters();
			List<FilterInfo> filters = actorRequestContext.getFilters();

			ActorHandler actorHandler = new ActorHandler(parametersMap, filters, connection);
			jsonObject = actorHandler.getJsonObject();
			out = jsonObject.toString();

		} catch (Exception e) {
			Log log = LogFactory.getLog(Class.class);
			log.error("Error occurred: ", e);
			writeException(response, e);
		}
		writeResponse(out, response);
	}

	private void runDmlQuery(String action, Connection connection, HttpServletRequest request) throws Exception {
		if (action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("data"));
			DeleteActor.Invoke(id, connection);
		} else if (action.equals("update")) {
			JSONObject jsonParameters = new JSONObject(request.getParameter("data"));
			System.out.println("JsonData: " + jsonParameters);
			UpdateActor.Invoke(connection, request.getParameter("data"));
		}
	}

	private void writeException(HttpServletResponse response, Exception e) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		Map<String, Object> map = setExceptionMap(e);
		writeResponse(JSONObject.valueToString(map), response);
	}

	private Map<String, Object> setExceptionMap(Exception e) {
		Map<String, Object> map = new HashMap<>();
		map.put("exception", e.getMessage());
		map.put("class", e.getClass().getCanonicalName());

		List<String> stackList = new ArrayList<>();
		e.getStackTrace();
		for (StackTraceElement stackLine : e.getStackTrace()) {
			stackList.add(String.format(" %s: %s (%s)", stackLine.getClassName(), stackLine.getMethodName(),
					stackLine.getLineNumber()));
		}
		map.put("stack", stackList);
		return map;
	}

	private void writeResponse(String out, HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			writer.println(out);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			Log log = LogFactory.getLog(Class.class);
			log.error("Response stream failure. ", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}
}
