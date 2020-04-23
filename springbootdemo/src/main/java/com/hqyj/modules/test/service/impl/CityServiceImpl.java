package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.modules.test.mapper.CityMapper;
import com.hqyj.modules.test.pojo.City;
import com.hqyj.modules.test.service.CityService;
@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public List<City> selCityById(int countryId) {
		// TODO Auto-generated method stub
		return cityMapper.selCityById(countryId);
	}

}
