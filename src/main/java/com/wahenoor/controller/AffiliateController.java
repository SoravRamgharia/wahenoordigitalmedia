package com.wahenoor.controller;

import com.wahenoor.Entity.Affiliate;
import com.wahenoor.domain.EntityStatus;
import com.wahenoor.service.AffiliateService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/affiliates")
@Controller
@RequiredArgsConstructor
public class AffiliateController {

	private final AffiliateService affiliateService;

	@GetMapping("/affiliateform")
	public String createAdvertiserForm(Model model) {
		model.addAttribute("entityStatus", EntityStatus.values());
		return "panel/affiliateform";
	}

//    {
//    	  "name": "John Doe",
//    	  "email": "john.doe@example.com",
//    	  "phone": "+1234567890",
//    	  "trafficSources": "Social Media, Paid Ads",
//    	  "affiliateType": "INDIVIDUAL",
//    	  "companyName": "Doe Affiliates",
//    	  "websiteUrl": "https://www.doeaffiliates.com",
//    	  "billingAddress": "123 Affiliate Lane, Suite 100",
//    	  "totalEarnings": 15000.50,
//    	  "status": "ACTIVE",
//    	  "address": "123 Affiliate Lane",
//    	  "city": "New York",
//    	  "state": "NY",
//    	  "country": "USA",
//    	  "postalCode": "10001"
//    	}
	@PostMapping("/affiliates")
	public ResponseEntity<Affiliate> createAffiliate(@RequestBody Affiliate affiliate) {
		Affiliate createdAffiliate = affiliateService.createAffiliate(affiliate);
		return ResponseEntity.ok(createdAffiliate);
	}

	@PutMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> updateAffiliate(@PathVariable Long id, @RequestBody Affiliate affiliate) {
		Affiliate updatedAffiliate = affiliateService.updateAffiliate(id, affiliate);
		return ResponseEntity.ok(updatedAffiliate);
	}

	@GetMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> getAffiliateById(@PathVariable Long id) {
		Affiliate affiliate = affiliateService.getAffiliateById(id);
		return ResponseEntity.ok(affiliate);
	}

//	@GetMapping
//	public ResponseEntity<List<Affiliate>> getAllAffiliates() {
//		List<Affiliate> affiliates = affiliateService.getAllAffiliates();
//		return ResponseEntity.ok(affiliates);
//	}

	@GetMapping("/affiliates")
	public String getAllAffilates(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, Model model) {

		// Create Pageable instance with page and size
		Pageable pageable = PageRequest.of(page, size);

		// Fetch the paginated list of affiliates
		Page<Affiliate> pageContent = affiliateService.getAllAffiliates(pageable);

//		System.out.println("This--------->");
//		System.out.println(pageContent.getNumber());
//		System.out.println(pageContent.getNumberOfElements());
//		System.out.println(pageContent.getSize());
//		System.out.println(pageContent.getTotalElements());
//		System.out.println(pageContent.getTotalPages());
//		System.out.println(pageContent.hasContent());

		// Get total number of affiliates for pagination control
		long count = affiliateService.getAffiliateCount();

		int totalPages = pageContent.getTotalPages();
		int currentPage = pageContent.getNumber();
		int pageWindow = 5; // How many pages to display in the pagination window

		int startPage = Math.max(1, currentPage - (pageWindow / 2));
		int endPage = Math.min(totalPages, currentPage + (pageWindow / 2));

		// Ensure there are always 5 pages displayed, adjusting start or end if
		// necessary
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
		model.addAttribute("affiliates", pageContent.getContent());
		model.addAttribute("affiliateCount", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageContent.getTotalPages());
		model.addAttribute("pageSize", size);

		return "panel/affiliatelist"; // Return the view name
	}

	@DeleteMapping("/affiliates/{id}")
	public ResponseEntity<Void> deleteAffiliate(@PathVariable Long id) {
		affiliateService.deleteAffiliate(id);
		return ResponseEntity.noContent().build();
	}
}