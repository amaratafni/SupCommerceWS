package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.math.BigDecimal;
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
       
    public UpdateProductServlet() {
        super();
    }

	/**
	 * Retrieve all categories and the product to update.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = DaoFactory.getCategoryDao().getAllCategories();
		Product product = DaoFactory.getProductDao().findProduct(KeyFactory.stringToKey(request.getParameter("id")));

		if(categories!=null && product!=null) {
			request.setAttribute("categories", categories);
			request.setAttribute("product", product);
			request.setAttribute("categoryKey", KeyFactory.keyToString(product.getCategory().getId()));
			request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
		} else {
			response.sendRedirect("../listProduct");
		}
	}

	/**
	 * Update a product
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			}
		} catch(IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
		}
		
		response.sendRedirect("../listProduct");
	}

}
