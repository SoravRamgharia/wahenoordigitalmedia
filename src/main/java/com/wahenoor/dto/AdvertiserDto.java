package com.wahenoor.dto;

import lombok.Data;

@Data
public class AdvertiserDto {
	private Long advertiserId;
	private String name;
	private String image;
	private String email;
	private String password;
	private String phone;
	private String companyName;
	private String websiteUrl;
	private String billingAddress;
	private String status;
	private String paymentTerms;
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
}