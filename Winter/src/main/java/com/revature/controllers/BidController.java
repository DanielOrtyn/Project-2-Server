package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Bid;
import com.revature.model.User;
import com.revature.service.BidService;

@RestController
@RequestMapping("bid")
public class BidController {
	@Autowired
	private BidService bidService;

	@GetMapping()
	public List<Bid> findAll() {
		return bidService.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Bid> findById(@PathVariable int id,
			HttpServletRequest req) {
		Bid foundBid = bidService.findById(id).get();

		User currentUser = (User) req.getSession().getAttribute("user");
		if (foundBid == null || currentUser == null || (currentUser
				.getUserId() != foundBid.getBidder().getUserId())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(foundBid, HttpStatus.OK);
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<Bid> save(@RequestBody Bid b,
			HttpServletRequest req) {
		User currentUser = (User) req.getSession().getAttribute("user");
		if (b == null || currentUser == null
				|| (currentUser.getUserId() != b.getBidder().getUserId())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		ResponseEntity<Bid> response = bidService.save(b);
		if(response==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PostMapping("findByBidder")
	public ResponseEntity<List<Bid>> findByBidderId(@RequestBody User bidder,
			HttpServletRequest req) {

		User currentUser = (User) req.getSession().getAttribute("user");
		if (bidder == null || currentUser == null
				|| (currentUser.getUserId() != bidder.getUserId())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(bidService.findByBidder(bidder),
				HttpStatus.OK);
	}
}
