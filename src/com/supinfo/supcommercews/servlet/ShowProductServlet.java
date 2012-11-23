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
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Key id = KeyFactory.stringToKey(request.getParameter("id"));
		if(id==null)
			id = (Key) request.getAttribute("id");
		
		Product product = DaoFactory.getProductDao().findProduct(id);
		
		if(product==null)
			response.sendRedirect("/listProduct");		
		else {
//			System.out.println(product.getPrice().floatValue());
//			System.out.println(product.getPrice());
			request.setAttribute("product", product);
			request.getRequestDispatcher("showProduct.jsp").forward(request, response);
		}
		
	}

}
