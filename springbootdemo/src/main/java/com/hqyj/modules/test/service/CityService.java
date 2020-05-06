package com.hqyj.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.common.Result;
import com.hqyj.modules.test.pojo.City;
import com.hqyj.modules.test.pojo.Country;

public interface CityService {

	
	List<City> selCitiesByCountryId(int countryId);
	
	City selCityByName(String cityName,String localCityName);
	
	
	PageInfo<City> selPageCitiesByCountryId
		(int currentPage,int pageSize,int countryId);
	
	Result<City> insCity(City city);
	
	Result<City> updCity(City city);
	
	Result<Object> delCity(int cityId);
}
