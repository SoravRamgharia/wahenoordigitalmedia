package com.wahenoor.controller;

import com.wahenoor.helper.CampaignExcelImporter;
import com.wahenoor.helper.Message;
import com.wahenoor.helper.MessageType;
import com.wahenoor.mapper.CampaignMapper;
import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Campaign;
import com.wahenoor.domain.CategoryType;
import com.wahenoor.domain.ChannelType;
import com.wahenoor.domain.EntityStatus;
import com.wahenoor.domain.PlatformType;
import com.wahenoor.domain.RevenueModel;
import com.wahenoor.domain.TrafficType;
import com.wahenoor.domain.VerticalType;
import com.wahenoor.dto.CampaignDto;
import com.wahenoor.service.AdvertiserService;
import com.wahenoor.service.CampaignService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/api/campaigns")
@Controller
@RequiredArgsConstructor
public class CampaignController {

	private final CampaignService campaignService;
	private final AdvertiserService advertiserService;
	private final CampaignExcelImporter campaignExcelImporter;

	@RequestMapping(value = "/getverticaltypes", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getVerticalTypesByCategory(@RequestParam String categoryType) {
//		System.out.println("Fetching verticalTypes for categoryType: " + categoryType);

		// Initialize the list to store vertical types
		List<String> verticalTypes = new ArrayList<>();

		// Parse the categoryType from the request
		CategoryType category = CategoryType.valueOf(categoryType);

		// Loop through all VerticalType values & filter based on provided categoryType
		for (VerticalType vertical : VerticalType.values()) {
			// If vertical's category matches the provided categoryType, add it to list
			if (vertical.getCategory() == category) {
				verticalTypes.add(vertical.toString());
			}
		}
		return verticalTypes;
	}

	@GetMapping("/campaignform")
	public String createCampaignForm(Model model) {

		List<Advertiser> allAdvertisers = advertiserService.getAllAdvertisers();
//		allAdvertisers.stream().forEach(advertiser -> {
//			System.out.println(advertiser.getAdvertiserId() + " - " + advertiser.getName());
//		});

		model.addAttribute("advertisers", allAdvertisers);
		model.addAttribute("platformTypes", PlatformType.values());
		model.addAttribute("trafficTypes", TrafficType.values());
		model.addAttribute("categoryTypes", CategoryType.values()); // select categoryType then show verticals
		model.addAttribute("channelTypes", ChannelType.values());
		model.addAttribute("entityStatus", EntityStatus.values());
		model.addAttribute("revenueModel", RevenueModel.values());

		return "panel/campaignform";
	}

//	{
//		  "campaignName": "Social Media Campaign",
//		  "campaignImage": "https://example.com/campaign-image.jpg",
//		  "campaignPreviewLink": "https://example.com/preview",
//		  "platformType": "SOCIAL",
//		  "trafficType": "PAID",
//		  "categoryType": "HEALTH_WELLNESS",
//		  "verticalType": "MENTAL_HEALTH",
//		  "channelType": "SOCIAL",
//		  "targetGeography": "US, UK",
//		  "revenueModel": "CPC",
//		  "advertiserPayout": 150.50,
//		  "affiliatePayout": 120.00,
//		  "budget": 10000.00,
//		  "startDate": "2024-12-10T10:00",
//		  "endDate": "2024-12-10T10:00",
//		  "campaignDescription": "Promoting new technology gadgets.",
//		  "advertiserId": 1,
//		  "status": "ACTIVE"
//		}

	@PostMapping("/campaignsjson")
	public ResponseEntity<CampaignDto> createCampaign(@RequestBody @Valid CampaignDto campaignDto) {
		CampaignDto createdCampaign = campaignService.createCampaign(campaignDto);
		return new ResponseEntity<>(createdCampaign, HttpStatus.CREATED);
	}

	@PostMapping("/campaigns")
	public String createCampaign(@ModelAttribute @Valid CampaignDto campaignDto, HttpSession session) {
		System.out.println(campaignDto.toString());
		// Process the campaignDto object
		CampaignDto createdCampaign = campaignService.createCampaign(campaignDto);

		Message message = Message.builder().content("Your New Campaign is Successfully Created :)")
				.type(MessageType.green).build();
		session.setAttribute("message", message);

		return "redirect:/campaignform";
	}

	@PutMapping("/campaigns/{id}")
	public ResponseEntity<CampaignDto> updateCampaign(@PathVariable Long id,
			@RequestBody @Valid CampaignDto campaignDto) {
		CampaignDto updatedCampaign = campaignService.updateCampaign(id, campaignDto);
		return ResponseEntity.ok(updatedCampaign);
	}

//	http://localhost:8080/api/campaigns
	@GetMapping("/campaigns/{id}")
	public ResponseEntity<CampaignDto> getCampaignById(@PathVariable Long id) {
		CampaignDto campaign = campaignService.getCampaignById(id);
		return ResponseEntity.ok(campaign);
	}

//	@GetMapping("/campaigns")
//	public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
//		List<CampaignDto> campaigns = campaignService.getAllCampaigns(); // Fetch all campaigns
//		return ResponseEntity.ok(CampaignMapper.toDtoList(campaigns));
//	}

	@GetMapping("/campaignsjson")
	public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
		// Fetch all campaigns as entities
		List<Campaign> campaigns = campaignService.getAllCampaignsEntity();
		// Convert entities to DTOs using the mapper
		List<CampaignDto> campaignDtos = CampaignMapper.toDtoList(campaigns);
		// Return the DTOs as the response
		return ResponseEntity.ok(campaignDtos);
	}

//	@GetMapping("/campaigns")
//	public String getAllCampaigns(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size, Model model) {
//
//		// Create Pageable instance with page and size
//		Pageable pageable = PageRequest.of(page, size);
//
//		// Fetch the paginated list of advertisers
//		Page<Campaign> pageContent = campaignService.getAllCampaigns(pageable);
//
//		// Get total number of advertisers for pagination control
//		long count = campaignService.getCampaignCount();
//
//		int totalPages = pageContent.getTotalPages();
//		int currentPage = pageContent.getNumber();
//		int pageWindow = 5; // How many pages to display in the pagination window
//
//		int startPage = Math.max(1, currentPage - (pageWindow / 2));
//		int endPage = Math.min(totalPages, currentPage + (pageWindow / 2));
//
//		// Ensure there are always 5 pages displayed, adjusting start or end if
//		// necessary
//		if (endPage - startPage + 1 < pageWindow) {
//			if (startPage == 1) {
//				endPage = Math.min(pageWindow, totalPages);
//			} else if (endPage == totalPages) {
//				startPage = Math.max(1, totalPages - pageWindow + 1);
//			}
//		}
//
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("pageContent", pageContent);
//		model.addAttribute("pageSize", size); // Add the current page size to the model
//
//		// Add attributes to the model
//		model.addAttribute("campaigns", pageContent.getContent());
//		model.addAttribute("campaignCount", count);
//		model.addAttribute("currentPage", page);
//		model.addAttribute("totalPages", pageContent.getTotalPages());
//		model.addAttribute("pageSize", size);
//
//		model.addAttribute("platformTypes", PlatformType.values());
//		model.addAttribute("revenueModel", RevenueModel.values());
//		model.addAttribute("categoryTypes", CategoryType.values());
//		model.addAttribute("entityStatus", EntityStatus.values());
//		model.addAttribute("geoTargets", campaignService.getGeoTarget());
//
//		return "campaigns"; // Return the view name
//	}

	@GetMapping("/campaigns")
	public String getAllCampaigns(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String platformType,
			@RequestParam(required = false) String revenueModel, @RequestParam(required = false) String categoryType,
			@RequestParam(required = false) String geoTarget, Model model) {

		System.out.println("platformType: " + platformType);
		System.out.println("revenueModel: " + revenueModel);
		System.out.println("categoryType: " + categoryType);
		System.out.println("geoTarget: " + geoTarget);

		// Create a Pageable instance
		Pageable pageable = PageRequest.of(page, size);

		// Fetch filtered campaigns
		Page<Campaign> pageContent = campaignService.getFilteredCampaigns(platformType, revenueModel, categoryType,
				geoTarget, pageable);

		// Prepare pagination variables
		long count = pageContent.getTotalElements();
		int totalPages = pageContent.getTotalPages();
		int currentPage = pageContent.getNumber();
		int pageWindow = 5;

		int startPage = Math.max(1, currentPage - (pageWindow / 2));
		int endPage = Math.min(totalPages, currentPage + (pageWindow / 2));
		if (endPage - startPage + 1 < pageWindow) {
			if (startPage == 1) {
				endPage = Math.min(pageWindow, totalPages);
			} else if (endPage == totalPages) {
				startPage = Math.max(1, totalPages - pageWindow + 1);
			}
		}

		// Add pagination and filter details to model
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageContent", pageContent);
		model.addAttribute("campaigns", pageContent.getContent());
		model.addAttribute("campaignCount", count);

		// Add filter options
		model.addAttribute("platformTypes", PlatformType.values());
		model.addAttribute("revenueModel", RevenueModel.values());
		model.addAttribute("categoryTypes", CategoryType.values());
		model.addAttribute("geoTargets", campaignService.getGeoTarget());

		return "campaigns";
	}

	@GetMapping("/campaignlistjson")
	public ResponseEntity<List<CampaignDto>> getAllCampaignList() {
		// Fetch all campaigns as entities
		List<Campaign> campaigns = campaignService.getAllCampaignsEntity();
		// Convert entities to DTOs using the mapper
		List<CampaignDto> campaignDtos = CampaignMapper.toDtoList(campaigns);
		// Return the DTOs as the response
		return ResponseEntity.ok(campaignDtos);
	}

	@GetMapping("/campaignlist")
	public String getAllCampaignList(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, Model model) {

		// Create Pageable instance with page and size
		Pageable pageable = PageRequest.of(page, size);

		// Fetch the paginated list of advertisers
		Page<Campaign> pageContent = campaignService.getAllCampaigns(pageable);

//		System.out.println("This--------->");
//		System.out.println(pageContent.getNumber());
//		System.out.println(pageContent.getNumberOfElements());
//		System.out.println(pageContent.getSize());
//		System.out.println(pageContent.getTotalElements());
//		System.out.println(pageContent.getTotalPages());
//		System.out.println(pageContent.hasContent());

		// Get total number of advertisers for pagination control
		long count = campaignService.getCampaignCount();

		int totalPages = pageContent.getTotalPages();
		int currentPage = pageContent.getNumber();
		int pageWindow = 5; // How many pages to display in the pagination window

		int startPage = Math.max(1, currentPage - (pageWindow / 2));
		int endPage = Math.min(totalPages, currentPage + (pageWindow / 2));

		// Ensure there are always 5 pages displayed, adjusting start or end if
		// Necessary
		if (endPage - startPage + 1 < pageWindow) {
			if (startPage == 1) {
				endPage = Math.min(pageWindow, totalPages);
			} else if (endPage == totalPages) {
				startPage = Math.max(1, totalPages - pageWindow + 1);
			}
		}

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageContent", pageContent);
		model.addAttribute("pageSize", size); // Add the current page size to the model

		// Add attributes to the model
		model.addAttribute("campaigns", pageContent.getContent());
		model.addAttribute("campaignCount", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageContent.getTotalPages());
		model.addAttribute("pageSize", size);

		return "panel/campaignlist"; // Return the view name
	}

	@DeleteMapping("/campaigns/{id}")
	public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
		campaignService.deleteCampaign(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/importcampaigns")
	public ResponseEntity<String> importCampaigns(@RequestParam("file") MultipartFile file) {
		try {
			File tempFile = File.createTempFile("campaign_upload", null);
			file.transferTo(tempFile);

			campaignService.importExcelToCampaign(tempFile);
			return ResponseEntity.ok("Campaigns imported successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to import campaigns: " + e.getMessage());
		}
	}

	@GetMapping("campaigns/searchtype")
	public ResponseEntity<List<CampaignDto>> searchCampaigns(@RequestParam String type, @RequestParam String value) {
		try {
			List<CampaignDto> results = campaignService.searchByType(type, value);
			return ResponseEntity.ok(results);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Collections.emptyList());
		}
	}

}
