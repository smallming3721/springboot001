package com.hqyj.modules.test.service;

import java.util.List;

import com.hqyj.common.MyResult;
import com.hqyj.modules.test.pojo.User;

public interface UserService {

	
	List<User> selUserAll();
	
	User selUserByUserNameAndPassword(User user);
	
	MyResult<User> insUser(User user);
	
	MyResult<User> updUser(User user);
	
	MyResult<Object> delUser(String userId);
}
