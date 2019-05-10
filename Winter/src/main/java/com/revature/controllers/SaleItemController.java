package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.SaleItem;
import com.revature.service.SaleItemService;

@RestController
@RequestMapping("SaleItem")
public class SaleItemController {

	@Autowired
	private SaleItemService saleItemService;

	@GetMapping()
	public List<SaleItem> findAll() {
		return saleItemService.findAll();
	}

	@PostMapping()
	public Genre save(@RequestBody Genre r) {
		return genreService.save(r);
	}
}
