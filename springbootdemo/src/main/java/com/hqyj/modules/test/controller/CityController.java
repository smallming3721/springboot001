package com.hqyj.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.common.Result;
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
	 * @RequestAttribute 写不写都一样吧（默认）
	 */
	@RequestMapping("/cities/getPageCitiesByCountryId")
	public PageInfo<City> getPageCitiesByCountryId
		(@RequestAttribute int currentPage,int pageSize,int countryId){
		
		return cityServiceImpl.selPageCitiesByCountryId(currentPage, pageSize, countryId);
	}
	/**
	 * http://127.0.0.1/api/city?cityName=test1&localCityName=localtest
	 */
	@RequestMapping("/city")
	public City getCityByName(String cityName, String localCityName){
		return cityServiceImpl.selCityByName(cityName, localCityName);
	}
	
	/**
	 * http://127.0.0.1/api/city
	 * {"cityId":"1","cityName":"test1","countryId":"522"}
	 */
	@PostMapping(value="/city", consumes="application/json")
	public Result<City> insCity(@RequestBody City city){
		return cityServiceImpl.insCity(city);
	}
	
	
	/**
	 * http://127.0.0.1/api/city
	 * @ModelAttribute （默认？？）不写也能接受参数？？？
	 */
	@PutMapping(value="/city",consumes="application/x-www-form-urlencoded")
	public  Result<City> updCity(City city){
		return cityServiceImpl.updCity(city);
	}
	
	/**
	 * http://127.0.0.1/api/city/test
	 * 测试用的，post接收参数修改数据
	 */
	@PostMapping("/city/test")
	public  Result<City> updCitytest(City city){
		return cityServiceImpl.updCity(city);
	}
	
	/**
	 * http://127.0.0.1/api/city/2261
	 */
	@DeleteMapping(value="/city/{cityId}")
	public  Result<Object> delCity(@PathVariable int cityId){
		return cityServiceImpl.delCity(cityId);
	}
	
	
}
