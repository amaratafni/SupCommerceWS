package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Product;

/**
 * Servlet implementation class ShowProductServlet
 */
public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShowProductServlet() {
        super();
    }

	/**
	 * Show the details of a product
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Key id = KeyFactory.stringToKey(request.getParameter("id"));
			
			Product product = DaoFactory.getProductDao().findProduct(id);
			
			if(product==null)
				response.sendRedirect("/listProduct");		
			else {
				request.setAttribute("product", product);
				request.getRequestDispatcher("showProduct.jsp").forward(request, response);
			}
		} catch(IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
			response.sendRedirect("/listProduct");	
		}
		
	}

}
