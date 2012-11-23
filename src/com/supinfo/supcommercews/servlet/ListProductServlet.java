package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommercews.dao.DaoFactory;

/**
 * Servlet implementation class ListProductServlet
 */
public class ListProductServlet extends HttpServlet {
    
	private static final long serialVersionUID = -3462620582008953418L;

    public ListProductServlet() {
        super();
    }
    
    /**
     * Retrieve the list of products and forward the user to the listProduct JSP file
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("listProducts", DaoFactory.getProductDao().getAllProducts());
    	request.getRequestDispatcher("listProduct.jsp").forward(request, response);
    }
    
    /**
     * Call doGet() method
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
    	doGet(request, response);
    };
}
