package com.hqyj.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.modules.test.pojo.City;
import com.hqyj.modules.test.service.CityService;

@RestController
@RequestMapping("/api/city")
public class CityController {

	@Autowired
	private CityService cityServiceImpl;
	
	/**
	 * http://127.0.0.1/api/city/getCityById/522
	 */
	@RequestMapping("/getCityById/{countryId}")
	public List<City> getCity(@PathVariable int countryId){
		return cityServiceImpl.selCityById(countryId);
		
	}
}
