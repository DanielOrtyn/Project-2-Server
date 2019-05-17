package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Bid;
import com.revature.model.SaleItem;
import com.revature.model.User;
import com.revature.repository.BidRepo;
import com.revature.repository.SaleItemRepo;

@Service
public class BidService {
	@Autowired
	private BidRepo bidRepo;
	@Autowired
	private SaleItemRepo saleItemRepo;

	public List<Bid> findAll() {
		return bidRepo.findAll();
	}

	public Optional<Bid> findById(long id) {
		return bidRepo.findById(id);
	}

	public ResponseEntity<Bid> save(Bid b) {
		List<Bid> previousBids = bidRepo
				.findUsingBidderAndSaleItem(b.getSaleItemId(), b.getBidder());
		// Resolve conflict with earlier bids
		if (previousBids.size() > 1) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (previousBids.size() == 1) {
			Bid previousBid = previousBids.get(0);
			double newBidPrice = previousBid.getMaxBidPrice();
			if (b.getMaxBidPrice() > previousBid.getCurrentBidPrice()) {
				newBidPrice = b.getMaxBidPrice();
			} else {
				newBidPrice = previousBid.getCurrentBidPrice();
			}
			previousBid.setMaxBidPrice(newBidPrice);
			b = previousBid;
		} else {
			b = bidRepo.save(b);
		}

		Optional<SaleItem> itemSale = saleItemRepo.findById(b.getSaleItemId());
		if (itemSale.isPresent()) {
			Bid currentSaleBid = itemSale.get().getCurrentBid();
			Bid lowBid;
			Bid highBid;
			if (currentSaleBid.getMaxBidPrice() < b.getMaxBidPrice()) {
				highBid = b;
				lowBid = currentSaleBid;
			} else {
				highBid = currentSaleBid;
				lowBid = b;
			}
			
			// check if bids have equal max price
			if (highBid.getMaxBidPrice() == lowBid.getMaxBidPrice()) {
				highBid.setCurrentBidPrice(lowBid.getMaxBidPrice());
			} else {
				// increment high bid's price to 1 dollar more, unless it's
				// maximum is in cents
				// then increment it only as much as allowed
				if (highBid.getMaxBidPrice() > lowBid.getMaxBidPrice() + 1)
					highBid.setCurrentBidPrice(lowBid.getMaxBidPrice() + 1);
				else
					highBid.setCurrentBidPrice(highBid.getMaxBidPrice());
			}

			itemSale.get().setCurrentBid(highBid);
			return new ResponseEntity<>(b, HttpStatus.OK);
		}
		return null;
	}

	public List<Bid> findByBidder(User bidder) {
		return bidRepo.findByBidder(bidder);
	}
}
