package com.wahenoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Affiliate;
import com.wahenoor.Entity.Campaign;
import com.wahenoor.Repository.AdministratorRepository;
import com.wahenoor.Repository.AdvertiserRepository;
import com.wahenoor.Repository.AffiliateRepository;
import com.wahenoor.Repository.CampaignRepository;
import com.wahenoor.domain.CampaignStatus;
import com.wahenoor.domain.EntityStatus;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
	@Autowired
	private CampaignRepository campaignRepository;

	@Autowired
	private AffiliateRepository affiliateRepository;

	@Autowired
	private AdvertiserRepository advertiserRepository;

	@Autowired
	private AdministratorRepository administratorRepository;

	// Fetch all campaigns
	@GetMapping("/admincampaigns")
	public List<Campaign> getAllCampaigns() {
		return campaignRepository.findAll();
	}

	// Fetch all affiliates
	@GetMapping("/affiliates")
	public List<Affiliate> getAllAffiliates() {
		return affiliateRepository.findAll();
	}

	// Fetch all advertisers
	@GetMapping("/advertisers")
	public List<Advertiser> getAllAdvertisers() {
		return advertiserRepository.findAll();
	}

	// Activate or deactivate a campaign
	@PutMapping("/campaign/{id}/status")
	public ResponseEntity<String> updateCampaignStatus(@PathVariable Long id, @RequestParam CampaignStatus status) {
		return campaignRepository.findById(id).map(campaign -> {
			campaign.setStatus(status);
			campaignRepository.save(campaign);
			return ResponseEntity.ok("Campaign status updated successfully.");
		}).orElse(ResponseEntity.notFound().build());
	}

	// Activate or deactivate an affiliate 
	@PutMapping("/affiliate/{id}/status")
	public ResponseEntity<String> updateAffiliateStatus(@PathVariable Long id, @RequestParam EntityStatus status) {
		return affiliateRepository.findById(id).map(affiliate -> {
			affiliate.setStatus(status);
			affiliateRepository.save(affiliate);
			return ResponseEntity.ok("Affiliate status updated successfully.");
		}).orElse(ResponseEntity.notFound().build());
	}

	// Activate or deactivate an advertiser
	@PutMapping("/advertiser/{id}/status")
	public ResponseEntity<String> updateAdvertiserStatus(@PathVariable Long id, @RequestParam EntityStatus status) {
		return advertiserRepository.findById(id).map(advertiser -> {
			advertiser.setStatus(status);
			advertiserRepository.save(advertiser);
			return ResponseEntity.ok("Advertiser status updated successfully.");
		}).orElse(ResponseEntity.notFound().build());
	}
}
