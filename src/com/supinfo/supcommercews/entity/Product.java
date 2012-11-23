package com.supinfo.supcommercews.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name="products")
public class Product implements Serializable {

	private static final long serialVersionUID = -6491580734752070406L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	private String name;
	private String content;
	@Column(scale=2)
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="category_fk")
	private Category category;

	
	public Product(String name, String content, BigDecimal price) {
		this.name = name;
		this.content = content;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * On renvoie le prix avec 2 chiffes après la virgule
	 * @return BigDecimal
	 */
	public BigDecimal getPrice() {
		return new BigDecimal(price.toPlainString()).setScale(2, RoundingMode.HALF_UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Key getId() {
		return id;
	}
	
	/**
	 * Redéfinition de la methode equals pour comparer les Key
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Product))
			return false;
		
		Product p = (Product) obj;
		
		if(name!=null && content!=null && price!=null)
			return name.equals(p.name) && content.equals(p.content) && price.equals(p.price);
		else return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
//		result = 37 * result + (int)(id.getId()^(id.getId()>>>32));
		result = 37 * result + name.hashCode();
		result = 37 * result + content.hashCode();
		result = 37 * result + price.hashCode();
		return result;
	}
}
