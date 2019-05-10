package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	List<User> findByUsername(String username);

	List<User> findByName(String name);

	@Query("FROM User user WHERE user.username = :username AND user.password = :password")
	List<User> authUser(String username, String password);
}
