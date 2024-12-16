package com.wahenoor.mapper;

import com.wahenoor.dto.AffiliateDto;
import com.wahenoor.Entity.Affiliate;

public class AffiliateMapper {

	// Map Entity to DTO
	public static AffiliateDto toDto(Affiliate affiliate) {
		AffiliateDto affiliateDto = new AffiliateDto();
		affiliateDto.setAffiliateId(affiliate.getAffiliateId());
		affiliateDto.setName(affiliate.getName());
		affiliateDto.setEmail(affiliate.getEmail());
		affiliateDto.setPhone(affiliate.getPhone());
		affiliateDto.setTrafficSources(affiliate.getTrafficSources());
		affiliateDto.setAffiliateType(affiliate.getAffiliateType());
		affiliateDto.setCompanyName(affiliate.getCompanyName());
		affiliateDto.setWebsiteUrl(affiliate.getWebsiteUrl());
		affiliateDto.setBillingAddress(affiliate.getBillingAddress());
		affiliateDto.setTotalEarnings(affiliate.getTotalEarnings());
		affiliateDto.setStatus(affiliate.getStatus());
		affiliateDto.setCreatedAt(affiliate.getCreatedAt());
		affiliateDto.setUpdatedAt(affiliate.getUpdatedAt());
		affiliateDto.setAddress(affiliate.getAddress());
		affiliateDto.setCity(affiliate.getCity());
		affiliateDto.setState(affiliate.getState());
		affiliateDto.setCountry(affiliate.getCountry());
		affiliateDto.setPostalCode(affiliate.getPostalCode());
		return affiliateDto;
	}

	// Map DTO to Entity
	public static Affiliate toEntity(AffiliateDto affiliateDto) {
		Affiliate affiliate = new Affiliate();
		affiliate.setName(affiliateDto.getName());
		affiliate.setEmail(affiliateDto.getEmail());
		affiliate.setPhone(affiliateDto.getPhone());
		affiliate.setTrafficSources(affiliateDto.getTrafficSources());
		affiliate.setAffiliateType(affiliateDto.getAffiliateType());
		affiliate.setCompanyName(affiliateDto.getCompanyName());
		affiliate.setWebsiteUrl(affiliateDto.getWebsiteUrl());
		affiliate.setBillingAddress(affiliateDto.getBillingAddress());
		affiliate.setTotalEarnings(affiliateDto.getTotalEarnings());
		affiliate.setStatus(affiliateDto.getStatus());
		affiliate.setAddress(affiliateDto.getAddress());
		affiliate.setCity(affiliateDto.getCity());
		affiliate.setState(affiliateDto.getState());
		affiliate.setCountry(affiliateDto.getCountry());
		affiliate.setPostalCode(affiliateDto.getPostalCode());
		return affiliate;
	}

	// Update existing Entity with DTO data
	public static void updateEntityFromDto(AffiliateDto affiliateDto, Affiliate affiliate) {
		affiliate.setName(affiliateDto.getName());
		affiliate.setEmail(affiliateDto.getEmail());
		affiliate.setPhone(affiliateDto.getPhone());
		affiliate.setTrafficSources(affiliateDto.getTrafficSources());
		affiliate.setAffiliateType(affiliateDto.getAffiliateType());
		affiliate.setCompanyName(affiliateDto.getCompanyName());
		affiliate.setWebsiteUrl(affiliateDto.getWebsiteUrl());
		affiliate.setBillingAddress(affiliateDto.getBillingAddress());
		affiliate.setTotalEarnings(affiliateDto.getTotalEarnings());
		affiliate.setStatus(affiliateDto.getStatus());
		affiliate.setAddress(affiliateDto.getAddress());
		affiliate.setCity(affiliateDto.getCity());
		affiliate.setState(affiliateDto.getState());
		affiliate.setCountry(affiliateDto.getCountry());
		affiliate.setPostalCode(affiliateDto.getPostalCode());
	}
}