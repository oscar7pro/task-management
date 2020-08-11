package com.oscartraining.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscartraining.taskmanagement.model.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{
	Role findByCode(String code);

}
