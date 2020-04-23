package com.hqyj.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.common.MyResult;
import com.hqyj.modules.test.pojo.City;
import com.hqyj.modules.test.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	private CityService cityServiceImpl;
	
	/**
	 * http://127.0.0.1/api/cities/getCitiesByCountryId/522
	 */
	@RequestMapping("/cities/getCitiesByCountryId/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId){
		return cityServiceImpl.selCitiesByCountryId(countryId);
		
	}
	
	
	/**
	 * http://127.0.0.1/api/cities/getPageCitiesByCountryId?currentPage=1&pageSize=20&countryId=522
	 */
	@RequestMapping("/cities/getPageCitiesByCountryId")
	public PageInfo<City> getPageCitiesByCountryId
		(int currentPage,int pageSize,int countryId){
		
		return cityServiceImpl.selPageCitiesByCountryId(currentPage, pageSize, countryId);
	}
	
	/**
	 * http://127.0.0.1/api/insCity
	 * {"cityId":"1","cityName":"test1","countryId":"522"}
	 */
	@PostMapping(value="/insCity", consumes="application/json")
	public MyResult<City> insCity(@RequestBody City city){
		return cityServiceImpl.insCity(city);
	}
	
}
