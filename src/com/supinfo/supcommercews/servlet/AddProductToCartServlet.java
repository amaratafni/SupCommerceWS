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

public class AddProductToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 2092312773974642455L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* On recupere la liste des produits dans le panier */
		ArrayList<Product> cart = (ArrayList<Product>)req.getSession().getAttribute("shoppingCart");
		if(cart==null)
			cart = new ArrayList<Product>();
		
		try {
			Key key = KeyFactory.stringToKey(req.getParameter("id"));

			cart.add(DaoFactory.getProductDao().findProduct(key));
			req.getSession().setAttribute("shoppingCart", cart);
			System.out.println("cart: "+cart.size());
		} catch (IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
		}
		
		resp.sendRedirect("listProduct");
		
	}
}
