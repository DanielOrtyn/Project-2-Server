package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Category;
import com.revature.model.SaleItem;
import com.revature.model.User;

public interface SaleItemRepo extends JpaRepository<SaleItem, Long> {
	public List<SaleItem> findByTitle(String title);
	
	@Query("FROM SaleItem item WHERE item.endDate >= :currentDate")
	public List<SaleItem> findAllActiveSales(long currentDate);
	
	@Query("FROM SaleItem item WHERE item.endDate >= :currentDate ORDER BY item.currentBid.currentBidPrice")
	public List<SaleItem> findAllActiveSalesOrderPrice(long currentDate);

	public List<SaleItem> findBySeller(User seller);

	@Query("FROM SaleItem item JOIN item.currentBid bid WHERE bid.currentBidPrice <= :highPrice AND bid.currentBidPrice >= :lowPrice")
	public List<SaleItem> findByItemsSellingForRange(double highPrice,
			double lowPrice);

	@Query("FROM SaleItem item JOIN item.currentBid bid WHERE bid.currentBidPrice >= :lowPrice")
	public List<SaleItem> findByItemsSellingForMoreThen(double lowPrice);

	@Query("FROM SaleItem item JOIN item.currentBid bid WHERE bid.currentBidPrice <= :highPrice")
	public List<SaleItem> findByItemsSellingForLessThen(double highPrice);

	public List<SaleItem> findByCategory(Category category);

	@Query("FROM SaleItem item WHERE"
			+ " (LOWER(item.title) LIKE %:searchString% OR"
			+ " LOWER(item.description) LIKE %:searchString%)")
	public List<SaleItem> searchByTextContent(String searchString);

	@Query("FROM SaleItem item WHERE category = :category AND"
			+ " (LOWER(item.title) LIKE %:searchString% OR"
			+ " LOWER(item.description) LIKE %:searchString%)")
	public List<SaleItem> searchByCategoryAndTextContent(Category category,
			String searchString);

	@Query("FROM SaleItem item WHERE category = :category AND end_date >= :dateCount")
	public List<SaleItem> findActiveByCategory(Category category,
			long dateCount);

	@Query("FROM SaleItem item WHERE"
			+ " (LOWER(item.title) LIKE %:searchString% OR"
			+ " LOWER(item.description) LIKE %:searchString%) AND end_date >= :dateCount")
	public List<SaleItem> searchActiveByTextContent(String searchString,
			long dateCount);

	@Query("FROM SaleItem item WHERE category = :category AND"
			+ " (LOWER(item.title) LIKE %:searchString% OR"
			+ " LOWER(item.description) LIKE %:searchString%) AND end_date >= :dateCount")
	public List<SaleItem> searchActiveByCategoryAndTextContent(
			Category category, String searchString, long dateCount);

	@Query("FROM SaleItem item WHERE item.endDate >= :startDate AND item.endDate >= :endDate")
	public List<SaleItem> findByItemsWithEndDateRange(long startDate,
			long endDate);

	@Query("FROM SaleItem item WHERE item.endDate >= :startDate")
	public List<SaleItem> findByItemsWithEndDateAfter(long startDate);

	@Query("FROM SaleItem item WHERE item.endDate >= :endDate")
	public List<SaleItem> findByItemsWithEndDateBefore(long endDate);
}
