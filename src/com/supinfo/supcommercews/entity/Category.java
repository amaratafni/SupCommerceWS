package com.supinfo.supcommercews.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
@Table(name="categories")
@XmlRootElement
public class Category implements Serializable {

	private static final long serialVersionUID = -4959975553152009352L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key key;
	private String name;
	
	@OneToMany(mappedBy="category")
	private Collection<Product> products;
	
	
	public Category(String name) {
		this.name = name;
	}
	
	
	@XmlTransient
	public Key getKey() {
		return key;
	}
	
	@XmlElement(name="id")
	public String getId() {
		return KeyFactory.keyToString(key);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlTransient
	public Collection<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
	
}
