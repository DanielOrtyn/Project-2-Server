package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Bid;
import com.revature.model.SaleItem;

public interface BidRepo extends JpaRepository<Bid, Integer>{
	List<Bid> findByBidderid(int id);
	List<Bid> findBySaleitem(SaleItem si);
}
