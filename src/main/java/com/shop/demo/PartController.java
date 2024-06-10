package com.shop.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PartController {
	private static final Logger log=LoggerFactory.getLogger(PartController.class);
	
	@GetMapping("/part/chest")
	public String main(Model model) {
		return "/part/chest";
	}
}
