package com.hqyj.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.modules.test.pojo.Country;

public interface CountryService {

	Country selCountryByCountryId(int countryId);
	
	
	Country selCountryByCountryName(String countryName);
	
	
	
}
