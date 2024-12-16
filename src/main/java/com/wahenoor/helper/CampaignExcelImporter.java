package com.wahenoor.helper;

import org.apache.poi.ss.usermodel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wahenoor.Repository.CampaignRepository;

import com.wahenoor.Entity.*;
import com.wahenoor.domain.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@Service
public class CampaignExcelImporter {

	@Autowired
	private CampaignRepository campaignRepository;

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Transactional
	public void importCampaignData(File excelFile) throws IOException {
		try (FileInputStream fis = new FileInputStream(excelFile); Workbook workbook = WorkbookFactory.create(fis)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();

			// Skip header row
			if (rows.hasNext()) {
				rows.next();
			}

			while (rows.hasNext()) {
				Row row = rows.next();
				Campaign campaign = parseRowToCampaign(row);
				campaignRepository.save(campaign);
			}
		}
	}

	private Campaign parseRowToCampaign(Row row) {
		Campaign campaign = new Campaign();

		campaign.setCampaignName(getCellValueAsString(row.getCell(0)));
		campaign.setRevenueModel(RevenueModel.valueOf(getCellValueAsString(row.getCell(1))));
		campaign.setPlatformType(PlatformType.valueOf(getCellValueAsString(row.getCell(2))));
		campaign.setCategoryType(CategoryType.valueOf(getCellValueAsString(row.getCell(3))));
		campaign.setVerticalType(VerticalType.valueOf(getCellValueAsString(row.getCell(4))));
		campaign.setChannelType(ChannelType.valueOf(getCellValueAsString(row.getCell(5))));
		campaign.setTargetGeography(getCellValueAsString(row.getCell(6)));
		campaign.setAdvertiserPayout(getCellValueAsBigDecimal(row.getCell(7)));
		campaign.setAffiliatePayout(getCellValueAsBigDecimal(row.getCell(8)));
		campaign.setBudget(getCellValueAsBigDecimal(row.getCell(9)));
		campaign.setStatus(CampaignStatus.valueOf(getCellValueAsString(row.getCell(10))));
		campaign.setCampaignDescription(getCellValueAsString(row.getCell(11)));
		campaign.setCampaignImage(getCellValueAsString(row.getCell(12)));
		campaign.setCampaignPreviewLink(getCellValueAsString(row.getCell(13)));
		campaign.setStartDate(parseDateTime(row.getCell(14)));
		campaign.setUpdatedAt(parseDateTime(row.getCell(15)));
		campaign.setCreatedAt(parseDateTime(row.getCell(16)));
		campaign.setEndDate(parseDateTime(row.getCell(17)));

		// Advertiser and Affiliate IDs could be linked here if necessary
		// Example:
		// campaign.setAdvertiser(advertiserRepository.findById(getCellValueAsLong(row.getCell(18))));

		return campaign;
	}

//	private Campaign parseRowToCampaign(Row row) {
//		Campaign campaign = new Campaign();
//		
//		campaign.setCampaignName(getCellValueAsString(row.getCell(0)));
//		campaign.setAdvertiserPayout(getCellValueAsBigDecimal(row.getCell(1)));
//		campaign.setAffiliatePayout(getCellValueAsBigDecimal(row.getCell(2)));
//		campaign.setBudget(getCellValueAsBigDecimal(row.getCell(3)));
//		campaign.setCampaignDescription(getCellValueAsString(row.getCell(4)));
//		campaign.setCampaignImage(getCellValueAsString(row.getCell(5)));
//		campaign.setCampaignPreviewLink(getCellValueAsString(row.getCell(6)));
//		campaign.setCategoryType(CategoryType.valueOf(getCellValueAsString(row.getCell(7))));
//		campaign.setChannelType(ChannelType.valueOf(getCellValueAsString(row.getCell(8))));
//		campaign.setCreatedAt(parseDateTime(row.getCell(9)));
//		campaign.setEndDate(parseDateTime(row.getCell(10)));
//		campaign.setPlatformType(PlatformType.valueOf(getCellValueAsString(row.getCell(11))));
//		campaign.setRevenueModel(RevenueModel.valueOf(getCellValueAsString(row.getCell(12))));
//		campaign.setStartDate(parseDateTime(row.getCell(13)));
//		campaign.setStatus(CampaignStatus.valueOf(getCellValueAsString(row.getCell(14))));
//		campaign.setTargetGeography(getCellValueAsString(row.getCell(15)));
////        campaign.setTrafficType(TrafficType.valueOf(getCellValueAsString(row.getCell(15))));
//		campaign.setVerticalType(VerticalType.valueOf(getCellValueAsString(row.getCell(15))));
//		
//		// Advertiser and Affiliate IDs could be linked here if necessary
//		// Example:
//		// campaign.setAdvertiser(advertiserRepository.findById(getCellValueAsLong(row.getCell(18))));
//		
//		return campaign;
//	}

	private String getCellValueAsString(Cell cell) {
		if (cell == null)
			return null;
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		default:
			return null;
		}
	}

	private BigDecimal getCellValueAsBigDecimal(Cell cell) {
		if (cell == null || cell.getCellType() != CellType.NUMERIC)
			return null;
		return BigDecimal.valueOf(cell.getNumericCellValue());
	}

	private LocalDateTime parseDateTime(Cell cell) {
		if (cell == null || cell.getCellType() != CellType.STRING)
			return null;
		return LocalDateTime.parse(cell.getStringCellValue(), DATE_TIME_FORMATTER);
	}
}
