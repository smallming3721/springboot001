package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.modules.test.mapper.CountryMapper;
import com.hqyj.modules.test.pojo.Country;
import com.hqyj.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryMapper countryMapper;
	@Override
	public List<Country> selCountryById(int countryId) {
		return countryMapper.selCountryById(countryId);
	}
	@Override
	public List<Country> selCountryByName(String countryName) {
		// TODO Auto-generated method stub
		return countryMapper.selCountryByName(countryName);
	}

}
