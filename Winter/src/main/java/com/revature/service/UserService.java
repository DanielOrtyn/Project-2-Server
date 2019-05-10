package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.User;
import com.revature.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User findById(long id) {
		Optional<User> item = userRepo.findById(id);

		return item.isPresent() ? item.get() : null;
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findByUsername(String username) {
		List<User> matchingUsers = userRepo.findByUsername(username);
		if (matchingUsers.size() != 1) {
			return null;
		}
		return matchingUsers.get(0);
	}

	public List<User> findByName(String name) {
		return userRepo.findByName(name);
	}

	public User authUser(String username, String password) {
		List<User> matchingUsers = userRepo.authUser(username, password);
		if (matchingUsers.size() != 1) {
			return null;
		}
		return matchingUsers.get(0);
	}
	
	public User createUser(User user) {
		return userRepo.save(user);
	}

	public User updateUser(User user) {
		return userRepo.save(user);
	}
}
