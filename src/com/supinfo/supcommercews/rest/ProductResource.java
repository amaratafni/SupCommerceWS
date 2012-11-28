package com.supinfo.supcommercews.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Product;

@Path("/products")
public class ProductResource {

	/**
	 * Retrieve all products
	 * @return JSON
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		return DaoFactory.getProductDao().getAllProducts();
	}
	
}
