package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Bid;
import com.revature.model.SaleItem;
import com.revature.repository.BidRepo;

@Service
public class BidService {
	@Autowired
	private BidRepo bidRepo;
	
	public List<Bid> findAll(){
		return bidRepo.findAll();
	}
	
	public Optional<Bid> findById(int id) {
		return bidRepo.findById(id);
	}
	
	public Bid save(Bid b) {
		return bidRepo.save(b);
	}
	
	public List<Bid> findByBidderid(int id){
		return bidRepo.findByBidderid(id);
	}
	
	public List<Bid> findBySaleitem(SaleItem si){
		return bidRepo.findBySaleitem(si);
	}
}
