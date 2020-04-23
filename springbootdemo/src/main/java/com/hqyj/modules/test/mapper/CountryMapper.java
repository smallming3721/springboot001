package com.hqyj.modules.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.hqyj.modules.test.pojo.Country;

@Mapper
public interface CountryMapper {

	@Select("select * from m_country where country_id=#{countryId}")
	@Results(id="myCountry",value={
			@Result(column="country_id",property="countryId"),
			@Result(column="country_id",property="cities",
				many=@Many(select="com.hqyj.modules.test.mapper.CityMapper.selCityById"))
			})
	List<Country> selCountryById(int countryId);
	
	
	@Select("select * from m_country where country_name=#{countryName}")
	@ResultMap("myCountry")
	List<Country> selCountryByName(String countryName);
}
