package com.wahenoor.domain;

public enum RevenueModel {
	CPC("Cost Per Click"), // Pay per ad click
	CPA("Cost Per Acquisition"), // Pay per specific action (purchase, etc.)
	CPL("Cost Per Lead"), // Pay per lead generated
	CPM("Cost Per Mille (Thousand Impressions)"), // Pay per 1,000 ad impressions
	CPS("Cost Per Sale"), // Pay per each sale generated
	CPI("Cost Per Install"), // Pay per app installation
	CPR("Cost Per Registration"), // Pay per user registration
	CPP("Cost Per Purchase"), // Pay per completed purchase
	CPE("Cost Per Engagement"), // Pay for user engagement (likes, shares, etc.)
	ROAS("Return On Advertising Spend"), // Revenue as a ratio of ad spend
	PPL("Pay Per Lead"), // Similar to CPL
	PPS("Pay Per Sale"), // Similar to CPS
	RPI("Revenue Per Install"), // Revenue generated per app install
	EPC("Earnings Per Click"), // Affiliate earning for each click
	EPM("Earnings Per Mille"), // Affiliate earning per 1,000 impressions
	LTV("Lifetime Value"), // Revenue based on customer lifetime value
	REVSHARE("Revenue Share"), // Percentage share of revenue
	TPP("Total Pay Per Performance"), // Aggregate of pay for performance metrics
	PPV("Pay Per View"), // Pay per video view or similar view action
	CPF("Cost Per Follow"), // Pay per social media follow
	CPAU("Cost Per Active User"), // Pay per user who becomes active
	CPO("Cost Per Order"), // Pay per order placed

	// Industry-Specific Models
	CPMF("Cost Per Form Fill"), // Pay per completed form (e.g., surveys)
	CPLQ("Cost Per Qualified Lead"), // Pay for leads meeting specific qualifications
	CPVM("Cost Per Video Mille"), // Pay per 1,000 video views
	CPPU("Cost Per Paying User"), // Pay per user who completes payment

	// Hybrid Models
	Hybrid_CPC_CPA("Hybrid Cost Per Click and Acquisition"), // Mixed model combining clicks and actions
	Hybrid_CPM_CPA("Hybrid Cost Per Mille and Acquisition"); // Mixed model combining impressions and actions

	private final String displayName;

	RevenueModel(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}