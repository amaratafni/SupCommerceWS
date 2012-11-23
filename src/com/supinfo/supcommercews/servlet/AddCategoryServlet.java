package com.supinfo.supcommercews.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Category;

/**
 * Servlet implementation class AddCategoryServlet
 */
public class AddCategoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = -3958559221182870031L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryServlet() {
        super();
    }

    
	/**
	 * Forward the jsp file to create a new category
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("addCategory.jsp").forward(request, response);
		response.sendRedirect("addCategory.jsp");
	}

	/**
	 * Persist a new category and redirect the user to the products list
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		
		if(name==null)
			response.sendRedirect("addCategory");
		else {
			Category category = new Category(name);
			DaoFactory.getCategoryDao().addCategory(category);
			
			response.sendRedirect("../listProduct");
//			request.getRequestDispatcher("../listProduct").forward(request, response);
//			getServletContext().getRequestDispatcher("listProduct").forward(request, response);
		}
	}
}
