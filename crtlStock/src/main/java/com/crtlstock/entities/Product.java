package com.crtlstock.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String name; 
	private Double price;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant date;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String imgUrl;
	private Integer quantityStock;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_product_category", //nome della tabella
			joinColumns = @JoinColumn(name = "product_id"), //nome della colunna con la referenza della tabella PRODUCT
			inverseJoinColumns = @JoinColumn(name = "category_id")) //nome della colunna con la referenza della tabella <Category>
	private Set<Category> categories = new HashSet<>();


	@ManyToMany(mappedBy = "products",fetch = FetchType.EAGER)
	private Set<OrderDetails> orderDetails = new HashSet<>();
	
	public Product() {
	}

	public Product(Long id, String name, Double price, Instant date, String description, String imgUrl, Integer quantityStock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
		this.description = description;
		this.imgUrl = imgUrl;
		this.quantityStock = quantityStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg_url() {
		return imgUrl;
	}

	public void setImg_url(String img_url) {
		this.imgUrl = img_url;
	}
	
	public Integer getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(Integer quantityStock) {
		this.quantityStock = quantityStock;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	@PrePersist
	public void prePersistData() {
		date = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
