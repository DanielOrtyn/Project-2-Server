package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Category;
import com.revature.repository.CategoryRepo;


@Service
public class CategoryService {
	@Autowired 
	private CategoryRepo categoryRepo;
	
	public List<Category> findAll(){
		return categoryRepo.findAll();
	}
	
	public Optional<Category> findById(long id) {
		return categoryRepo.findById(id);
	}
	
	public Category save(Category c) {
		return categoryRepo.save(c);
	}
}
