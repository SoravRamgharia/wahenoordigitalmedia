package com.wahenoor.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.*;

public enum TrafficType {
	ALL_MEDIA("All Media"),
    ORGANIC("Organic"),
    PAID("Paid"),
    REFERRAL("Referral"),
    SOCIAL("Social"),
    DIRECT("Direct"),
    EMAIL("Email Traffic"),
    DISPLAY("Display Ads"),
    INFLUENCER("Influencer Referral"),
    AFFILIATE("Affiliate Network"),
    VIDEO("Video Traffic"),
    SEARCH_ENGINE("Search Engine Optimization"),
    PUSH("Push Notifications");

    private final String description;

    TrafficType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
//    TrafficType traffic = TrafficType.PAID;
//    System.out.println(traffic.getDescription()); // Output: Paid
//    System.out.println(traffic.getRealWorldUse()); // Output: Traffic driven by paid advertising channels like Google Ads, Facebook Ads, or Instagram Ads to an affiliate link or landing page.

//    List<TrafficType> organicPaidTraffic = Arrays.stream(TrafficType.values())
//    	    .filter(traffic -> traffic == TrafficType.ORGANIC || traffic == TrafficType.PAID)
//    	    .collect(Collectors.toList());


    public String getRealWorldUse() {
        switch (this) {
            case ORGANIC:
                return "Traffic from search engine results through SEO-optimized content or blog posts. Example: Ranking on Google for 'Best fitness trackers' and linking to an affiliate store.";
            case PAID:
                return "Traffic driven by paid advertising channels like Google Ads, Facebook Ads, or Instagram Ads to an affiliate link or landing page.";
            case REFERRAL:
                return "Traffic coming from a partnership or collaboration where one website or blog links to another, such as an affiliate product review or comparison post.";
            case SOCIAL:
                return "Traffic sourced from social media platforms like TikTok, Instagram, and Pinterest. Example: A TikTok video showcasing a product with an affiliate link in the bio.";
            case DIRECT:
                return "Direct traffic from visitors typing a URL into their browser or clicking a bookmarked link, often from email campaigns or repeat visitors.";
            case EMAIL:
                return "Traffic generated through email marketing campaigns, often via newsletters or promotional emails containing affiliate links.";
            case DISPLAY:
                return "Traffic driven from display ads like banner ads or pop-ups on websites, blogs, or affiliate networks.";
            case INFLUENCER:
                return "Traffic originating from influencer marketing, where influencers share affiliate products with their followers through posts, stories, or videos.";
            case AFFILIATE:
                return "Traffic from affiliate networks, where affiliates generate leads or sales through their links on various websites or blogs.";
            case VIDEO:
                return "Traffic coming from video content platforms like YouTube or Vimeo, where product reviews, tutorials, or affiliate promotions are shared.";
            case SEARCH_ENGINE:
                return "Traffic from search engines through paid search ads (PPC) or organic search results based on SEO-optimized content.";
            case PUSH:
                return "Traffic driven by push notifications, often sent via browser or mobile app to notify users of deals, products, or affiliate offers.";
            default:
                return "Unknown traffic type.";
        }
    }

	@JsonValue
	public String toJson() {
		return this.name();
	}
	
	@JsonCreator
	public static TrafficType fromJson(String value) {
		return Arrays.stream(TrafficType.values())
				.filter(type -> type.name().equalsIgnoreCase(value))
				.findFirst()
				.orElseThrow();
	}
}