package com.Daniel.test;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/divide")
public class InputServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		try {
		String x = request.getParameter("a");
		String y = request.getParameter("b");
		int a = Integer.parseInt(x);
		int b = Integer.parseInt(y);
		int result = a/b;
		out.println("<h2>Result: "+result+"</h2>");
		}catch (ArithmeticException e){
			out.println("<h3>Cannot print by Zero</h3>");
		}catch(NumberFormatException e) {
			out.println("<h3>Not a number</h3>");
		}catch (Exception e) {
			out.println("<h3>Unknown Excption</h3>");
		}
		
		//doGet(request, response);
	}

}
