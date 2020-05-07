package com.hqyj.scProduct.modules.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

	@RequestMapping("/testDesc1")
	public String productDesc(){
		return "This is product describe.";
	}
}
