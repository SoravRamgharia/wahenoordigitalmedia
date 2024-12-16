package com.wahenoor.domain;

public enum EntityStatus {
    PENDING("Pending Approval"),
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    REJECTED("Rejected");

    private final String displayName;

    EntityStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
