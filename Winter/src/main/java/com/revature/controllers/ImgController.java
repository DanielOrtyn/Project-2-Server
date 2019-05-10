package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
		return imgService.findAll();
	}
	
	@GetMapping("/img/{id}")
	public Img findById(@PathVariable long id) {
		return imgService.findById(id);
	}
	
	@PutMapping()
	public Img save(@RequestBody Img img) {
		return imgService.save(img);
	}
}
