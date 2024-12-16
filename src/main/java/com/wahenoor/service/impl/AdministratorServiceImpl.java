package com.wahenoor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wahenoor.Entity.Administrator;
import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Affiliate;
import com.wahenoor.Repository.AdministratorRepository;
import com.wahenoor.Repository.AdvertiserRepository;
import com.wahenoor.Repository.AffiliateRepository;
import com.wahenoor.domain.AdminRole;
import com.wahenoor.domain.EntityStatus;
import com.wahenoor.service.AdministratorService;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private AdvertiserRepository advertiserRepository;

	@Autowired
	private AffiliateRepository affiliateRepository;

	@Override
	public Administrator createAdministrator(Administrator administrator) {
		if (administratorRepository.existsByUsername(administrator.getUsername())) {
			throw new IllegalArgumentException("Username already exists.");
		}

		administrator.setRole(AdminRole.ADMIN);

		return administratorRepository.save(administrator);
	}

	@Override
	public Administrator getAdministratorById(Long id) {
		return administratorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Administrator not found with ID " + id));
	}

	@Override
	public List<Administrator> getAllAdministrators() {
		return administratorRepository.findAll();
	}

	@Override
	public void approveAdvertiser(Long advertiserId) {
		Advertiser advertiser = advertiserRepository.findById(advertiserId)
				.orElseThrow(() -> new IllegalArgumentException("Advertiser not found."));
		advertiser.setStatus(EntityStatus.ACTIVE);
		advertiserRepository.save(advertiser);
	}

	@Override
	public void rejectAdvertiser(Long advertiserId, String reason) {
		Advertiser advertiser = advertiserRepository.findById(advertiserId)
				.orElseThrow(() -> new IllegalArgumentException("Advertiser not found."));
		advertiser.setStatus(EntityStatus.REJECTED);
		advertiserRepository.save(advertiser);
		// Log or notify rejection with reason
	}

	@Override
	public void approveAffiliate(Long affiliateId) {
		Affiliate affiliate = affiliateRepository.findById(affiliateId)
				.orElseThrow(() -> new IllegalArgumentException("Affiliate not found."));
		affiliate.setStatus(EntityStatus.ACTIVE);
		affiliateRepository.save(affiliate);
	}

	@Override
	public void rejectAffiliate(Long affiliateId, String reason) {
		Affiliate affiliate = affiliateRepository.findById(affiliateId)
				.orElseThrow(() -> new IllegalArgumentException("Affiliate not found."));
		affiliate.setStatus(EntityStatus.REJECTED);
		affiliateRepository.save(affiliate);
		// Log or notify rejection with reason
	}

	@Override
	public List<Advertiser> getAllAdvertisers() {
		return advertiserRepository.findAll();
	}

	@Override
	public List<Affiliate> getAllAffiliates() {
		return affiliateRepository.findAll();
	}
}
