package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Category;
import com.revature.model.SaleItem;
import com.revature.model.User;
import com.revature.repository.SaleItemRepo;

@Service
public class SaleItemService {

	@Autowired
	private SaleItemRepo saleItemRepo;

	public SaleItem findById(long id) {
		Optional<SaleItem> item = saleItemRepo.findById(id);
		
		return item.isPresent()? item.get(): null;
	}
	
	public List<SaleItem> findAll() {
		return saleItemRepo.findAll();
	}
	
	public List<SaleItem> findByTitle(String title) {
		return saleItemRepo.findByTitle(title);
	}

	public List<SaleItem> findBySeller(User seller) {
		return saleItemRepo.findBySeller(seller);
	}

	public List<SaleItem> findByItemsSellingForRange(double lowPrice, double highPrice) {
		return saleItemRepo.findByItemsSellingForRange(highPrice, lowPrice);
	}

	public List<SaleItem> findByItemsSellingForMoreThen(double lowPrice) {
		return saleItemRepo.findByItemsSellingForMoreThen(lowPrice);
	}

	public List<SaleItem> findByItemsSellingForLessThen(double highPrice) {
		return saleItemRepo.findByItemsSellingForLessThen(highPrice);
	}

	public List<SaleItem> findByCategory(Category category) {
		return saleItemRepo.findByCategory(category);
	}

	public List<SaleItem> findByItemsWithEndDateRange(long startDate, long endDate) {
		return saleItemRepo.findByItemsWithEndDateRange(startDate, endDate);
	}

	public List<SaleItem> findByItemsWithEndDateAfter(long startDate) {
		return saleItemRepo.findByItemsWithEndDateAfter(startDate);
	}

	public List<SaleItem> findByItemsWithEndDateBefore(long endDate) {
		return saleItemRepo.findByItemsWithEndDateBefore(endDate);
	}

	public SaleItem createSaleItem(SaleItem saleItem) {
		return saleItemRepo.save(saleItem);
	}

	public SaleItem updateSaleItem(SaleItem saleItem) {
		return saleItemRepo.save(saleItem);
	}
}
