package com.crtlstock.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.crtlstock.entities.Category;
import com.crtlstock.entities.Product;


public class ProductDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min=3,max=60,message="Minimo de 3 e maximo de 60 caractere")
	@NotBlank(message = "Campo Obrigatorio")
	private String name;
	
	@NotBlank(message = "Campo Obrigatorio")
	private String description;
	
	@Positive(message = "Valor Invalido!!")
	private Double price;
	private String img_url;
	
	//@FutureOrPresent(message = "Data Invalida")
	private Instant date;
	
	@Positive(message = "Valor Invalido!!")
	private Integer quantityStock;
	
	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO() {
	}

	public ProductDTO(String name, String description, Double price, String img_url, Instant date, Integer qtStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.img_url = img_url;
		this.date = date;
		this.quantityStock = qtStock;
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description =  entity.getDescription();
		this.price = entity.getPrice();
		this.img_url = entity.getImg_url();
		this.date = entity.getDate();
		this.quantityStock = entity.getQuantityStock();
	}
	
	//carica lista dei categories
	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
	
		//carrega lista com valores DTO
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}
	
	public Integer getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(Integer quantityStock) {
		this.quantityStock = quantityStock;
	}
	
	public List<CategoryDTO> getCategories() {
		return categories;
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
		ProductDTO other = (ProductDTO) obj;
		return Objects.equals(id, other.id);
	}	
	
}
