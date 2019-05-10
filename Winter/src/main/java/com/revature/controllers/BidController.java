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

import com.revature.model.Bid;
import com.revature.model.SaleItem;
import com.revature.services.BidService;

@RestController
@RequestMapping("bid") 
public class BidController {
	@Autowired
	private BidService bidService;
	
	@GetMapping()
	public List<Bid> findAll(){
		return bidService.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Bid> findById(@PathVariable int id) {
		return bidService.findById(id);
	}
	
	@PostMapping()
	public Bid save(Bid b) {
		return bidService.save(b);
	}
	
	@GetMapping("bidder/{id}")
	public List<Bid> findByBidderId(@PathVariable int id) {
		return bidService.findByBidderid(id);
	}
	
	@GetMapping("saleitem")
	public List<Bid> findBySaleItem(@RequestBody SaleItem si) {
		return bidService.findBySaleitem(si);
	}
		
}
