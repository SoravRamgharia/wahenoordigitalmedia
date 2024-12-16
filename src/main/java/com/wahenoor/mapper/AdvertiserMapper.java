package com.wahenoor.mapper;

import com.wahenoor.dto.AdvertiserDto;
import com.wahenoor.Entity.Advertiser;
import com.wahenoor.domain.EntityStatus;

public class AdvertiserMapper {

	// Convert Advertiser entity to AdvertiserDto
	public static AdvertiserDto toDto(Advertiser advertiser) {
		AdvertiserDto dto = new AdvertiserDto();
		dto.setAdvertiserId(advertiser.getAdvertiserId());
		dto.setName(advertiser.getName());
		dto.setEmail(advertiser.getEmail());
		dto.setPassword(advertiser.getPassword());
		dto.setPhone(advertiser.getPhone());
		dto.setCompanyName(advertiser.getCompanyName());
		dto.setWebsiteUrl(advertiser.getWebsiteUrl());
		dto.setStatus(advertiser.getStatus().name());
		dto.setPaymentTerms(advertiser.getPaymentTerms());
		dto.setAddress(advertiser.getAddress());
		dto.setCity(advertiser.getCity());
		dto.setState(advertiser.getState());
		dto.setCountry(advertiser.getCountry());
		dto.setPostalCode(advertiser.getPostalCode());
		return dto;
	}

	// Convert AdvertiserDto to Advertiser entity
	public static Advertiser toEntity(AdvertiserDto dto) {
		Advertiser advertiser = new Advertiser();
		advertiser.setAdvertiserId(dto.getAdvertiserId());
		advertiser.setName(dto.getName());
		advertiser.setEmail(dto.getEmail());
		advertiser.setPassword(dto.getPassword());
		advertiser.setPhone(dto.getPhone());
		advertiser.setCompanyName(dto.getCompanyName());
		advertiser.setWebsiteUrl(dto.getWebsiteUrl());
		advertiser.setStatus(EntityStatus.valueOf(dto.getStatus()));
		advertiser.setPaymentTerms(dto.getPaymentTerms());
		advertiser.setAddress(dto.getAddress());
		advertiser.setCity(dto.getCity());
		advertiser.setState(dto.getState());
		advertiser.setCountry(dto.getCountry());
		advertiser.setPostalCode(dto.getPostalCode());
		return advertiser;
	}
}
