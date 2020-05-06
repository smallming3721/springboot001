package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.modules.common.vo.Result;
import com.hqyj.modules.common.vo.Result.ResultStatus;
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
	public Result<City> insCity(City city) { 
		Result<City> mr=null;
		try{
			cityMapper.insCity(city);
			mr=new Result<City>(ResultStatus.SUCCESS.status,"insert success!!",city);
		}catch(Exception e){
			mr=new Result<City>(ResultStatus.FAILED.status,e.getMessage());
			e.printStackTrace();
		}
		return mr;
	}

	@Override
	public Result<City> updCity(City city) {
		Result<City> mr=null;
		try{
			cityMapper.updCity(city);
			mr=new Result<City>(ResultStatus.SUCCESS.status,"update success!!",city);
		}catch(Exception e){
			mr=new Result<City>(ResultStatus.FAILED.status,e.getMessage());
			e.printStackTrace();
		}
		return mr;
	}

	@Override
	@Transactional(noRollbackFor=ArithmeticException.class)
	public Result<Object> delCity(int cityId) {
		Result<Object> mr=new Result<Object>(ResultStatus.SUCCESS.status,"delete success!!");
		cityMapper.delCity(cityId);
		//int i=1/0;
		return mr;
	}
	
	
}
