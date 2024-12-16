package com.wahenoor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wahenoor.Entity.Campaign;
import com.wahenoor.domain.*;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	long count(); // Count the number of Campaigns

	List<Campaign> findByCampaignNameContainingIgnoreCase(String campaignName);

	List<Campaign> findByTargetGeographyContainingIgnoreCase(String targetGeography);

	List<Campaign> findByRevenueModel(RevenueModel revenueModel);

	List<Campaign> findByCategoryType(CategoryType categoryType);

}
