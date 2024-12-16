package com.wahenoor.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.wahenoor.domain.AffiliateType;
import com.wahenoor.domain.EntityStatus;

import lombok.Data;

@Data
public class AffiliateDto {
	private Long affiliateId;
	private String name;
	private String email;
	private String phone;
	private String trafficSources;
	private AffiliateType affiliateType;
	private String companyName;
	private String websiteUrl;
	private String billingAddress;
	private BigDecimal totalEarnings;
	private EntityStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
}