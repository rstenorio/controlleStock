package com.crtlstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crtlstock.dto.OrderDetailsDTO;
import com.crtlstock.entities.OrderDetails;
import com.crtlstock.repositories.OrderDetailsRepository;

@Service
public class OrderDetailsService {
	@Autowired
	private OrderDetailsRepository repository;
	
	@Transactional(readOnly = true)
	public Page<OrderDetailsDTO> findAll(PageRequest pageRequest){
		Page<OrderDetails> list = repository.findAll(pageRequest);
		return list.map(x-> new OrderDetailsDTO(x, x.getProducts()));
	}
	
}
