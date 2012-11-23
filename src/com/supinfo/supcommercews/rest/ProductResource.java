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
public class ProductResource {

	/**
	 * Retrieve all products
	 * @return JSON
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProducts() {
		// On recupere la liste des produits //
		ArrayList<Product> list = new ArrayList<Product>();
		list.addAll(DaoFactory.getProductDao().getAllProducts());
		
		try {
			
			JSONObject mainObj = new JSONObject();
			JSONArray array = new JSONArray();
		
			for(Product product : list) {
				// On construit un objet JSON en lui ajoutant les champs du produit //
				JSONObject productObj = new JSONObject();
				productObj.put("id", KeyFactory.keyToString(product.getId()));
				productObj.put("name", product.getName());
				productObj.put("content", product.getContent());
				productObj.put("price", String.valueOf(product.getPrice()));
				
				// On recupere aussi les informations sur la categorie du produit //
				JSONObject categoryObj = new JSONObject();
				Category category = product.getCategory();
				if(category!=null) {
					categoryObj.put("id", KeyFactory.keyToString(category.getId()));
					categoryObj.put("name", category.getName());
				}
				
				// On ajoute le JSONObject de la category au produit puis le produit au JSONArray //
				productObj.put("category", categoryObj);
				array.put(productObj);
			}
			
			// On ajoute la liste des produits à la racine du JSONObject //
			mainObj.put("products", array);
			
			return mainObj.toString();
			
		} catch (JSONException e) {
			System.err.println(e.getMessage());
			return "{\"products\":[]}";
		}
	}
	
}
