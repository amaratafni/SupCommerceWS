package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
       
	private static final long serialVersionUID = -3058703981573443565L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
    /**
     * Forward to the jsp file to log in
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * Add the username to the session scope and redirect the user to the products' list jsp file
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("admin") && password.equals("@dm1n89")) {
			request.getSession().setAttribute("username", username);
//			request.getRequestDispatcher("/listProduct").forward(request, response);
			response.sendRedirect("listProduct");
		} else {
			response.sendRedirect("login");
		}
		
	}

}
