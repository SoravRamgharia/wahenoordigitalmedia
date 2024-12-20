package com.wahenoor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wahenoor.Entity.Campaign;
import com.wahenoor.domain.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	long count(); // Count the number of Campaigns

	List<Campaign> findByCampaignNameContainingIgnoreCase(String campaignName);

	@Query("SELECT DISTINCT c.targetGeography FROM Campaign c")
	List<String> findAllDistinctTargetGeographies();

	List<Campaign> findByTargetGeographyContainingIgnoreCase(String targetGeography);

	List<Campaign> findByRevenueModel(RevenueModel revenueModel);

	List<Campaign> findByCategoryType(CategoryType categoryType);

//	@Query("SELECT c FROM Campaign c " + "WHERE (:platformType IS NULL OR c.platformType = :platformType) "
//			+ "AND (:revenueModel IS NULL OR c.revenueModel = :revenueModel) "
//			+ "AND (:categoryType IS NULL OR c.categoryType = :categoryType) "
//			+ "AND (:geoTarget IS NULL OR c.targetGeography LIKE %:geoTarget%)")
//	Page<Campaign> findFilteredCampaigns(@Param("platformType") PlatformType platformType,
//			@Param("revenueModel") RevenueModel revenueModel, @Param("categoryType") CategoryType categoryType,
//			@Param("geoTarget") String geoTarget, Pageable pageable);
	
	 @Query("SELECT c FROM Campaign c " +
				"WHERE (:platformType IS NULL OR c.platformType = :platformType) "
				+ "AND (:revenueModel IS NULL OR c.revenueModel = :revenueModel) "
				+ "AND (:categoryType IS NULL OR c.categoryType = :categoryType) "
				+ "AND (:geoTarget IS NULL OR c.targetGeography LIKE %:geoTarget%)")
		Page<Campaign> findFilteredCampaigns(@Param("platformType") PlatformType platformType,
				@Param("revenueModel") RevenueModel revenueModel, @Param("categoryType") CategoryType categoryType,
				@Param("geoTarget") String geoTarget, Pageable pageable);

}
