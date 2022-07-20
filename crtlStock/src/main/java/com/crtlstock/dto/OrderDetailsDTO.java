package com.crtlstock.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.crtlstock.entities.OrderDetails;
import com.crtlstock.entities.Product;

public class OrderDetailsDTO {
	private Long id;
	private Instant date;
	private List<ProductDTO> products = new ArrayList<>();

	
	public OrderDetailsDTO() {
	}

	public OrderDetailsDTO(Long id, Instant date) {
		this.id = id;
		this.date = date;
	}

	public OrderDetailsDTO(OrderDetails entity) {
		this.id = entity.getId();
		this.date = entity.getDate();
	}

	//carica lista dei products
	public OrderDetailsDTO(OrderDetails entity, Set<Product> products) {
		this(entity);
		products.forEach(prod -> this.products.add(new ProductDTO(prod)));
	}
	
		//carrega lista com valores DTO


	
	/*
	 * 	//carica lista dei categories
	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
	
		//carrega lista com valores DTO
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));

	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}
	

	
	
}
