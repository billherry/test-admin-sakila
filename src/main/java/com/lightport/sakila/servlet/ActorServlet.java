package com.lightport.sakila.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.catalina.connector.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

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
			String initParameter = config.getServletContext().getInitParameter("SakilaContextParam");
			JdbcHelper.initDataSource(initParameter);
			JdbcHelper.addTableColumns("actor");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DataSource dataSource = JdbcHelper.getDataSource();
			Connection connection = dataSource.getConnection();

			ActorRequestContext actorRequestContext = new ActorRequestContext(request);
			Map<String, String> parametersMap = actorRequestContext.getRequestParameters();
			List<FilterInfo> filters = actorRequestContext.getFilters();

			ActorHandler actorHandler = new ActorHandler(parametersMap, filters, connection);
			JSONObject jsonObject = actorHandler.getJsonObject();
			writeResponse(jsonObject.toString(), response.getWriter());

		} catch (JSONException e) {
			Log log = LogFactory.getLog(Class.class);
			log.error("JsonParseError" + e.getMessage());
			writeException(response.getWriter(), e);
		} catch (Exception e) {
			Log log = LogFactory.getLog(Class.class);
			log.error("DatabaseError" + e.getMessage());
			writeException(response.getWriter(), e);
		}
	}

	private void writeResponse(String out, PrintWriter writer) {
		try{
			writer.println(out);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			writeException(writer, e);
		}
	}

	private void writeException(PrintWriter writer, Exception e) {
		writer.println(String.format("%s Error:%s", Response.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}
}
