package com.oscartraining.taskmanagement.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oscartraining.taskmanagement.model.State;
import com.oscartraining.taskmanagement.model.Task;
import com.oscartraining.taskmanagement.model.User;
import com.oscartraining.taskmanagement.model.UserTask;
import com.oscartraining.taskmanagement.service.StateService;
import com.oscartraining.taskmanagement.service.TaskService;
import com.oscartraining.taskmanagement.service.UserService;
import com.oscartraining.taskmanagement.service.UserTaskService;
import com.oscartraining.taskmanagementconstantes.RoleConstante;
import com.oscartraining.taskmanagementconstantes.StateConstante;

import lombok.extern.java.Log;

@Controller
@Log
public class TaskController {
	private final static Logger LOGGER = Logger.getLogger(TaskController.class.getName());
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	StateService stateService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserTaskService userTaskService;
	
	final private static String REDIRECT ="redirect:/";

	
	@GetMapping("/list-tasks")
	public String listTasks(final Model model, final HttpServletRequest request) {
		User connectedUser = (User)request.getSession().getAttribute("userDB");
		if(connectedUser.getRole().getCode().equals(RoleConstante.ROLE_ADMIN)) {
			model.addAttribute("tasks", taskService.findAll());
		} else {
			List<Task> userTask = new ArrayList<>();
			for(UserTask uTask : userTaskService.findByUserEmail(connectedUser.getEmail())) {
				userTask.add(uTask.getTask());
			}
			model.addAttribute("tasks", userTask);
		}
		return "views/list-tasks";
		
	}
	
	@GetMapping("/add-task")
    //@RequestMapping(value="/add-task", method=RequestMethod.GET)
	public String addTask(final Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("states",stateService.findAll());
		return "views/add-task";
	}
	
	@PostMapping("/add-task")
    //@RequestMapping(value="/add-task", method=RequestMethod.POST)
	public String addTaskPost( @Valid Task task ,  final BindingResult result, final Model model ) {
		taskService.save(task);
		return "redirect:/list-tasks";
	}
	
	@GetMapping("/delete-task")
	public String deleteTask(@RequestParam("idTask")final Long id) {
		taskService.delete(id);
		return "redirect:/list-tasks";
	}
	
	@GetMapping("/detail-task")
	public String detailTask(@RequestParam("idTask")final Long id, final Model model) {
		model.addAttribute("task", taskService.findOne(id).get());
		
		UserTask userTask = new UserTask();
		userTask.setTask(taskService.findOne(id).get());
		model.addAttribute("userTask", userTask);
		model.addAttribute("users", userService.findAll());
		return "views/detail-task";
	}
	
	@PostMapping("/affect")
	public String affect(final UserTask userTask) {
		userTask.setAssignmentDate(new Date());
		userTaskService.save(userTask);
		return REDIRECT + "list-tasks";
	}
	
	@GetMapping("/edit-task")
	public String editTask(@RequestParam("idTask")final Long id, final Model model, final Task task) {
		model.addAttribute("task", taskService.findOne(id).get());
		model.addAttribute("states", stateService.findAll());
		return "views/add-task";
	}
	
	@GetMapping("/toDo")
	public String toDoTask(@RequestParam("idTask")final Long id, final Model model) {
		Task task = taskService.findOne(id).get();
		task.setState(stateService.findByCode(StateConstante.TODO));
		taskService.save(task);
		return "redirect:/home";
	}
	
	@GetMapping("/done")
	public String doneTask(@RequestParam("idTask")final Long id, final Model model) {
		Task task = taskService.findOne(id).get();
		task.setState(stateService.findByCode(StateConstante.DONE));
		taskService.save(task);
		return "redirect:/home";
	}

}
