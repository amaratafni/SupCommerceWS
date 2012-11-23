package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommercews.entity.Product;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
       
	private static final long serialVersionUID = -3058703981573443565L;

    public LoginServlet() {
        super();
    }
    
    /**
     * Redirect the user to the login JSP file
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * Check if the login informations are corrects.
	 * If they are, redirect the user to the listProduct JSP file, else redirect the user to the login page
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("admin") && password.equals("@dm1n89")) {
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("shoppingCart", new ArrayList<Product>());
			response.sendRedirect("listProduct");
		} else {
			response.sendRedirect("login");
		}
		
	}

}
