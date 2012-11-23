package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Product;

/**
 * Servlet implementation class InsertSomeProductServlet
 */
public class AddProductServlet extends HttpServlet {
    
	private static final long serialVersionUID = 4498170929371810655L;

	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setAttribute("categories", DaoFactory.getCategoryDao().getAllCategories());
    	req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }
    
    /**
     * Persist a new product
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try {
	    	Product product = new Product(request.getParameter("name"), request.getParameter("content"), new BigDecimal(request.getParameter("price")));
	    	product.setCategory(DaoFactory.getCategoryDao().findCategory(KeyFactory.stringToKey(request.getParameter("category"))));
	    	
	    	DaoFactory.getProductDao().addProduct(product);
	    	resp.sendRedirect("../listProduct");
    	} catch(NumberFormatException ex) {
    		System.err.println(ex.getMessage());
    		resp.sendRedirect("addProduct");
    	}
    	
    }
}
