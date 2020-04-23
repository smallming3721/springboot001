package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.common.MyResult;
import com.hqyj.common.MyResult.MyEnum;
import com.hqyj.modules.test.mapper.CityMapper;
import com.hqyj.modules.test.pojo.City;
import com.hqyj.modules.test.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;

	@Override
	public List<City> selCitiesByCountryId(int countryId) {
		return cityMapper.selCitiesByCountryId(countryId);
	}

	@Override
	public PageInfo<City> selPageCitiesByCountryId(int currentPage, int pageSize, int countryId) {
		System.out.println(pageSize);
		PageHelper.startPage(currentPage, pageSize);
		List<City> list = cityMapper.selCitiesByCountryId(countryId);
		return new PageInfo<City>(list);
	}

	@Override
	public MyResult<City> insCity(City city) { 
		MyResult<City> mr=null;
		try{
			cityMapper.insCity(city);
			mr=new MyResult<City>(MyEnum.SUCCESS.status,"insert success!!",city);
		}catch(Exception e){
			mr=new MyResult<City>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return mr;
	}
}
