package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Category;
import com.revature.model.SaleItem;
import com.revature.model.User;
import com.revature.service.SaleItemService;

@RestController
@RequestMapping("SaleItem")
public class SaleItemController {

	@Autowired
	private SaleItemService saleItemService;

	@GetMapping("id/{id}")
	public SaleItem findByIdTitle(@PathVariable long id) {
		return saleItemService.findById(id);
	}
	
	@GetMapping()
	public List<SaleItem> findAll() {
		return saleItemService.findAll();
	}

	@GetMapping("title/{title}")
	public List<SaleItem> findByTitle(@PathVariable String title) {
		return saleItemService.findByTitle(title);
	}

	@PostMapping("seller")
	public List<SaleItem> findByTitle(@RequestBody User seller) {
		return saleItemService.findBySeller(seller);
	}

	@GetMapping("price/lowPrice/{lowPrice}/highPrice/{highPrice}")
	public List<SaleItem> findByItemsSellingForRange(
			@PathVariable double lowPrice, @PathVariable double highPrice) {
		return saleItemService.findByItemsSellingForRange(lowPrice, highPrice);
	}

	@GetMapping("price/lowPrice/{lowPrice}")
	public List<SaleItem> findByItemsSellingForMoreThen(double lowPrice) {
		return saleItemService.findByItemsSellingForMoreThen(lowPrice);
	}

	@GetMapping("price/highPrice/{highPrice}")
	public List<SaleItem> findByItemsSellingForLessThen(double highPrice) {
		return saleItemService.findByItemsSellingForLessThen(highPrice);
	}

	@PostMapping("category")
	public List<SaleItem> findByCategory(@RequestBody Category category) {
		return saleItemService.findByCategory(category);
	}

	@GetMapping("saleEndDate/startDate/{startDate}/endDate/{endDate}")
	public List<SaleItem> findByItemsWithEndDateRange(long startDate,
			long endDate) {
		return saleItemService.findByItemsWithEndDateRange(startDate, endDate);
	}

	@GetMapping("saleEndDate/startDate/{startDate}")
	public List<SaleItem> findByItemsWithEndDateAfter(long startDate) {
		return saleItemService.findByItemsWithEndDateAfter(startDate);
	}

	@GetMapping("saleEndDate/endDate/{endDate}")
	public List<SaleItem> findByItemsWithEndDateBefore(long endDate) {
		return saleItemService.findByItemsWithEndDateBefore(endDate);
	}

	@PostMapping()
	public SaleItem createSaleItem(@RequestBody SaleItem saleItem) {
		return saleItemService.createSaleItem(saleItem);
	}

	@PatchMapping()
	public SaleItem updateSaleItem(@RequestBody SaleItem saleItem) {
		return saleItemService.updateSaleItem(saleItem);
	}
}
