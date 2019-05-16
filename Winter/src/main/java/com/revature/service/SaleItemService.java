package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Category;
import com.revature.model.SaleItem;
import com.revature.model.User;
import com.revature.repository.CategoryRepo;
import com.revature.repository.SaleItemRepo;
import com.revature.util.TimeUtil;

@Service
public class SaleItemService {

	@Autowired
	private SaleItemRepo saleItemRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	public SaleItem findById(long id) {
		Optional<SaleItem> item = saleItemRepo.findById(id);

		return item.isPresent() ? item.get() : null;
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

	public List<SaleItem> findByItemsSellingForRange(double lowPrice,
			double highPrice) {
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

	public List<SaleItem> findByTextSearch(String searchString) {
		return saleItemRepo.searchByTextContent(searchString);
	}

	public List<SaleItem> findByCategoryAndTextSearch(Category category,
			String searchString) {
		return saleItemRepo.searchByCategoryAndTextContent(category,
				searchString.toLowerCase());
	}

	public List<SaleItem> findActiveByCategory(Category category) {
		return saleItemRepo.findActiveByCategory(category,
				TimeUtil.GetTimeCount());
	}

	public List<SaleItem> findActiveByTextSearch(String searchString) {
		return saleItemRepo.searchActiveByTextContent(searchString,
				TimeUtil.GetTimeCount());
	}

	public List<SaleItem> findActiveByCategoryAndTextSearch(Category category,
			String searchString) {
		return saleItemRepo.searchActiveByCategoryAndTextContent(category,
				searchString.toLowerCase(), TimeUtil.GetTimeCount());
	}

	public List<SaleItem> findByItemsWithEndDateRange(long startDate,
			long endDate) {
		return saleItemRepo.findByItemsWithEndDateRange(startDate, endDate);
	}

	public List<SaleItem> findByItemsWithEndDateAfter(long startDate) {
		return saleItemRepo.findByItemsWithEndDateAfter(startDate);
	}

	public List<SaleItem> findByItemsWithEndDateBefore(long endDate) {
		return saleItemRepo.findByItemsWithEndDateBefore(endDate);
	}

	public List<List<SaleItem>> findByTopPriceItemByCategoryItems(
			int itemCount) {
		List<Category> categoryList = categoryRepo.findAll();
		Map<Long, List<SaleItem>> itemMapList = new TreeMap<>();
		for (Category cat : categoryList) {
			itemMapList.put(cat.getCategoryId(), new ArrayList<SaleItem>());
		}

		// get all the items and then group by category
		List<SaleItem> saleItemList = saleItemRepo
				.findAllActiveSalesOrderPrice(TimeUtil.GetTimeCount());
		for (SaleItem item : saleItemList) {
			itemMapList.get(item.getCategory().getCategoryId()).add(item);
		}

		// Place all lists into a list and trim the list to the desired size
		// items are already sorted by price, due to the query
		List<List<SaleItem>> itemListList = new ArrayList<>();
		for (Long key : itemMapList.keySet()) {
			List<SaleItem> byCategoryItemList = itemMapList.get(key);
			while (byCategoryItemList.size() > itemCount) {
				byCategoryItemList.remove(byCategoryItemList.size() - 1);
			}
			if (byCategoryItemList.size() > 0)
				itemListList.add(byCategoryItemList);
		}

		return itemListList;
	}

	public SaleItem createSaleItem(SaleItem saleItem) {
		return saleItemRepo.save(saleItem);
	}

	public SaleItem updateSaleItem(SaleItem saleItem) {
		return saleItemRepo.save(saleItem);
	}
}
