package com.oscartraining.taskmanagement.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oscartraining.taskmanagement.model.Task;
import com.oscartraining.taskmanagement.model.User;
import com.oscartraining.taskmanagement.model.UserTask;
import com.oscartraining.taskmanagement.service.TaskService;
import com.oscartraining.taskmanagement.service.UserService;
import com.oscartraining.taskmanagement.service.UserTaskService;
import com.oscartraining.taskmanagementconstantes.RoleConstante;
import com.oscartraining.taskmanagementconstantes.StateConstante;

@Controller
public class HomeController {
	
	@Autowired
	 TaskService taskService;
	
	@Autowired
	 UserService userService;
	
	@Autowired
	 UserTaskService userTaskService;

	@GetMapping(value = "/home")
	public String index(final Model model, final Principal principal, final HttpServletRequest request) {
		User connectedUser = userService.findByEmail(principal.getName());
		
		if(connectedUser.getRole().getCode().equals(RoleConstante.ROLE_ADMIN)) {
			model.addAttribute("newState", taskService.countByStateCode(StateConstante.NEW));
			model.addAttribute("doneState", taskService.countByStateCode(StateConstante.DONE));
			model.addAttribute("todoState", taskService.countByStateCode(StateConstante.TODO));
			model.addAttribute("allState", taskService.count());
			
			model.addAttribute("listNew", taskService.findByStateCode(StateConstante.NEW));
			model.addAttribute("listTodo", taskService.findByStateCode(StateConstante.TODO));
			model.addAttribute("listDone", taskService.findByStateCode(StateConstante.DONE));
		}
		
		if(connectedUser.getRole().getCode().equals(RoleConstante.ROLE_USER)) {
		
			List<Task> taskNew = new ArrayList<>();
			for(UserTask userTask : userTaskService.findByUserEmailAndTaskStateCode(connectedUser.getEmail(), StateConstante.NEW)) {
				taskNew.add(userTask.getTask());
			}
			model.addAttribute("listNew", taskNew);

			
			List<Task> taskTodo= new ArrayList<>();
			for(UserTask userTask : userTaskService.findByUserEmailAndTaskStateCode(connectedUser.getEmail(), StateConstante.TODO)) {
				taskTodo.add(userTask.getTask());
			}
			model.addAttribute("listTodo", taskTodo);
			
			List<Task> taskDone= new ArrayList<>();
			for(UserTask userTask : userTaskService.findByUserEmailAndTaskStateCode(connectedUser.getEmail(), StateConstante.DONE)) {
				taskDone.add(userTask.getTask());
			}
			
			model.addAttribute("newState", taskNew.size());
			model.addAttribute("doneState",taskDone.size());
			model.addAttribute("todoState", taskTodo.size());
			model.addAttribute("allState", countTask(taskNew, taskTodo, taskDone) );
			
			model.addAttribute("listDone", taskDone);
		}
		request.getSession().setAttribute("userDB", connectedUser);
		return "index";
	}

	private int countTask(List<Task> taskNew, List<Task> taskTodo, List<Task> taskDone) {
		return taskNew.size() + taskDone.size() + taskTodo.size();
	}
	
	@RequestMapping(value = { "/", "/login", "/index"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}



}
