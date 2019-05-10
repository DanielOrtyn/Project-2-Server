package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.SaleItem;
import com.revature.model.User;
import com.revature.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User findById(int id) {
		Optional<User> item = userRepo.findById(id);
		
		return item.isPresent()? item.get(): null;
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public List<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public List<User> findByName(String name) {
		return userRepo.findByName(name);
	}
}
