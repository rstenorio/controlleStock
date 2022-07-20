package com.crtlstock.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crtlstock.dto.CategoryDTO;
import com.crtlstock.dto.ProductDTO;
import com.crtlstock.entities.Category;
import com.crtlstock.entities.Product;
import com.crtlstock.repositories.CategoryRepository;
import com.crtlstock.repositories.ProductRepository;
import com.crtlstock.services.exceptions.ResourcesNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(PageRequest page) {
		Page<Product> list = repository.findAll(page);
		return list.map(x -> new ProductDTO(x, x.getCategories()));
	}
	
	/*
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(String name, Long categoryId, Pageable pageable) {
		List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getReferenceById(categoryId));

		Page<Product> page = repository.find(name, categories, pageable);
		
		repository.findProductsWithCategories(page.getContent());
		return page.map(x -> new ProductDTO(x, x.getCategories()));

	}
	 */
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourcesNotFoundException("Categoria non trovata!!"));
		//return new ProductDTO(entity);
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional(readOnly = true)
	public ProductDTO insert(@Valid ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional(readOnly = true)
	public ProductDTO update(@Valid ProductDTO dto, Long id) {
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourcesNotFoundException("Id non Trovato: " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourcesNotFoundException("Id non Trovato: " + id);
		}
	}
	
	private void copyDtoToEntity(@Valid ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setPrice(dto.getPrice());
		entity.setImg_url(dto.getImg_url());
		
		entity.getCategories().clear();
		
		for(CategoryDTO catDTO : dto.getCategories()) {
			Category category = categoryRepository.getReferenceById(catDTO.getId());
			entity.getCategories().add(category);
		}
		
	}

}
