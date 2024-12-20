package com.wahenoor.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Affiliate;
import com.wahenoor.Entity.Campaign;
import com.wahenoor.Repository.AdvertiserRepository;
import com.wahenoor.domain.*;
import com.wahenoor.dto.CampaignDto;

@Component
public class CampaignMapper {

	public static Campaign toEntity(CampaignDto dto, Advertiser advertiser, Affiliate affiliate) {
		Campaign campaign = new Campaign();
		campaign.setCampaignId(dto.getCampaignId());
		campaign.setCampaignName(dto.getCampaignName());
		campaign.setCampaignImage(dto.getCampaignImage());
		campaign.setCampaignPreviewLink(dto.getCampaignPreviewLink());
		campaign.setPlatformType(PlatformType.valueOf(dto.getPlatformType()));
//		campaign.setTrafficType(TrafficType.valueOf(dto.getTrafficType()));
		campaign.setCategoryType(dto.getCategoryType());
		campaign.setVerticalType(dto.getVerticalType());
		campaign.setChannelType(ChannelType.valueOf(dto.getChannelType()));
		campaign.setTargetGeography(dto.getTargetGeography());
		campaign.setRevenueModel(RevenueModel.valueOf(dto.getRevenueModel()));
		campaign.setAdvertiserPayout(dto.getAdvertiserPayout());
		campaign.setAffiliatePayout(dto.getAffiliatePayout());
		campaign.setBudget(dto.getBudget());
		campaign.setStartDate(dto.getStartDate());
		campaign.setEndDate(dto.getEndDate());
		campaign.setCampaignDescription(dto.getCampaignDescription());
		campaign.setAdvertiser(advertiser);
		campaign.setAffiliate(affiliate);
		campaign.setStatus(CampaignStatus.valueOf(dto.getStatus()));
		return campaign;
	}

	public static CampaignDto toDto(Campaign campaign) {
		if (campaign == null) {
			return null;
		}
		CampaignDto dto = new CampaignDto();
		dto.setCampaignId(campaign.getCampaignId());
		dto.setCampaignName(campaign.getCampaignName());
		dto.setCampaignImage(campaign.getCampaignImage());
		dto.setCampaignPreviewLink(campaign.getCampaignPreviewLink());
		dto.setPlatformType(campaign.getPlatformType().name());
//		dto.setTrafficType(campaign.getTrafficType() != null ? campaign.getTrafficType().name() : null);
		dto.setCategoryType(campaign.getCategoryType());
		dto.setVerticalType(campaign.getVerticalType());
		dto.setChannelType(campaign.getChannelType().name());
		dto.setTargetGeography(campaign.getTargetGeography());
		dto.setRevenueModel(campaign.getRevenueModel().name());
		dto.setAdvertiserPayout(campaign.getAdvertiserPayout());
		dto.setAffiliatePayout(campaign.getAffiliatePayout());
		dto.setBudget(campaign.getBudget());
		dto.setStartDate(campaign.getStartDate());
		dto.setEndDate(campaign.getEndDate());
		dto.setCampaignDescription(campaign.getCampaignDescription());
		dto.setAdvertiserId(campaign.getAdvertiser().getAdvertiserId());
		dto.setAffiliateId(campaign.getAffiliate() != null ? campaign.getAffiliate().getAffiliateId() : null);
		dto.setStatus(campaign.getStatus().name());
		return dto;
	}

	public static void updateEntity(CampaignDto dto, Campaign entity, Advertiser advertiser, Affiliate affiliate) {
		// Update simple fields directly from DTO
		entity.setCampaignName(dto.getCampaignName());
		entity.setCampaignImage(dto.getCampaignImage());
		entity.setCampaignPreviewLink(dto.getCampaignPreviewLink());
		entity.setTargetGeography(dto.getTargetGeography());
		entity.setAdvertiserPayout(dto.getAdvertiserPayout());
		entity.setAffiliatePayout(dto.getAffiliatePayout());
		entity.setBudget(dto.getBudget());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setCampaignDescription(dto.getCampaignDescription());

		// Update enum fields by converting string values to corresponding enum types
		entity.setPlatformType(PlatformType.valueOf(dto.getPlatformType()));
//		entity.setTrafficType(TrafficType.valueOf(dto.getTrafficType()));
		entity.setCategoryType(dto.getCategoryType());
		entity.setVerticalType(dto.getVerticalType());
		entity.setChannelType(ChannelType.valueOf(dto.getChannelType()));
		entity.setRevenueModel(RevenueModel.valueOf(dto.getRevenueModel()));
		entity.setStatus(CampaignStatus.valueOf(dto.getStatus()));

		// Set the relationships (Advertiser and Affiliate)
		entity.setAdvertiser(advertiser);
		entity.setAffiliate(affiliate);
	}

	public static List<CampaignDto> toDtoList(List<Campaign> campaigns) {
		if (campaigns == null) {
			return Collections.emptyList();
		}
		return campaigns.stream().map(CampaignMapper::toDto).collect(Collectors.toList());
	}

}
