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

import com.crtlstock.dto.RoleDTO;
import com.crtlstock.entities.Role;
import com.crtlstock.repositories.RoleRepository;
import com.crtlstock.services.exceptions.DBException;
import com.crtlstock.services.exceptions.ResourcesNotFoundException;

@Service
public class RoleService {
	@Autowired
	private RoleRepository repository;
	
	@Transactional(readOnly = true) //evita lock do DB
	public Page<RoleDTO> findAllPaged(PageRequest pageRequest) {
		Page<Role> list = repository.findAll(pageRequest);
		return  list.map(x-> new RoleDTO(x));
	}

	@Transactional(readOnly = true) //evita lock do DB
	public List<RoleDTO> findAll(){
		List<Role> list = repository.findAll();
		return  list.stream().map(x-> new RoleDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true) //evita lock do DB
	public RoleDTO findById(Long id) {
		Optional<Role> obj = repository.findById(id);
		Role entity = obj.orElseThrow(()-> new ResourcesNotFoundException("Categoria non trovata!!"));
		return new RoleDTO(entity);
	}


	@Transactional
	public RoleDTO insert(RoleDTO dto) {
		Role entity = new Role();
		entity.setauthority(dto.getAuthority());
		entity = repository.save(entity);
		return new RoleDTO(entity);
	}

	@Transactional
	public RoleDTO update(Long id, RoleDTO dto) {
		try {
			Role entity = repository.getReferenceById(id);
			entity.setauthority(dto.getAuthority());
			entity = repository.save(entity);
			return new RoleDTO(entity);
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
