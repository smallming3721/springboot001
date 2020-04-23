package com.hqyj.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.common.MyResult;
import com.hqyj.modules.test.pojo.City;
import com.hqyj.modules.test.pojo.Country;

public interface CityService {

	
	List<City> selCitiesByCountryId(int countryId);
	
	PageInfo<City> selPageCitiesByCountryId
		(int currentPage,int pageSize,int countryId);
	
	
	MyResult<City> insCity(City city);
}
