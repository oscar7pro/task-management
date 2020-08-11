package com.oscartraining.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscartraining.taskmanagement.model.State;

public interface StateRepository extends JpaRepository<State, Long>{
	State findByCode(String code);
}
