package com.wahenoor.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.wahenoor.helper.AppConstants;

@ControllerAdvice
public class RootController {

	@ModelAttribute
	public void addGlobalAttributes(Model model) {
		model.addAttribute("AppName", AppConstants.APP_NAME);
	}
}