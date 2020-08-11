package com.oscartraining.taskmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscartraining.taskmanagement.model.Role;
import com.oscartraining.taskmanagement.repository.RoleRepository;
import com.oscartraining.taskmanagement.service.RoleService;

@Service
public class RoleServiveImpl implements RoleService{
	@Autowired
	RoleRepository repository;

	@Override
	public Optional<Role> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Role role) {
		repository.save(role);
		
	}

	@Override
	public void update(Role role) {
		repository.save(role);
		
	}

	@Override
	public void delete(Long id) {
	repository.deleteById(id);
	}

	@Override
	public void delete(Role role) {
		repository.delete(role);
	}

	@Override
	public Role findByCode(String code) {
		return repository.findByCode(code);
	}

}
