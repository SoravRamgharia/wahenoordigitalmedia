package com.wahenoor.domain;

public enum AffiliateType {
	INDIVIDUAL("Individual"),
	COMPANY("Company");
	
    private final String displayName;

    AffiliateType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
