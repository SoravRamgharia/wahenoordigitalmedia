package com.wahenoor.service;

import java.util.List;
import java.util.Optional;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.dto.AdvertiserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertiserService {
	AdvertiserDto createAdvertiser(AdvertiserDto advertiserDto);

	AdvertiserDto updateAdvertiser(Long id, AdvertiserDto advertiserDto);

	Optional<AdvertiserDto> getAdvertiserById(Long id);

	void deleteAdvertiser(Long id);

	public long getAdvertiserCount();

	public Page<Advertiser> getAllAdvertisers(Pageable pageable);

	List<Advertiser> getAllAdvertisers();
}