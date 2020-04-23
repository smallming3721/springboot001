package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.modules.test.mapper.CountryMapper;
import com.hqyj.modules.test.pojo.Country;
import com.hqyj.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryMapper countryMapper;
	
	@Override
	public Country selCountryByCountryId(int countryId) {
		return countryMapper.selCountryByCountryId(countryId);
	}
	
	@Override
	public Country selCountryByCountryName(String countryName) {
		return countryMapper.selCountryByCountryName(countryName);
	}
	
	

}
