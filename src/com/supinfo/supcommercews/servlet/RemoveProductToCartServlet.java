package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Product;

public class RemoveProductToCartServlet extends HttpServlet {

	private static final long serialVersionUID = -3055260820113484333L;

	/**
	 * Remove a product from the shopping cart
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException ,IOException {
		try {
			Key productKey = KeyFactory.stringToKey((String)request.getParameter("id"));
			Product product = DaoFactory.getProductDao().findProduct(productKey);
			
			if(product!=null) {
				ArrayList<Product> cart = (ArrayList<Product>) request.getSession().getAttribute("shoppingCart");
				if(cart!=null)
					System.out.println(cart.remove(product));
			}
				
		} catch(IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
		}
		
		resp.sendRedirect("showCart");
	};
	
	/**
	 * Call doGet() method
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doGet(request, resp);
	}
}
