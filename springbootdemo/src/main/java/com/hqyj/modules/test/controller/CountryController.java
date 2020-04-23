package com.hqyj.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.modules.test.pojo.Country;
import com.hqyj.modules.test.service.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController {
	@Autowired
	private CountryService countryServiceImpl;

	/**
	 * http://127.0.0.1/api/country/getCountryById/522
	 */
	@RequestMapping("/getCountryById/{countryId}")
	public List<Country> getCountry(@PathVariable int countryId){
		return countryServiceImpl.selCountryById(countryId);
	}
	
	/**
	 * http://127.0.0.1/api/country/getCountryByName/China
	 */
	@RequestMapping("/getCountryByName/{countryName}")
	public List<Country> getCountry(@PathVariable String countryName){
		return countryServiceImpl.selCountryByName(countryName);
	}
}
