package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Img;
import com.revature.service.ImgService;

@RestController
@RequestMapping("imgs")
public class ImgController {
	
	@Autowired
	private ImgService imgService;
	
	@GetMapping()
	public List<Img> findAll() {
		System.out.println("I AM CALLED");
		return imgService.findAll();
	}
	
	@GetMapping("/img/{id}")
	public Img findById(@PathVariable long id) {
		return imgService.findById(id);
	}
	
	@PostMapping()
	public Img save(@RequestBody Img i) {
		return imgService.save(i);
	}
}
