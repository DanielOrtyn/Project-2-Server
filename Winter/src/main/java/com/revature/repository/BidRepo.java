package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Bid;
import com.revature.model.User;

public interface BidRepo extends JpaRepository<Bid, Long> {
	
	public List<Bid> findByBidder(User bidder);
	
	@Query("FROM Bid bid WHERE bid.saleItemId = :saleId")
	public List<Bid> findUsingBidder(long saleId);

	@Query("FROM Bid bid WHERE bid.saleItemId = :saleId AND bid.bidder = :bidder")
	public List<Bid> findUsingBidderAndSaleItem(long saleId, User bidder);

}
