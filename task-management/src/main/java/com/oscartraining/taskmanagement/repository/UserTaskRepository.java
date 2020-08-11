package com.oscartraining.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscartraining.taskmanagement.model.UserTask;

public interface UserTaskRepository extends JpaRepository<UserTask, Long>{
	List<UserTask> findByUserEmailAndTaskStateCode(String email, String code);
	List<UserTask> findByUserEmail(String email);
}
