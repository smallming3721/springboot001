package com.hqyj.modules.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hqyj.modules.test.pojo.City;

@Mapper
public interface CityMapper {

	@Select("select * from m_city where country_id=#{countryId}")
	List<City> selCityById(int countryId);
}
