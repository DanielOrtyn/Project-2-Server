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
import com.revature.model.User;
import com.revature.service.BidService;

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
	
	@PostMapping("findByBidder")
	public List<Bid> findByBidderId(@RequestBody User bidder) {
		return bidService.findByBidder(bidder);
	}
	
	@GetMapping("saleitem")
	public List<Bid> findBySaleItem(@RequestBody SaleItem si) {
		return bidService.findBySaleItem(si);
	}
		
}
