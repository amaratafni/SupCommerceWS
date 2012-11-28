package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommercews.entity.Product;


public class ShowCartServlet extends HttpServlet {

	private static final long serialVersionUID = 2111041775845952537L;

	public ShowCartServlet() {
		super();
	}
	
	/**
	 * Retrieve all products from the shopping cart and calculate the total
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Product> products = (ArrayList<Product>) req.getSession().getAttribute("shoppingCart");
		
		if(products!=null && products.size()>0) {
			BigDecimal sum = BigDecimal.ZERO;

			for(Product p : products)
				sum = sum.add(p.getPrice());

			sum = sum.setScale(2,RoundingMode.HALF_UP);
			req.setAttribute("totalCart", sum);
		}
		
		req.getRequestDispatcher("showCart.jsp").forward(req, resp);
	}
	
	/**
	 * Call doGet() method
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
