package com.oscartraining.taskmanagement.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oscartraining.taskmanagement.config.SecurityConfiguration;
import com.oscartraining.taskmanagement.model.User;
import com.oscartraining.taskmanagement.service.RoleService;
import com.oscartraining.taskmanagement.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/list-users")
	public String listUsers(final Model model) {
		model.addAttribute("users",service.findAll());
		return "views/user/list-users";
		
	}
	
	@GetMapping("/add-user")
	public String addUser(final Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles",roleService.findAll());
		return "views/user/add-user";
	}
	
	@PostMapping("/add-user")
	public String addUserPost( @Valid User user ,  final BindingResult result, final Model model ) {
		PasswordEncoder bCryptPasswordEncoder = convertToBCryptPasswordEncoder();
		String mdp = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(mdp);
		user.setActive(true);
		service.save(user);
		return "redirect:/list-users";
	}

	private PasswordEncoder convertToBCryptPasswordEncoder() {
		SecurityConfiguration secu = new SecurityConfiguration();
		PasswordEncoder bCryptPasswordEncoder = secu.passwordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@GetMapping("/delete-user")
	public String deleteUser(@RequestParam("idUser")final Long id) {
		service.delete(id);
		return "redirect:/list-users";
	}
	
	@GetMapping("/detail-user")
	public String detailUser(@RequestParam final Long idUser, final Model model) {
		model.addAttribute("user", service.findOne(idUser).get());
		return "views/user/detail-user";
	}
	
	@GetMapping("/edit-user")
	public String editUser(@RequestParam("idUser")final Long id, final Model model, final User user) {
		model.addAttribute("user", service.findOne(id).get());
		model.addAttribute("roles", roleService.findAll());
		return "views/user/add-user";
	}
	
	@GetMapping("/profile")
	public String profile(final Model model) {
		return "views/user/profile";
		
	}
	
	@GetMapping("/update-password")
	public String showPasswordUpdate(final Model model) {
		model.addAttribute("user", new User());
		return "views/user/password";
		
	}
	
	@PostMapping("/update-password")
	public String passwordUpdate(final Model model , final User user, final HttpServletRequest servletRequest) {
		User userConnected = (User) servletRequest.getSession().getAttribute("userDB");
		PasswordEncoder bCryptPasswordEncoder = convertToBCryptPasswordEncoder();
		userConnected.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		service.save(userConnected);
		return "redirect:/profile";
	}
}
