package com.hqyj.modules.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.hqyj.modules.test.pojo.City;

@Mapper
public interface CityMapper {

	@Select("select * from m_city where country_id=#{countryId}")
	List<City> selCitiesByCountryId(int countryId);
	
	@Insert("insert into m_city values"
			+ "(default,#{cityName},#{localCityName},#{countryId},#{district},"
			+ "#{population},#{dateModified},#{dateCreated})")
	@Options(useGeneratedKeys=true,keyColumn="city_id",keyProperty="cityId")
	void insCity(City city);
}
