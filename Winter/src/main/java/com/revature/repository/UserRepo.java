package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	public List<User> findByUsername(String username);

	public List<User> findByName(String name);
	
	public User findByUsernameAndPassword(String username, String password);
}
