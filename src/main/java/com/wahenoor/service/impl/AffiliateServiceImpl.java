package com.wahenoor.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wahenoor.Entity.Affiliate;
import com.wahenoor.Repository.AffiliateRepository;
import com.wahenoor.domain.EntityStatus;
import com.wahenoor.service.AffiliateService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AffiliateServiceImpl implements AffiliateService {

	private final AffiliateRepository affiliateRepository;

	@Override
	public Affiliate createAffiliate(Affiliate affiliate) {
		affiliate.setStatus(EntityStatus.PENDING); // Default status
		return affiliateRepository.save(affiliate);
	}
	

	@Override
	public Affiliate updateAffiliate(Long id, Affiliate affiliate) {
//		Affiliate existingAffiliate = affiliateRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Affiliate with ID " + id + " not found."));
		Affiliate existingAffiliate = affiliateRepository.findById(id).orElseThrow();
		existingAffiliate.setName(affiliate.getName());
		existingAffiliate.setEmail(affiliate.getEmail());
		existingAffiliate.setPhone(affiliate.getPhone());
		existingAffiliate.setTrafficSources(affiliate.getTrafficSources());
		existingAffiliate.setAffiliateType(affiliate.getAffiliateType());
		existingAffiliate.setCompanyName(affiliate.getCompanyName());
		existingAffiliate.setWebsiteUrl(affiliate.getWebsiteUrl());
		existingAffiliate.setBillingAddress(affiliate.getBillingAddress());
		existingAffiliate.setTotalEarnings(affiliate.getTotalEarnings());
		existingAffiliate.setStatus(affiliate.getStatus());
		return affiliateRepository.save(existingAffiliate);
	}

	@Override
	public Affiliate getAffiliateById(Long id) {
		return affiliateRepository.findById(id).orElseThrow();
		// return affiliateRepository.findById(id).orElseThrow(() -> new
		// IllegalArgumentException("Affiliate with ID " + id + " not found."));
	}

	@Override
	public List<Affiliate> getAllAffiliates() {
		return affiliateRepository.findAll();
	}

	@Override
	public void deleteAffiliate(Long id) {
//		Affiliate affiliate = affiliateRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Affiliate with ID " + id + " not found."));
		Affiliate affiliate = affiliateRepository.findById(id).orElseThrow();
		affiliateRepository.delete(affiliate);
	}

	@Override
	public long getAffiliateCount() {
		return affiliateRepository.count();
	}

	@Override
	public Page<Affiliate> getAllAffiliates(Pageable pageable) {
		return affiliateRepository.findAll(pageable);
	}

}
