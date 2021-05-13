package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class CustomerAPI
 */
@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static customer CustomerObj;

    /**
     * Default constructor. 
     */
    public CustomerAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerObj = new customer();
		System.out.println("ereerer");
		String output = CustomerObj.insertCustomer(request.getParameter("name"),
				request.getParameter("address"),
				request.getParameter("phonenumber"),
				request.getParameter("email"),
				request.getParameter("occupation"),
				request.getParameter("needproduct"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 

	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerObj = new customer();
		
		Map paras = getParasMap(request);
		String output = CustomerObj.updateCustomer(paras.get("hidItemIDSave").toString(),
				 
		paras.get("name").toString(),
		paras.get("address").toString(),
		paras.get("phonenumber").toString(),
		paras.get("email").toString(),
		paras.get("occupation").toString(),
		paras.get("needproduct").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dsdsd");
		CustomerObj = new customer();
		Map paras = getParasMap(request);
		 String output = CustomerObj.deleteCustomer(Integer.parseInt(paras.get("id").toString()));
		response.getWriter().write(output); 
	}

}
