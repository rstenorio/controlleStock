package com.crtlstock.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crtlstock.dto.OrderDetailsDTO;
import com.crtlstock.services.OrderDetailsService;

@RestController
@RequestMapping("/orders")
public class OrderDetailsResource {
	@Autowired
	private OrderDetailsService service;
	
	@GetMapping
	public ResponseEntity<Page<OrderDetailsDTO>> findAll(			
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "size",defaultValue = "10") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "date") String orderBy
			){
		
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		
		Page<OrderDetailsDTO> list = service.findAll(pageRequest);
		return ResponseEntity.ok().body(list);
	}
}
