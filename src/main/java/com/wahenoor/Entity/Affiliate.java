package com.wahenoor.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.wahenoor.domain.AffiliateType;
import com.wahenoor.domain.EntityStatus;

@Entity
@Table(name = "affiliates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Affiliate extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long affiliateId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password; // added

	private String phone;

	@Column(name = "traffic_sources", nullable = false)
	private String trafficSources;

	@Enumerated(EnumType.STRING)
	private AffiliateType affiliateType;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "website_url")
	private String websiteUrl;

	@Column(name = "billing_address")
	private String billingAddress;

	@Column(name = "total_earnings", precision = 15, scale = 2)
	private BigDecimal totalEarnings;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EntityStatus status;
	
	@ManyToOne
	@JoinColumn(name = "advertiser_id", nullable = true)
	private Advertiser advertiser; // added

}
