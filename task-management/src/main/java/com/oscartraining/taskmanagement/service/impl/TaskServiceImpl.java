package com.oscartraining.taskmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscartraining.taskmanagement.model.Task;
import com.oscartraining.taskmanagement.repository.TaskRepository;
import com.oscartraining.taskmanagement.service.TaskService;
import com.oscartraining.taskmanagementexception.StateException;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	TaskRepository repository;

	@Override
	public Optional<Task> findOne(Long id) {
		//return Optional.of(repository.findById(id).orElse(new Task()));
		   return Optional.ofNullable(repository.findById(id)
				   .orElseThrow(()
						   -> new StateException(String.format("STATE IS NOT FOUND", id))));
				   
	}

	@Override
	public List<Task> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Task task) {
		repository.save(task);
	}

	@Override
	public void update(Task task) {
		repository.save(task);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(Task task) {
		repository.delete(task);
	}

	@Override
	public int countByStateCode(String code) {
		return repository.countByStateCode(code);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public List<Task> findByStateCode(String code) {
		return repository.findByStateCode(code);
	}

}
