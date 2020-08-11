package com.oscartraining.taskmanagement.service;

import java.util.List;
import java.util.Optional;

import com.oscartraining.taskmanagement.model.User;

public interface UserService {
	Optional<User> findOne(Long id);
	List<User> findAll();
	void save(User user);
	void  update(User user);
	void delete(Long id);
	void delete(User user);
	User findByEmail(String email);

}
