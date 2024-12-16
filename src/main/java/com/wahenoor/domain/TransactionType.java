package com.wahenoor.domain;

public enum TransactionType {
	PAYOUT("Payout"),
	REVENUE("Revenue");

    private final String displayName;

    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}