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

import com.hqyj.modules.test.pojo.User;

@Mapper
public interface UserMapper {
	
	@Select("select *  from user")
	@ResultMap(value="resultUser")
	List<User> selUserAll();
	
	@Select("select *  from user where user_name = #{userName} and password=#{password} limit 1")
	@Results(id="resultUser",value={
		@Result(id=true,column="user_id",property="userId"),
		@Result(column="user_id",property="roles",many=
		@Many(select="com.hqyj.modules.test.mapper.RoleMapper.selRoleByUserId"))
			})
	User selUserByUserNameAndPassword(User user);
	
	//根据roleId在user_role表中查询user_id，在根据user_id查询user数据
	@Select("select *  from user where user_id in "
			+ "(select user_id from user_role where role_id=#{roleId})")
	List<User> selUserByRoleId(String roleId);
	
	@Insert("insert into user values(default,#{userName},#{password},#{createDate})")
	@Options(useGeneratedKeys=true,keyColumn="user_id",keyProperty="userId")
	void insUser(User user);
	
	@Update("update user set user_name=#{userName},password=#{password} where user_id=#{userId}")
	void updUser(User user);
	
	@Delete("delete from user where user_id=#{userId}")
	void delUser(String userId);

}
