package com.lightport.sakila.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.dataconnection.JsonHelper;

/**
 * Servlet implementation class ActorServlet
 */
@WebServlet("/ActorServlet")
public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(Class.class);
	private  int pageLimit;
	private  int pageStart;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		pageLimit = Integer.parseInt(request.getParameter("limit"));
		pageStart = Integer.parseInt(request.getParameter("start"));
		log.info("limit"+pageLimit);
		log.info("start"+pageStart);
		
		try {
			
			JdbcHelper.openConnect();
			JsonHelper jsonHelper = new JsonHelper();
			JSONObject jsonObject = jsonHelper
					.getJsonObject("SELECT * FROM actor LIMIT "+pageStart+","+pageLimit+")");
			//JSONObject jsonObject2 = jsonHelper.getJsonObject("select * from actor where actor_id = 42");	
			writer.printf("%s", jsonObject);
		} catch (Exception e) {			
			log.error(e.getMessage(),e);
		}
		writer.flush();
		writer.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
	}

}
