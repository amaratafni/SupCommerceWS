package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommercews.entity.Product;

public class EmptyCartServlet extends HttpServlet {

	private static final long serialVersionUID = 7867262617635096724L;

	/**
	 * Remove all products of the shopping cart
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.getSession().setAttribute("shoppingCart", new ArrayList<Product>());
		resp.sendRedirect("showCart");
	}
	
	/**
	 * Call doGet() method
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		doGet(req, resp);
	};
}
