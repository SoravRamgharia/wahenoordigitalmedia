package com.wahenoor.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CampaignDto {

	private Long campaignId;
	@NotNull
	private String campaignName;
	private String campaignImage;
//	@NotNull
	private String campaignPreviewLink;
	@NotNull
	private String platformType;
//	private String trafficType;
	@NotNull
	private String categoryType;
	@NotNull
	private String verticalType;
//	@NotNull
	private String channelType;
	private String targetGeography;
	@NotNull
	private String revenueModel;
	@NotNull
	private String advertiserPayout;
	@NotNull
	private String affiliatePayout;
//	@NotNull
	private BigDecimal budget;
//	@NotNull
	private LocalDateTime startDate;
//	@NotNull
	private LocalDateTime endDate;
	private String campaignDescription;
	@NotNull
	private Long advertiserId;
	private Long affiliateId;
//	@NotNull
	private String status;

}
