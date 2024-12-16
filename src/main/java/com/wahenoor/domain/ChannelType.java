package com.wahenoor.domain;

public enum ChannelType {
	ALL_MEDIA("All Media"),
	// Traditional and Digital Channels
	EMAIL("Email Marketing"), SOCIAL("Social Media"), SEARCH("Search Engine"), CONTENT("Content Marketing"),
	DISPLAY("Display Ads"), INFLUENCER("Influencer Marketing"),

	// Newer and Innovative Channels
	PODCAST("Podcasting"), VIDEO("Video Marketing"), AFFILIATE_NETWORK("Affiliate Networks"),
	APP("Mobile App Marketing"), SMS("SMS Marketing"), PUSH_NOTIFICATIONS("Push Notifications"),
	AFFILIATE_BLOGS("Affiliate Blogs"), OTT("Over-The-Top (OTT) Media"),

	// Less Common, Niche Channels
	CHATBOTS("Chatbot Marketing"), GUEST_POSTS("Guest Posts"), REFERRAL_PROGRAMS("Referral Programs");

	private final String description;

	ChannelType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

//    List<ChannelType> videoInfluencerChannels = Arrays.stream(ChannelType.values())
//    	    .filter(channel -> channel == ChannelType.VIDEO || channel == ChannelType.INFLUENCER)
//    	    .collect(Collectors.toList());

	public String getRealWorldUse() {
		switch (this) {
		case EMAIL:
			return "Email newsletters or promotional emails, often through platforms like Mailchimp or ConvertKit.";
		case SOCIAL:
			return "Affiliate promotions on platforms like Instagram, Facebook, TikTok, and Pinterest.";
		case SEARCH:
			return "Paid search ads through Google Ads or Bing Ads driving traffic to affiliate products or services.";
		case CONTENT:
			return "Affiliate product placements in blog posts, review articles, or how-to guides.";
		case DISPLAY:
			return "Display ads like banner ads on affiliate websites, blogs, or e-commerce platforms.";
		case INFLUENCER:
			return "Influencers on platforms like YouTube or Instagram using affiliate links in their posts or videos.";
		case PODCAST:
			return "Affiliate promotions during podcasts, often with dedicated sponsor messages or affiliate link plugs.";
		case VIDEO:
			return "YouTube or TikTok videos featuring product reviews or affiliate product promotions.";
		case AFFILIATE_NETWORK:
			return "Affiliate networks like ShareASale, CJ Affiliate, and Rakuten, where affiliates track performance and commissions.";
		case APP:
			return "Affiliate marketing through mobile apps such as cashback or shopping apps like Rakuten, Honey, or Ibotta.";
		case SMS:
			return "Text messages containing affiliate offers or product links sent to a subscriber list.";
		case PUSH_NOTIFICATIONS:
			return "Affiliate offers delivered via push notifications on mobile apps or websites.";
		case AFFILIATE_BLOGS:
			return "Dedicated blogs or websites focused on affiliate promotions, reviews, and product comparisons.";
		case OTT:
			return "Affiliate marketing through streaming platforms, where ads or sponsored content feature affiliate links.";
		case CHATBOTS:
			return "Affiliate links integrated into chatbot conversations, typically on websites or mobile apps.";
		case GUEST_POSTS:
			return "Affiliate marketing through guest blog posts on other websites or platforms.";
		case REFERRAL_PROGRAMS:
			return "Affiliate marketing programs that reward users for referring others, often with a commission or discount.";
		default:
			return "Unknown channel type.";
		}
	}
}
