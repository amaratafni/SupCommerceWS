package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.supinfo.supcommercews.dao.DaoFactory;

/**
 * Servlet implementation class RemoveProductServlet
 */
public class RemoveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveProductServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	super.doGet(req, resp);
    }

	/**
	 * Remove a product
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Key id = KeyFactory.stringToKey(request.getParameter("id"));
		DaoFactory.getProductDao().removeProduct(id);
		
		response.sendRedirect("../listProduct");
	}

}
