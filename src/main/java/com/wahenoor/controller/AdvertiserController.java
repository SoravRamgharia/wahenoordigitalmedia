package com.wahenoor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.domain.CategoryType;
import com.wahenoor.domain.ChannelType;
import com.wahenoor.domain.EntityStatus;
import com.wahenoor.domain.PlatformType;
import com.wahenoor.domain.RevenueModel;
import com.wahenoor.domain.TrafficType;
import com.wahenoor.dto.AdvertiserDto;
import com.wahenoor.service.AdvertiserService;

import org.springframework.ui.Model;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RestController
//@RequestMapping("/api/advertisers") 
@Controller
@RequiredArgsConstructor
public class AdvertiserController {

	private final AdvertiserService advertiserService;

	@GetMapping("/advertiserform")
	public String createAdvertiserForm(Model model) {
		model.addAttribute("entityStatus", EntityStatus.values());
		return "panel/advertiserform";
	}

//	{
//	    "name": "XYZ Corporation",
//	    "email": "xyzcorp@example.com",
//	    "password": "open123",
//	    "phone": "+1-234-567-890",
//	    "companyName": "XYZ Corp Ltd.",
//	    "websiteUrl": "https://www.xyzcorporation.com",
//	    "billingAddress": "1234 Elm Street, Springfield",
//	    "status": "PENDING",
//	    "paymentTerms": "Net 30",
//	    "address": "1234 Elm Street",
//	    "city": "Springfield",
//	    "state": "Illinois",
//	    "country": "USA",
//	    "postalCode": "62701"
//	}	

	@PostMapping("/advertisers")
	public ResponseEntity<AdvertiserDto> createAdvertiser(@RequestBody AdvertiserDto advertiserDto) {
		AdvertiserDto createdAdvertiser = advertiserService.createAdvertiser(advertiserDto);
		return new ResponseEntity<>(createdAdvertiser, HttpStatus.CREATED);
	}

	@PutMapping("/advertisers/{id}")
	public ResponseEntity<AdvertiserDto> updateAdvertiser(@PathVariable Long id,
			@RequestBody AdvertiserDto advertiserDto) {
		AdvertiserDto updatedAdvertiser = advertiserService.updateAdvertiser(id, advertiserDto);
		return new ResponseEntity<>(updatedAdvertiser, HttpStatus.OK);
	}

	@GetMapping("/advertisers/{id}")
	public ResponseEntity<AdvertiserDto> getAdvertiserById(@PathVariable Long id) {
		return advertiserService.getAdvertiserById(id)
				.map(advertiserDto -> new ResponseEntity<>(advertiserDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/advertisers")
	public String getAllAdvertisers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, Model model) {

		// Create Pageable instance with page and size
		Pageable pageable = PageRequest.of(page, size);

		// Fetch the paginated list of advertisers
		Page<Advertiser> pageContent = advertiserService.getAllAdvertisers(pageable);

//		System.out.println("This--------->");
//		System.out.println(pageContent.getNumber());
//		System.out.println(pageContent.getNumberOfElements());
//		System.out.println(pageContent.getSize());
//		System.out.println(pageContent.getTotalElements());
//		System.out.println(pageContent.getTotalPages());
//		System.out.println(pageContent.hasContent());

		// Get total number of advertisers for pagination control
		long count = advertiserService.getAdvertiserCount();

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
		model.addAttribute("advertisers", pageContent.getContent());
		model.addAttribute("advertiserCount", count);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageContent.getTotalPages());
		model.addAttribute("pageSize", size);

		return "panel/advertiserlist"; // Return the view name
	}

	@DeleteMapping("/advertisers/{id}")
	public ResponseEntity<Void> deleteAdvertiser(@PathVariable Long id) {
		advertiserService.deleteAdvertiser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}