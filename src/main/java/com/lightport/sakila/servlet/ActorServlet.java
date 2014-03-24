package com.lightport.sakila.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.lightport.sakila.dataconnection.JdbcHelper;
import com.lightport.sakila.dataconnection.JsonHelper;

/**
 * Servlet implementation class ActorServlet
 */
@WebServlet("/ActorServlet")
public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		JdbcHelper.openConnect();
		JsonHelper jsonHelper = new JsonHelper();
		JSONObject jsonObject = jsonHelper .getJsonObject("select *from actor");
		//JSONObject jsonObject2 = jsonHelper .getJsonObject("select * from actor where actor_id = 42");
		PrintWriter writer = response.getWriter();	
		
		writer.printf("%s",jsonObject);		
		writer.flush();
		writer.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
	}

}
