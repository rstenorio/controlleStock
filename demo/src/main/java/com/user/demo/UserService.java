package com.user.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true) //evita lock do DB
	public List<UserDTO> findAll(){
		List<User> list = repository.findAll();
		return list.stream().map(x-> new UserDTO(x)).collect(Collectors.toList());
	}
	
}
