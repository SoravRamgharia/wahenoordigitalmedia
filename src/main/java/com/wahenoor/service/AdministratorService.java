package com.wahenoor.service;

import java.util.List;

import com.wahenoor.Entity.Administrator;
import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Affiliate;

public interface AdministratorService {
	Administrator createAdministrator(Administrator administrator);

	Administrator getAdministratorById(Long id);

	List<Administrator> getAllAdministrators();

	void approveAdvertiser(Long advertiserId);

	void rejectAdvertiser(Long advertiserId, String reason);

	void approveAffiliate(Long affiliateId);

	void rejectAffiliate(Long affiliateId, String reason);

	List<Advertiser> getAllAdvertisers();

	List<Affiliate> getAllAffiliates();
}
