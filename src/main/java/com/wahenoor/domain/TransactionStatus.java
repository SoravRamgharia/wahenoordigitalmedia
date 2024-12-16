package com.wahenoor.domain;

public enum TransactionStatus {
	COMPLETED("Completed"),
	PENDING("Pending"),
	REJECTED("Rejected");

    private final String displayName;

    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
