package com.hqyj.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.modules.test.pojo.Country;
import com.hqyj.modules.test.service.CountryService;

@RestController
@RequestMapping("/api")
public class CountryController {
	@Autowired
	private CountryService countryServiceImpl;

	/**
	 * http://127.0.0.1/api/country/getCountryByCountryId/522
	 */
	@RequestMapping("/country/getCountryByCountryId/{countryId}")
	public Country getCountryByCountryId(@PathVariable int countryId){
		return countryServiceImpl.selCountryByCountryId(countryId);
	}
	
	/**
	 * http://127.0.0.1/api/country/getCountryByCountryName/China
	 */
	@RequestMapping("/country/getCountryByCountryName/{countryName}")
	public Country getCountryByCountryName(@PathVariable String countryName){
		return countryServiceImpl.selCountryByCountryName(countryName);
	}
	

}
