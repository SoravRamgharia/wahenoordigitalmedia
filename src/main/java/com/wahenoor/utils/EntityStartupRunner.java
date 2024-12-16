package com.wahenoor.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wahenoor.Entity.Category;
import com.wahenoor.Repository.CategoryRepository;
import com.wahenoor.domain.CategoryType;
import com.wahenoor.dto.AdvertiserDto;
import com.wahenoor.service.AdvertiserService;

@Component
@RequiredArgsConstructor
public class EntityStartupRunner implements CommandLineRunner {

	private final AdvertiserService advertiserService;

	private final CategoryRepository categoryRepository;

	@Override
	public void run(String... args) {
		createDefaultAdvertiser();
//		createNewCategory();
	}

	private void createDefaultAdvertiser() {
		AdvertiserDto defaultAdvertiser = new AdvertiserDto();
		defaultAdvertiser.setName("Manpreet Singh");
		defaultAdvertiser.setEmail("manpreetsingh@gmail.com");
		defaultAdvertiser.setPassword("man123");
		defaultAdvertiser.setPhone("9592297120");
		defaultAdvertiser.setCompanyName("Sorav Pvt. Ltd.");
		defaultAdvertiser.setWebsiteUrl("https://manpreetsingh.vercel.app/");
		defaultAdvertiser.setBillingAddress("New Gurnam Nagar, S.W Road, ASR");
		defaultAdvertiser.setStatus("ACTIVE");
		defaultAdvertiser.setPaymentTerms("Net 30");
		defaultAdvertiser.setAddress("New Gurnam Nagar, S.W Road, ASR");
		defaultAdvertiser.setCity("Amritsar");
		defaultAdvertiser.setState("Punjab");
		defaultAdvertiser.setCountry("India");
		defaultAdvertiser.setPostalCode("143001");

		try {
			advertiserService.createAdvertiser(defaultAdvertiser);
			System.out.println("Default Advertiser Created Successfully.");
		} catch (Exception e) {
			System.out.println("Default Advertiser Creation Skipped: " + e.getMessage());
		}
	}

	private void createNewCategory() {
		for (CategoryType categoryType : CategoryType.values()) {
			// Check if the category already exists
			categoryRepository.findByName(categoryType.name()).orElseGet(() -> {
				// Save the category if it doesn't exist
				Category category = new Category(categoryType.name(), categoryType.getDisplayName());
				return categoryRepository.save(category);
			});
		}
		System.out.println("Categories initialized.");
	} 

}
