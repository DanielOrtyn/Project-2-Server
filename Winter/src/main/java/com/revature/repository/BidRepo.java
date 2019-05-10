package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Bid;
import com.revature.model.SaleItem;
import com.revature.model.User;

public interface BidRepo extends JpaRepository<Bid, Long>{
	List<Bid> findByBidder(User bidder);
	List<Bid> findBySaleItem(SaleItem si);
}
