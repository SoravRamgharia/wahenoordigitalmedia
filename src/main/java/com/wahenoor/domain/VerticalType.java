package com.wahenoor.domain;


public enum VerticalType {

    // Health & Wellness Verticals
    MENTAL_HEALTH("Mental Health", CategoryType.HEALTH_WELLNESS),
    FITNESS_TRAINING("Fitness Training", CategoryType.HEALTH_WELLNESS),
    NUTRITION_SUPPLEMENTS("Nutrition & Supplements", CategoryType.HEALTH_WELLNESS),
    TELEMEDICINE("Telemedicine", CategoryType.HEALTH_WELLNESS),
    WEIGHT_MANAGEMENT("Weight Management", CategoryType.HEALTH_WELLNESS),

    // Finance Verticals
    CREDIT_CARDS("Credit Cards", CategoryType.FINANCE),
    LOANS_MORTGAGES("Loans & Mortgages", CategoryType.FINANCE),
    STOCK_INVESTMENTS("Stock Investments", CategoryType.FINANCE),
    CRYPTOCURRENCY("Cryptocurrency", CategoryType.FINANCE),
    INSURANCE_SERVICES("Insurance Services", CategoryType.FINANCE),

    // Travel & Tourism Verticals
    LUXURY_TRAVEL("Luxury Travel", CategoryType.TRAVEL),
    ADVENTURE_TRAVEL("Adventure Travel", CategoryType.TRAVEL),
    BUDGET_TRAVEL("Budget Travel", CategoryType.TRAVEL),
    TRAVEL_INSURANCE("Travel Insurance", CategoryType.TRAVEL),
    CRUISES("Cruises", CategoryType.TRAVEL),

    // Fashion & Lifestyle Verticals
    SUSTAINABLE_FASHION("Sustainable Fashion", CategoryType.FASHION),
    LUXURY_BRANDS("Luxury Brands", CategoryType.FASHION),
    ATHLEISURE("Athleisure", CategoryType.FASHION),
    JEWELRY_ACCESSORIES("Jewelry & Accessories", CategoryType.FASHION),
    SEASONAL_FASHION("Seasonal Fashion", CategoryType.FASHION),

    // Technology & Gadgets Verticals
    GAMING_HARDWARE("Gaming Hardware", CategoryType.TECHNOLOGY),
    SMART_HOME_DEVICES("Smart Home Devices", CategoryType.TECHNOLOGY),
    SAAS_TOOLS("SaaS Tools", CategoryType.TECHNOLOGY),
    CONSUMER_ELECTRONICS("Consumer Electronics", CategoryType.TECHNOLOGY),
    DEVELOPER_TOOLS("Developer Tools", CategoryType.TECHNOLOGY),

    // Education & Learning Verticals
    ONLINE_COURSES("Online Courses", CategoryType.EDUCATION),
    CERTIFICATION_EXAMS("Certification Exams", CategoryType.EDUCATION),
    LANGUAGE_LEARNING("Language Learning", CategoryType.EDUCATION),
    EBOOKS_AUDIOBOOKS("Ebooks & Audiobooks", CategoryType.EDUCATION),
    TUTORING_SERVICES("Tutoring Services", CategoryType.EDUCATION),

    // Home & Living Verticals
    FURNITURE("Furniture", CategoryType.HOME_LIVING),
    HOME_APPLIANCES("Home Appliances", CategoryType.HOME_LIVING),
    DECOR_ITEMS("Decor Items", CategoryType.HOME_LIVING),
    SMART_HOME_APPS("Smart Home Apps", CategoryType.HOME_LIVING),
    CLEANING_SUPPLIES("Cleaning Supplies", CategoryType.HOME_LIVING),

    // Gaming & Esports Verticals
    STREAMING_GEAR("Streaming Gear", CategoryType.GAMING),
    GAMING_SUBSCRIPTIONS("Gaming Subscriptions", CategoryType.GAMING),
    ESPORTS_MERCH("Esports Merchandise", CategoryType.GAMING),
    VR_AR_DEVICES("VR & AR Devices", CategoryType.GAMING),
    TOURNAMENTS("Gaming Tournaments", CategoryType.GAMING),

    // Sports & Outdoors Verticals
    FITNESS_GEAR("Fitness Gear", CategoryType.SPORTS_OUTDOORS),
    OUTDOOR_ADVENTURE("Outdoor Adventure", CategoryType.SPORTS_OUTDOORS),
    TEAM_SPORTS("Team Sports", CategoryType.SPORTS_OUTDOORS),
    HIKING_GEAR("Hiking Gear", CategoryType.SPORTS_OUTDOORS),
    WELLNESS_RETREATS("Wellness Retreats", CategoryType.SPORTS_OUTDOORS),

    // Pets & Animal Care Verticals
    PET_FOOD("Pet Food", CategoryType.PETS),
    ACCESSORIES("Accessories", CategoryType.PETS),
    PET_HEALTHCARE("Pet Healthcare", CategoryType.PETS),
    TRAINING_SERVICES("Training Services", CategoryType.PETS),
    TOYS_TREATS("Toys & Treats", CategoryType.PETS),

    // Food & Beverage Verticals
    FOOD_SUBSCRIPTIONS("Food Subscriptions", CategoryType.FOOD_BEVERAGE),
    SPECIALTY_DIETS("Specialty Diets", CategoryType.FOOD_BEVERAGE),
    WINE_SPIRITS("Wine & Spirits", CategoryType.FOOD_BEVERAGE),
    GOURMET_SNACKS("Gourmet Snacks", CategoryType.FOOD_BEVERAGE),
    COOKING_APPLIANCES("Cooking Appliances", CategoryType.FOOD_BEVERAGE);

    private final String description;
    private final CategoryType category;

    VerticalType(String description, CategoryType category) {
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public CategoryType getCategory() {
        return category;
    }
    
//    List<VerticalType> healthVerticals = Arrays.stream(VerticalType.values())
//    	    .filter(vertical -> vertical.getCategory() == CategoryType.HEALTH_WELLNESS)
//    	    .collect(Collectors.toList());

//    System.out.println(VerticalType.GAMING_HARDWARE.getDescription()); // Output: Gaming Hardware

}
