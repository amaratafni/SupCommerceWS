package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Category;
import com.supinfo.supcommercews.entity.Product;

/**
 * Servlet implementation class UpdateProductServlet
 */
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = DaoFactory.getCategoryDao().getAllCategories();
		Product product = DaoFactory.getProductDao().findProduct(KeyFactory.stringToKey(request.getParameter("id")));

		if(categories!=null && product!=null) {
			request.setAttribute("categories", categories);
			request.setAttribute("product", product);
			request.setAttribute("productKey", KeyFactory.keyToString(product.getId()));
			request.setAttribute("categoryKey", KeyFactory.keyToString(product.getCategory().getId()));
			request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
		} else {
			response.sendRedirect("../listProduct");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			Key id = (Key) request.getAttribute("productId");
			Key id = KeyFactory.stringToKey(request.getParameter("productId"));
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			Key categoryKey = KeyFactory.stringToKey(request.getParameter("category"));
			Category category = DaoFactory.getCategoryDao().findCategory(categoryKey);
			
			Product product = DaoFactory.getProductDao().findProduct(id);
			if(product!=null) {
				product.setName(name);
				product.setContent(content);
				product.setPrice(price);
				product.setCategory(category);
				
				DaoFactory.getProductDao().updateProduct(product);
				
//				request.setAttribute("id", product.getId());
//				request.getRequestDispatcher("../showProduct").forward(request, response);
				response.sendRedirect("../listProduct");
			}
		} catch(NumberFormatException ex) {
			System.err.println(ex.getMessage());
		} catch(IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
		}
		
		
	}

}
