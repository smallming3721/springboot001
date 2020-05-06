package com.hqyj.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

	@RequestMapping("/dashboard")
	public String dashboard(){
		return "index";
	}
	
	@RequestMapping("/403")
	public String errorPage403() {
		return "index";
	}
}
