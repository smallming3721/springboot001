package com.hqyj.modules.test.service;

import java.util.List;

import com.hqyj.modules.test.pojo.Country;

public interface CountryService {

	List<Country> selCountryById(int countryId);
	
	
	List<Country> selCountryByName(String countryName);
}
