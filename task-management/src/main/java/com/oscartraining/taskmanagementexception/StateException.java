package com.oscartraining.taskmanagementexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StateException  extends RuntimeException{

	public StateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
