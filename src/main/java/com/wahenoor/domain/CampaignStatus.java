package com.wahenoor.domain;

public enum CampaignStatus {
	PENDING("Pending"), 
	ACTIVE("Active"), 
	INACTIVE("Inactive"), 
	EXPIRED("Expired");

	private final String displayName;

	CampaignStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
