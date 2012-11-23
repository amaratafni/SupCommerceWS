package com.supinfo.supcommercews.rest;

import java.util.ArrayList;

//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
//import com.google.appengine.api.datastore.KeyFactory;
//import com.google.appengine.labs.repackaged.org.json.JSONArray;
//import com.google.appengine.labs.repackaged.org.json.JSONException;
//import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.entity.Category;
//import com.supinfo.supcommercews.entity.Category;
import com.supinfo.supcommercews.entity.Product;

@Path("/products")
//@Consumes(MediaType.APPLICATION_JSON)

public class ProductResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProducts() {
		ArrayList<Product> list = new ArrayList<Product>();
		list.addAll(DaoFactory.getProductDao().getAllProducts());

		try {
			JSONArray array = new JSONArray();
		
			for(Product product : list) {
				JSONObject productObj = new JSONObject();
				productObj.put("id", KeyFactory.keyToString(product.getId()));
				productObj.put("name", product.getName());
				productObj.put("content", product.getContent());
				productObj.put("price", String.valueOf(product.getPrice()));
				
				JSONObject categoryObj = new JSONObject();
				Category category = product.getCategory();
				if(category!=null) {
					categoryObj.put("id", KeyFactory.keyToString(category.getId()));
					categoryObj.put("name", category.getName());
				}
				
				productObj.put("category", categoryObj);
				array.put(productObj);
			}
			
			return "{\"products\": "+array.toString()+"}";
			
		} catch (JSONException e) {
			System.out.println("plop");
			System.err.println(e.getMessage());
			return "";
		}
		
//		return "plop";
	}
	
	
//	@GET @Path("/{id}")
//	public Product getProduct(@PathParam("id") Key productId) {
//		return DaoFactory.getProductDao().findProduct(productId);
//	}
}
