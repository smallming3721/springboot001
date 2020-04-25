package com.hqyj.modules.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.modules.test.pojo.Resource;

@Mapper
public interface ResourceMapper {

	@Select("select * from resource")
	@ResultMap(value="resultResource")
	List<Resource> selResourceAll();
	
	@Select("select * from resource where resource_id=#{resourceId}")
	@Results(id="resultResource",value={
		@Result(id=true,column="resource_id",property="resourceId"),
		@Result(column="resource_id",property="roles",many=
		@Many(select="com.hqyj.modules.test.mapper.RoleMapper.selRoleByResourceId")),
			})
	Resource selResourceByResourceId(String resourceId);
	
	//根据role_id查询role_resource表中的resource_id，再根据resource_id查询resource表的数据
	@Select("select * from resource where resource_id in "
			+ "(select resource_id from role_resource where role_id=#{roleId})")
	List<Resource> selResourceByRoleId(String roleId);
	
	@Insert("insert into resource values(default,#{resourceUri},#{resourceName},#{permission})")
	@Options(useGeneratedKeys=true,keyColumn="resource_id",keyProperty="resourceId")
	void insResource(Resource resource);
	
	@Update("update resource set resource_uri=#{resourceUri},resource_name=#{resourceName},"
			+ "permission=#{permission} where resource_id=#{resourceId}")
	void updResource(Resource resource);
	
	@Delete("delete from resource where resource_id=#{resourceId}")
	void delResource(String resourceId);
}
