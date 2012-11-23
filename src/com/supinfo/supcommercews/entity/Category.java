package com.supinfo.supcommercews.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name="categories")
public class Category implements Serializable {

	private static final long serialVersionUID = -4959975553152009352L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	private String name;
	
	@OneToMany(mappedBy="category")
	private Collection<Product> products;
	
	
	public Category(String name) {
		this.name = name;
	}
	
	
	public Key getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
	
}
