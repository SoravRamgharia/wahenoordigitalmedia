package com.wahenoor.service;

import java.io.File;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Campaign;
import com.wahenoor.dto.CampaignDto;

public interface CampaignService {
	CampaignDto createCampaign(CampaignDto campaignDto);

	CampaignDto updateCampaign(Long id, CampaignDto campaignDto);

	CampaignDto getCampaignById(Long id);

	List<CampaignDto> getAllCampaigns();

	List<Campaign> getAllCampaignsEntity();

	void deleteCampaign(Long id);

	public long getCampaignCount();

	public Page<Campaign> getAllCampaigns(Pageable pageable);

	void importExcelToCampaign(File excelFile);

	List<CampaignDto> searchByType(String type, String value);

}
