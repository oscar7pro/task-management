package com.oscartraining.taskmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscartraining.taskmanagement.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	 Optional<Task> findById(Long id);
	 int countByStateCode(String code);
	 long count();
	 List<Task> findByStateCode(String code);

}
