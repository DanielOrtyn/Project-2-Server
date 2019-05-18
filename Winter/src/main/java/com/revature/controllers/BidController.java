package com.revature.controllers;

import java.util.ArrayList;
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
import com.revature.model.BidWithSale;
import com.revature.model.SaleItem;
import com.revature.model.User;
import com.revature.service.BidService;
import com.revature.service.SaleItemService;

@RestController
@RequestMapping("bid")
public class BidController {
	@Autowired
	private BidService bidService;
	@Autowired
	private SaleItemService saleItemService;

	@GetMapping()
	public List<Bid> findAll() {
		return bidService.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Bid> findById(@PathVariable int id, HttpServletRequest req) {
		Bid foundBid = bidService.findById(id).get();

		User currentUser = (User) req.getSession().getAttribute("user");
		if (foundBid == null || currentUser == null || (currentUser.getUserId() != foundBid.getBidder().getUserId())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(foundBid, HttpStatus.OK);
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<Bid> save(@RequestBody Bid b, HttpServletRequest req) {
		User currentUser = (User) req.getSession().getAttribute("user");
		if (b == null || currentUser == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		b.setBidder(currentUser);
		ResponseEntity<Bid> response = bidService.save(b);
		if (response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PostMapping("findByBidder")
	public ResponseEntity<List<Bid>> findByBidderId(@RequestBody User bidder, HttpServletRequest req) {

		User currentUser = (User) req.getSession().getAttribute("user");
		if (bidder == null || currentUser == null || (currentUser.getUserId() != bidder.getUserId())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(bidService.findByBidder(bidder), HttpStatus.OK);
	}

	@PostMapping("findByBidderWithAttatchSales")
	public ResponseEntity<List<BidWithSale>> findByBidderWithAttachedSales(@RequestBody User bidder,
			HttpServletRequest req) {
		ResponseEntity<List<Bid>> userBidsResponse = this.findByBidderId(bidder, req);
		if (userBidsResponse.getStatusCode().value() < 200 || userBidsResponse.getStatusCode().value() >= 300) {
			return new ResponseEntity<>(userBidsResponse.getStatusCode());
		}

		List<Bid> bidList = userBidsResponse.getBody();
		List<BidWithSale> bidWithSaleList = new ArrayList<>(bidList.size());
		for (Bid bid : bidList) {
			long saleItemId = bid.getSaleItemId();
			SaleItem bidSale = this.saleItemService.findById(saleItemId);
			BidWithSale newBidWithSale = new BidWithSale(bid, bidSale);
			bidWithSaleList.add(newBidWithSale);
		}

		return new ResponseEntity<List<BidWithSale>>(bidWithSaleList, HttpStatus.OK);
	}
}
