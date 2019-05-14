package com.revature.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.model.UserAuthBody;
import com.revature.service.UserService;

@RestController
@RequestMapping("Auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody UserAuthBody credentials, HttpServletRequest req) {
		User user = userService.login(credentials.username, credentials.password);
		if (user != null) {
			req.getSession().setAttribute("user", user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
}
