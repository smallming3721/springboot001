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

import com.hqyj.modules.test.pojo.Role;

@Mapper
public interface RoleMapper {

	@Select("select * from role")
	@ResultMap(value="resultRole")
	List<Role> selRoleAll();
	
	@Select("select * from role where role_id=#{roleId}")
	@Results(id="resultRole",value={
		@Result(id=true,column="role_id",property="roleId"),
		@Result(column="role_id",property="users",many=
		@Many(select="com.hqyj.modules.test.mapper.UserMapper.selUserByRoleId")),
		@Result(column="role_id",property="resources",many=
		@Many(select="com.hqyj.modules.test.mapper.ResourceMapper.selResourceByRoleId"))
			})
	Role selRoleByRoleId(String roleId);
	
	//通过user_id查询表user_role中的role_id，再根据role_id查role数据
	@Select("select * from role where role_id in "
			+ "(select role_id from user_role where user_id=#{userId})")
	List<Role> selRoleByUserId(String userId);
	
	//通过resource_id查询表role_resource中的role_id，再根据role_id查role数据
	@Select("select * from role where role_id in "
			+ "(select role_id from role_resource where resource_id=#{resourceId})")
	List<Role> selRoleByResourceId(String resourceId);
	
	@Insert("insert into role values(default,#{roleName})")
	@Options(useGeneratedKeys=true,keyColumn="role_id",keyProperty="roleId")
	void insRole(Role role);
	
	@Update("update role set role_name=#{roleName} where role_id=#{roleId}")
	void updRole(Role role);
	
	@Delete("delete from role where role_id=#{userId}")
	void delRole(String roleId);
}
