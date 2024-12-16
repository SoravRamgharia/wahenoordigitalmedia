package com.wahenoor.service.impl;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Affiliate;
import com.wahenoor.Entity.Campaign;
import com.wahenoor.Repository.AdvertiserRepository;
import com.wahenoor.Repository.AffiliateRepository;
import com.wahenoor.Repository.CampaignRepository;
import com.wahenoor.domain.CampaignStatus;
import com.wahenoor.dto.CampaignDto;
import com.wahenoor.mapper.CampaignMapper;
import com.wahenoor.service.CampaignService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.wahenoor.domain.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // For .xlsx
import org.apache.poi.hssf.usermodel.HSSFWorkbook; // For .xls
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@Service
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	private CampaignRepository campaignRepository;

	@Autowired
	private AdvertiserRepository advertiserRepository;

	@Autowired
	private AffiliateRepository affiliateRepository;

	@Override
	public CampaignDto createCampaign(CampaignDto campaignDto) {
		Advertiser advertiser = advertiserRepository.findById(campaignDto.getAdvertiserId()).orElseThrow();

		Affiliate affiliate = null;
		if (campaignDto.getAffiliateId() != null) {
			affiliate = affiliateRepository.findById(campaignDto.getAffiliateId()).orElseThrow();
		}

		Campaign campaign = CampaignMapper.toEntity(campaignDto, advertiser, affiliate);
		Campaign savedCampaign = campaignRepository.save(campaign);
		return CampaignMapper.toDto(savedCampaign);
	}

	@Override
	public CampaignDto updateCampaign(Long id, CampaignDto campaignDto) {
		// Fetch Existing campaign from repository, throw an exception if not found
		Campaign existingCampaign = campaignRepository.findById(id).orElseThrow();

		// Fetch the advertiser; throw an exception if not found
		Advertiser advertiser = advertiserRepository.findById(campaignDto.getAdvertiserId()).orElseThrow();

		// Fetch the affiliate if present; throw an exception if not found
		Affiliate affiliate = null;
		if (campaignDto.getAffiliateId() != null) {
			affiliate = affiliateRepository.findById(campaignDto.getAffiliateId()).orElseThrow();
		}

		// Map the DTO to the entity, and set the existing campaign ID
		Campaign updatedCampaign = CampaignMapper.toEntity(campaignDto, advertiser, affiliate);
		updatedCampaign.setCampaignId(existingCampaign.getCampaignId()); // Retain ID of the existing campaign

		// Save the updated campaign to the repository
		Campaign savedCampaign = campaignRepository.save(updatedCampaign);

		// Return the saved campaign as a DTO
		return CampaignMapper.toDto(savedCampaign);
	}

	@Override
	public CampaignDto getCampaignById(Long id) {
		Campaign campaign = campaignRepository.findById(id).orElseThrow();
		return CampaignMapper.toDto(campaign);
	}

	@Override
	public List<CampaignDto> getAllCampaigns() {
		List<Campaign> campaigns = campaignRepository.findAll();
		return campaigns.stream().map(CampaignMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<Campaign> getAllCampaignsEntity() {
		List<Campaign> campaigns = campaignRepository.findAll();
		return campaigns;
//		return campaigns.stream().map(CampaignMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public void deleteCampaign(Long id) {
		if (!campaignRepository.existsById(id)) {
//            throw new RuntimeException("Campaign not found");
		}
		campaignRepository.deleteById(id);
	}

	@Override
	public long getCampaignCount() {
		return campaignRepository.count();
	}

	@Override
	public Page<Campaign> getAllCampaigns(Pageable pageable) {
		return campaignRepository.findAll(pageable);
	}

	// EXCEL To Campaign database CampaignServiceImpl------->
	@Override
	@Transactional
	public void importExcelToCampaign(File excelFile) {
		try (FileInputStream fis = new FileInputStream(excelFile); Workbook workbook = WorkbookFactory.create(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			List<Campaign> campaigns = new ArrayList<>();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				Campaign campaign = new Campaign();

				campaign.setCampaignId(getLongValue(row.getCell(0)));
				campaign.setCampaignName(getStringValue(row.getCell(1)));
				System.out.println("Campaign Name: " + getStringValue(row.getCell(1)));

				campaign.setRevenueModel(RevenueModel.valueOf(getStringValue(row.getCell(2))));
				campaign.setPlatformType(PlatformType.valueOf(getStringValue(row.getCell(3))));
				campaign.setCategoryType(CategoryType.valueOf(getStringValue(row.getCell(4))));
				campaign.setVerticalType(VerticalType.valueOf(getStringValue(row.getCell(5))));
				campaign.setChannelType(ChannelType.valueOf(getStringValue(row.getCell(6))));
				campaign.setTargetGeography(getStringValue(row.getCell(7)));
				campaign.setAdvertiserPayout(getBigDecimalValue(row.getCell(8)));
				campaign.setAffiliatePayout(getBigDecimalValue(row.getCell(9)));
				campaign.setBudget(getBigDecimalValue(row.getCell(10)));
				campaign.setStatus(CampaignStatus.valueOf(getStringValue(row.getCell(11))));
				campaign.setCampaignDescription(getStringValue(row.getCell(12)));
				campaign.setCampaignImage(getStringValue(row.getCell(13)));
				campaign.setCampaignPreviewLink(getStringValue(row.getCell(14)));
				campaign.setStartDate(parseDate(row.getCell(15), formatter));
				campaign.setUpdatedAt(parseDate(row.getCell(16), formatter));
				campaign.setCreatedAt(parseDate(row.getCell(17), formatter));
				campaign.setEndDate(parseDate(row.getCell(18), formatter));

				// Advertiser
				campaign.setAdvertiser(new Advertiser(getLongValue(row.getCell(19))));

				// Affiliate
				Long affiliateId = getLongValue(row.getCell(20));
				Affiliate affiliate = null;
				if (affiliateId != null) {
					affiliate = affiliateRepository.findById(affiliateId)
							.orElseThrow(() -> new RuntimeException("Affiliate with ID " + affiliateId + " not found"));
				}
				campaign.setAffiliate(affiliate);

				campaigns.add(campaign);
			}

			campaignRepository.saveAll(campaigns);
		} catch (Exception e) {
			throw new RuntimeException("Failed to import Excel file: " + e.getMessage(), e);
		}
	}

	private Long getLongValue(Cell cell) {
		return cell != null && cell.getCellType() == CellType.NUMERIC ? (long) cell.getNumericCellValue() : null;
	}

	private BigDecimal getBigDecimalValue(Cell cell) {
		return cell != null && cell.getCellType() == CellType.NUMERIC ? BigDecimal.valueOf(cell.getNumericCellValue())
				: null;
	}

	private String getStringValue(Cell cell) {
		return cell != null ? cell.toString().trim() : null;
	}

	private LocalDateTime parseDate(Cell cell, DateTimeFormatter formatter) {
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return null; // Return null for empty cells
		}
		try {
			if (cell.getCellType() == CellType.NUMERIC) {
				// Excel stores dates as numeric values, convert to LocalDateTime
				return cell.getLocalDateTimeCellValue();
			} else if (cell.getCellType() == CellType.STRING) {
				// Parse string to LocalDateTime
				String dateStr = cell.getStringCellValue().trim();
				return LocalDateTime.parse(dateStr, formatter);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse date: " + cell.toString(), e);
		}
		return null;
	}

	// Search by Type
	public List<CampaignDto> searchByType(String type, String value) {
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException("Search value cannot be null or empty.");
		}

		List<Campaign> campaigns;

		System.out.println("Type: " + type + " : " + "Value: " + value);

		switch (type.toLowerCase()) {
		case "":
			campaigns = campaignRepository.findAll();
			break;

		case "campaign":
			campaigns = campaignRepository.findByCampaignNameContainingIgnoreCase(value);
			break;

		case "geo":
			campaigns = campaignRepository.findByTargetGeographyContainingIgnoreCase(value);
			break;

		case "revenuemodel":
			try {
				RevenueModel revenueModel = RevenueModel.valueOf(value.toUpperCase());
				campaigns = campaignRepository.findByRevenueModel(revenueModel);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid revenue model: " + value, e);
			}
			break;

		default:
			throw new IllegalArgumentException(
					"Invalid search type: " + type + ". Valid types are: campaign, modeltype, revenuemodel.");
		}

		return CampaignMapper.toDtoList(campaigns);
	}
}
