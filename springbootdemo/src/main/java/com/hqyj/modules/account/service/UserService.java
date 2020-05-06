package com.hqyj.modules.account.service;


import com.github.pagehelper.PageInfo;
import com.hqyj.common.Result;
import com.hqyj.common.SearchVo;
import com.hqyj.modules.account.pojo.User;

public interface UserService {

Result<User> insertUser(User user);
	
	User getUserByUserName(String userName);
	
	Result<User> login(User user);
	
	void logout();
	
	PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
	
	User getUserById(int userId);
	
	Result<User> updateUser(User user);
	
	Result<User> deleteUser(int userId);
}