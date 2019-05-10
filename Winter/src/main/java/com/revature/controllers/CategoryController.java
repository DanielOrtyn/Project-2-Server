package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Category;
import com.revature.services.CategoryService;

@RestController
@RequestMapping("category") 
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	public List<Category> findAll(){
		return categoryService.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Category> findById(@PathVariable int id) {
		return categoryService.findById(id);
	}
	
	@PostMapping()
	public Category save(Category c) {
		return categoryService.save(c);
	}
}
