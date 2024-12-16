package com.wahenoor.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wahenoor.domain.EntityStatus;

@Entity
@Table(name = "advertisers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advertiser extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long advertiserId;

	@Column(nullable = false)
	private String name;

	@Column(name = "advertiser_image")
	private String advertiserImage;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password; // added

	private String phone;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "website_url")
	private String websiteUrl;

//	@Column(name = "billing_address")
//	private String billingAddress;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EntityStatus status;

	@Column(name = "payment_terms")
	private String paymentTerms;

	// Constructor that accepts only the advertiserId
	@JsonCreator
	public Advertiser(@JsonProperty("advertiserId") Long advertiserId) {
		this.advertiserId = advertiserId;
	}
}
