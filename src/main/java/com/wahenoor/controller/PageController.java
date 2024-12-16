package com.wahenoor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping("/base")
	public String base() {
		return "base";
	}

	@RequestMapping("/aboutus")
	public String aboutus() {
		return "aboutus";
	}

	@RequestMapping("/service")
	public String service() {
		return "service";
	}

//	@RequestMapping("/campaigns")
//	public String campaigns() {
//		return "campaigns";
//	}

	@RequestMapping("/blogs")
	public String blogs() {
		return "blogs";
	}

	@RequestMapping("/contactus")
	public String contactus() {
		return "contactus";
	}

	@RequestMapping("/afilliatemarketingdetail")
	public String afilliatemarketingdetail() {
		return "afilliatemarketingdetail";
	}

	@RequestMapping("/commissionmodels")
	public String commissionmodels() {
		return "commissionmodels";
	}

	@RequestMapping("/detail")
	public String detail() {
		return "detail";
	}

}
