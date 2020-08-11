package com.oscartraining.taskmanagement.service;

import java.util.List;
import java.util.Optional;

import com.oscartraining.taskmanagement.model.Role;

public interface RoleService {
	Optional<Role> findOne(Long id);
	List<Role> findAll();
	void save(Role role);
	void  update(Role role);
	void delete(Long id);
	void delete(Role role);
	Role findByCode(String code);


}
