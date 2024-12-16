package com.wahenoor.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Affiliate;

public interface AffiliateService {

	Affiliate createAffiliate(Affiliate affiliate);

	Affiliate updateAffiliate(Long id, Affiliate affiliate);

	Affiliate getAffiliateById(Long id);

	List<Affiliate> getAllAffiliates();

	void deleteAffiliate(Long id);

	public long getAffiliateCount();

	public Page<Affiliate> getAllAffiliates(Pageable pageable);
}