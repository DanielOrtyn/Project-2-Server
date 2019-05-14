package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController
@RequestMapping("User")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("id/{id}")
	public ResponseEntity<User> findById(@PathVariable long id,
			HttpServletRequest req) {
		User currentUser = (User) req.getSession().getAttribute("user");
		if (currentUser == null || (currentUser.getUserId() == id)) {
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}
		User updatedUser = userService.findById(id);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

//	@GetMapping()
//	public List<User> findAll() {
//		return userService.findAll();
//	}

//	@GetMapping("username/{username}")
//	public User findByUsername(@PathVariable String username) {
//		return userService.findByUsername(username);
//	}

//	@GetMapping("name/{name}")
//	public List<User> findByName(@PathVariable String name) {
//		return userService.findByName(name);
//	}

	@PostMapping()
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PatchMapping()
	public ResponseEntity<User> updateUser(@RequestBody User user,
			HttpServletRequest req) {
		User currentUser = (User) req.getSession().getAttribute("user");
		if (currentUser == null
				|| (currentUser.getUserId() == user.getUserId())) {
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}
		User updatedUser = userService.updateUser(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
}
