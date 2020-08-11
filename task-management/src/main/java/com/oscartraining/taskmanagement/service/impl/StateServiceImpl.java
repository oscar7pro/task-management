package com.oscartraining.taskmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscartraining.taskmanagement.model.State;
import com.oscartraining.taskmanagement.repository.StateRepository;
import com.oscartraining.taskmanagement.service.StateService;

@Service
public class StateServiceImpl implements StateService {
	@Autowired
	private StateRepository repository;
	
	@Override
	public Optional<State> findOne(Long id) {
		return Optional.of(repository.findById(id).orElse(new State()));
	}

	@Override
	public List<State> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(State state) {
		repository.save(state);
		
	}

	@Override
	public void update(State state) {
		repository.save(state);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(State state) {
		// TODO Auto-generated method stub
		repository.delete(state);
	}

	@Override
	public State findByCode(String code) {
		return repository.findByCode(code);
	}

}
