package com.crtlstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crtlstock.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
