package com.hqyj.modules.test.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.modules.test.service.CityService;
import com.hqyj.modules.test.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private CityService cityServiceImpl;
	
	
	/**
	 * http://127.0.0.1/test/index
	 */
	@RequestMapping("/index")
	public String index(ModelMap map){
		//map.addAttribute("template", "test/index");
		map.addAttribute("text1", "文本内容");
		map.addAttribute("thif", true);
		map.addAttribute("checkbox", "checkbox");
		map.addAttribute("bdUrl", "http://www.baidu.com");
		map.addAttribute("imgurl", "https://himg.bdimg.com/sys/portrait/hotitem/wildkid/2");
		map.addAttribute("user",userServiceImpl.selUserAll().get(0) );
		map.addAttribute("city",cityServiceImpl.selCitiesByCountryId(522).get(0) );
		map.addAttribute("updateCityUri","/api/city/test" );
		map.addAttribute("cities",cityServiceImpl.selCitiesByCountryId(522).
				stream().limit(10).collect(Collectors.toList()) );
		
		return "index";
	}
	
	/**
	 * 127.0.0.1/test/testFilter?key=fuck
	 * 测试过滤器过滤字符
	 */
	@RequestMapping("/testFilter")
	@ResponseBody
	public String testFilter(HttpServletRequest req,String key){
		String string = req.getParameter("key");
		return "testFilter---"+string+"---"+key;
	}

}
