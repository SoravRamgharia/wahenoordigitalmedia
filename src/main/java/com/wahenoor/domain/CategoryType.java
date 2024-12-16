package com.wahenoor.domain;

public enum CategoryType {
	HEALTH_WELLNESS("Health & Wellness"), FINANCE("Finance & Banking"), TRAVEL("Travel & Tourism"),
	FASHION("Fashion & Lifestyle"), TECHNOLOGY("Technology & Gadgets"), EDUCATION("Education & Learning"),
	HOME_LIVING("Home & Living"), AUTOMOTIVE("Automotive"), ENTERTAINMENT("Entertainment & Media"),
	FOOD_BEVERAGE("Food & Beverage"), GAMING("Gaming & Esports"), SPORTS_OUTDOORS("Sports & Outdoor Activities"),
	PETS("Pets & Animal Care"), BUSINESS_SERVICES("Business & Professional Services"),
	BEAUTY_CARE("Beauty & Personal Care"), CHARITY_NONPROFIT("Charity & Nonprofit"),
	EVENTS_HOSPITALITY("Events & Hospitality");

	private final String displayName;

	CategoryType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static CategoryType fromDisplayName(String displayName) {
		if (displayName == null || displayName.trim().isEmpty()) {
			throw new IllegalArgumentException("Category type cannot be null or empty");
		}
		for (CategoryType type : values()) {
			if (type.getDisplayName().equalsIgnoreCase(displayName.trim())) {
				return type;
			}
		}
		throw new IllegalArgumentException("No enum constant with display name: " + displayName);
	}
}
