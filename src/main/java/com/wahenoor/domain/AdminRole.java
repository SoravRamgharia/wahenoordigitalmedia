package com.wahenoor.domain;

public enum AdminRole {
	SUPER_ADMIN("Super Admin"),
	ADMIN("Admin"),
    MODERATOR("Moderator");

    private final String displayName;

    AdminRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}