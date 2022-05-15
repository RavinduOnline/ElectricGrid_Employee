package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;

import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class EmployeeAPI
 */
@WebServlet("/EmployeeAPI")
public class EmployeeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmployeeAPI() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Employee employeeObj = new Employee(); 

		String output = employeeObj.insertEmployee(request.getParameter("employeeCode"), 
								request.getParameter("employeeName"), 
								request.getParameter("employeeAddress"), 
								request.getParameter("employeeNIC"), 
								request.getParameter("employeeEmail"),
								request.getParameter("employeePNO")); 
		
		response.getWriter().write(output); 
				
	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) { 
		
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : ""; 
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
		
		Employee employeeObj = new Employee(); 
		
		Map paras = getParasMap(request); 
		
		String output = employeeObj.updateEmployee(paras.get("hidEmployeeIDSave").toString(), 
											paras.get("employeeCode").toString(), 
											paras.get("employeeName").toString(), 
											paras.get("employeeAddress").toString(), 
											paras.get("employeeNIC").toString(), 
											paras.get("employeeEmail").toString(),
											paras.get("employeePNO").toString()); 
		
		response.getWriter().write(output); 
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Employee employeeObj = new Employee(); 
		
		Map paras = getParasMap(request); 
		
		String output = employeeObj.deleteEmployee(paras.get("eID").toString()); 
		
		
		response.getWriter().write(output); 
		
	}

}
