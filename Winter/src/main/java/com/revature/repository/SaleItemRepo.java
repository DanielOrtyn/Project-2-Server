package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Category;
import com.revature.model.SaleItem;
import com.revature.model.User;

public interface SaleItemRepo extends JpaRepository<SaleItem, Long> {
	List<SaleItem> findByTitle(String title);

	List<SaleItem> findBySeller(User seller);
	
	@Query("FROM SaleItem item JOIN item.currentBid bid WHERE bid.currentBidPrice <= :highPrice AND bid.currentBidPrice >= :lowPrice")
	List<SaleItem> findByItemsSellingForRange(double highPrice,
			double lowPrice);

	@Query("FROM SaleItem item JOIN item.currentBid bid WHERE bid.currentBidPrice >= :lowPrice")
	List<SaleItem> findByItemsSellingForMoreThen(double lowPrice);

	@Query("FROM SaleItem item JOIN item.currentBid bid WHERE bid.currentBidPrice <= :highPrice")
	List<SaleItem> findByItemsSellingForLessThen(double highPrice);

	List<SaleItem> findByCategory(Category category);
	
	@Query("FROM SaleItem item WHERE item.endDate >= :startDate AND item.endDate >= :endDate")
	List<SaleItem> findByItemsWithEndDateRange(long startDate, long endDate);

	@Query("FROM SaleItem item WHERE item.endDate >= :startDate")
	List<SaleItem> findByItemsWithEndDateAfter(long startDate);

	@Query("FROM SaleItem item WHERE item.endDate >= :endDate")
	List<SaleItem> findByItemsWithEndDateBefore(long endDate);
}
