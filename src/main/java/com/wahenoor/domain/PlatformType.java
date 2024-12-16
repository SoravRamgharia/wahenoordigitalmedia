package com.wahenoor.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlatformType {
	WEBSITE("Website"), 
	MOBILEAPP("Mobile App"), 
	SOCIAL("Social Media"), 
	EMAIL("Email"), 
	SEARCH("Search Engine"), 
	AFFILIATENETWORKS("AffiliateNetworks"); 

	private final String displayName;

	PlatformType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@JsonValue
	public String toJson() {
		return this.name();
	}

	@JsonCreator
	public static PlatformType fromJson(String value) {
		for (PlatformType type : PlatformType.values()) {
			if (type.name().equalsIgnoreCase(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid PlatformType: " + value);
	}
}