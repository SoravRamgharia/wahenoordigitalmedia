package com.wahenoor.service.impl;

import org.springframework.stereotype.Service;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Repository.AdvertiserRepository;
import com.wahenoor.domain.EntityStatus;
import com.wahenoor.dto.AdvertiserDto;
import com.wahenoor.mapper.AdvertiserMapper;
import com.wahenoor.service.AdvertiserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.lang.IllegalArgumentException;

@Service
@RequiredArgsConstructor
public class AdvertiserServiceImpl implements AdvertiserService {

	private final AdvertiserRepository advertiserRepository;

	@Override
	public AdvertiserDto createAdvertiser(AdvertiserDto advertiserDto) {
		// Check if an advertiser with the same email already exists
		if (advertiserRepository.existsByEmail(advertiserDto.getEmail())) {
			throw new IllegalArgumentException("Advertiser with this email already exists.");
		}

		Advertiser advertiser = AdvertiserMapper.toEntity(advertiserDto);
		Advertiser savedAdvertiser = advertiserRepository.save(advertiser);
		return AdvertiserMapper.toDto(savedAdvertiser);
	}

	@Override
	public AdvertiserDto updateAdvertiser(Long id, AdvertiserDto advertiserDto) {
		Advertiser existingAdvertiser = advertiserRepository.findById(id).orElseThrow();

		Advertiser updatedAdvertiser = AdvertiserMapper.toEntity(advertiserDto);
		updatedAdvertiser.setAdvertiserId(existingAdvertiser.getAdvertiserId()); // Retain ID

		Advertiser savedAdvertiser = advertiserRepository.save(updatedAdvertiser);
		return AdvertiserMapper.toDto(savedAdvertiser);
	}

	@Override
	public Optional<AdvertiserDto> getAdvertiserById(Long id) {
		return advertiserRepository.findById(id).map(AdvertiserMapper::toDto);
	}

	@Override
	public void deleteAdvertiser(Long id) {
		Advertiser existingAdvertiser = advertiserRepository.findById(id).orElseThrow();
		advertiserRepository.delete(existingAdvertiser);
	}

	@Override
	public long getAdvertiserCount() {
		return advertiserRepository.count();
	}

	@Override
	public Page<Advertiser> getAllAdvertisers(Pageable pageable) {
		return advertiserRepository.findAll(pageable);
	}

	@Override
	public List<Advertiser> getAllAdvertisers() {
		return advertiserRepository.findAll();
	}
}