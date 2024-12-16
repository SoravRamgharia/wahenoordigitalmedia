package com.wahenoor.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.wahenoor.domain.CampaignStatus;
import com.wahenoor.domain.CategoryType;
import com.wahenoor.domain.ChannelType;
import com.wahenoor.domain.EntityStatus;
import com.wahenoor.domain.PlatformType;
import com.wahenoor.domain.RevenueModel;
import com.wahenoor.domain.TrafficType;
import com.wahenoor.domain.VerticalType;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long campaignId;

	@Column(nullable = false)
	private String campaignName;

	@Column(name = "campaign_image")
	private String campaignImage; // or thumbnailUrl

	@Column(name = "preview_link", nullable = false)
	private String campaignPreviewLink;

	// The medium where affiliate campaigns are executed (environment or
	// technology).
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PlatformType platformType;

	// A broad classification that groups affiliate campaigns into general subject
	// areas or industries.
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CategoryType categoryType;

//	@ManyToOne
//	@JoinColumn(name = "category_id", nullable = false)
//	private Category category;

	// A more specialized niche within Category(or subCategory of Campaign),
	// focusing on a specific target market or product.
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VerticalType verticalType;

//	Describes how traffic is sourced and categorized based on origin.
//	Advertisers analyze TrafficTypes to understand the quality and intent of the audience, focusing on conversion optimization.
//	@Enumerated(EnumType.STRING)
//	private TrafficType trafficType;

//	Distribution method used to deliver campaign content or traffic to audience.
//	Affiliates choose ChannelTypes based on their niche & expertise to maximize their earning potential and align with advertiser goals.
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ChannelType channelType;

	@Column(name = "target_geography")
	private String targetGeography;

//	@Enumerated(EnumType.STRING)
//	@Column(precision = 10, scale = 2, nullable = false)
//	private RevenueModel revenueModel;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RevenueModel revenueModel;

	@Column(name = "advertiser_payout", precision = 10, scale = 0, nullable = false)
	private BigDecimal advertiserPayout;

	@Column(name = "affiliate_payout", precision = 10, scale = 0, nullable = false)
	private BigDecimal affiliatePayout;

	@Column(name = "budget", precision = 15, scale = 0, nullable = false)
	private BigDecimal budget;

	@Column(name = "start_date")
	private LocalDateTime startDate;
	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(columnDefinition = "TEXT")
	private String campaignDescription;

	@ManyToOne
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;

	@ManyToOne
	@JoinColumn(name = "affiliate_id", nullable = true)
	private Affiliate affiliate;

	@Enumerated(EnumType.STRING)
	private CampaignStatus status;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// Life-Cycle CallBacks
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
