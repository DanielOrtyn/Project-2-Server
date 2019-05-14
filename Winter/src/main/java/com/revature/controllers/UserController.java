package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.model.UserAuthBody;
import com.revature.service.UserService;

@RestController
@RequestMapping("User")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("id/{id}")
	public User findById(@PathVariable long id) {
		return userService.findById(id);
	}
	
	@GetMapping()
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("username/{username}")
	public User findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	@GetMapping("name/{name}")
	public List<User> findByName(@PathVariable String name) {
		return userService.findByName(name);
	}

	@PostMapping()
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PatchMapping()
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
}
