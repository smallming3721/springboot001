package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public City selCityByName(String cityName, String localCityName) {
		//return cityMapper.selCityByName(cityName, localCityName);
		return cityMapper.selCityByName2(cityName, localCityName);
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

	@Override
	public MyResult<City> updCity(City city) {
		MyResult<City> mr=null;
		try{
			cityMapper.updCity(city);
			mr=new MyResult<City>(MyEnum.SUCCESS.status,"update success!!",city);
		}catch(Exception e){
			mr=new MyResult<City>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return mr;
	}

	@Override
	@Transactional(noRollbackFor=ArithmeticException.class)
	public MyResult<Object> delCity(int cityId) {
		MyResult<Object> mr=new MyResult<Object>(MyEnum.SUCCESS.status,"delete success!!");
		cityMapper.delCity(cityId);
		//int i=1/0;
		return mr;
	}
	
	
}
