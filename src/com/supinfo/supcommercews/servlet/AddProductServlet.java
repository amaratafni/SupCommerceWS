package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Product;

/**
 * Servlet implementation class InsertSomeProductServlet
 */
public class AddProductServlet extends HttpServlet {
    
	private static final long serialVersionUID = 4498170929371810655L;

    public AddProductServlet() {
        super();
    }
    
    /**
     * Get all categories and forward the user to ths addProduct JSP file
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setAttribute("categories", DaoFactory.getCategoryDao().getAllCategories());
    	req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }
    
    /**
     * Persist a new product and redirect the user to the list of products
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
    	
    	Product product = new Product(request.getParameter("name"), request.getParameter("content"), new BigDecimal(request.getParameter("price")));
    	product.setCategory(DaoFactory.getCategoryDao().findCategory(KeyFactory.stringToKey(request.getParameter("category"))));
    	
    	DaoFactory.getProductDao().addProduct(product);
    	resp.sendRedirect("../listProduct");
    	
    }
}
