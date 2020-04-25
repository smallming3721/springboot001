package com.hqyj.modules.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.modules.test.pojo.City;

@Mapper
public interface CityMapper {

	@Select("select * from m_city where country_id=#{countryId}")
	List<City> selCitiesByCountryId(int countryId);
	
	/**
	 * 参数为实体类直接写属性名	#{cityName}，map集合写key	#{key1}
	 * 单参数时随便怎么写	eg：#{qwer}
	 * 多参数传递有三种方式：
	 * 第一种：第一个参数-->#{arg0} 第二个参数-->#{arg1}，依次递增
	 * 	eg：select * from m_city where city_name=#{arg0} and local_city_name=#{arg1}
	 * 第二种：第一个参数-->#{param1} 第二个参数-->#{param2}，依次递增
	 * 	eg：select * from m_city where city_name=#{param1} and local_city_name=#{param2}
	 * 第三种：使用@Param注解注入
	 * 	eg：@Param("cityName")String cityName,@Param("localCityName")String localCityName
	 */
	@Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}")
	City selCityByName(@Param("cityName")String cityName,@Param("localCityName")String localCityName);
	
	@Select("<script>select * from m_city"
			+ "<where>"
			+ "<if test='cityName !=\"\"'>"
			+ "and city_name=#{cityName}"
			+ "</if>"
			+ "<if test='localCityName !=\"\"'>"
			+ "and local_city_name=#{localCityName}"
			+ "</if>"
			+ "</where>"
			+ "limit 1"
			+ "</script>")
	City selCityByName2(@Param("cityName")String cityName,@Param("localCityName")String localCityName);
	
	
	@Insert("insert into m_city values"
			+ "(default,#{cityName},#{localCityName},#{countryId},#{district},"
			+ "#{population},#{dateModified},#{dateCreated})")
	@Options(useGeneratedKeys=true,keyColumn="city_id",keyProperty="cityId")
	void insCity(City city);
	
	@Update("update m_city set city_name=#{cityName} where city_id=#{cityId}")
	void updCity(City city);
	
	@Update("delete from m_city where city_id=#{cityId}")
	void delCity(int cityId);
}
