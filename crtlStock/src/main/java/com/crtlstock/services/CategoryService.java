package com.crtlstock.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crtlstock.dto.CategoryDTO;
import com.crtlstock.entities.Category;
import com.crtlstock.repositories.CategoryRepository;
import com.crtlstock.services.exceptions.DBException;
import com.crtlstock.services.exceptions.ResourcesNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //evita lock do DB
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> list = repository.findAll(pageRequest);
		return  list.map(x-> new CategoryDTO(x));
	}

	@Transactional(readOnly = true) //evita lock do DB
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		return  list.stream().map(x-> new CategoryDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true) //evita lock do DB
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(()-> new ResourcesNotFoundException("Categoria non trovata!!"));
		return new CategoryDTO(entity);
	}

	@Transactional(readOnly = true) //evita lock do DB
	public CategoryDTO findByName(String name) {
		Optional<Category> obj = repository.findByName(name);
		Category entity = obj.orElseThrow(()-> new ResourcesNotFoundException("Categoria non trovata!!"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourcesNotFoundException("Id non Trovato: " + id);
		}
		
	}

	//senza transactional per potere catturare una exception
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourcesNotFoundException("Id non Trovato: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DBException("Categoria non puo' essere cancellata, c'ha produti collegata a lei: " + id);
		}
	}

	
	
	
}
