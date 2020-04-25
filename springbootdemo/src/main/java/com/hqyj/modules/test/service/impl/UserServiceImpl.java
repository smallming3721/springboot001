package com.hqyj.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.common.MyResult;
import com.hqyj.common.MyResult.MyEnum;
import com.hqyj.modules.test.mapper.UserMapper;
import com.hqyj.modules.test.pojo.User;
import com.hqyj.modules.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> selUserAll() {
		return userMapper.selUserAll();
	}

	@Override
	public User selUserByUserNameAndPassword(User user) {
		return userMapper.selUserByUserNameAndPassword(user);
	}

	@Override
	public MyResult<User> insUser(User user) {
		MyResult<User> result = null;
		try {
			userMapper.insUser(user);
			result=new MyResult<User>(MyEnum.SUCCESS.status,"insert success",user);
		} catch (Exception e) {
			result=new MyResult<User>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MyResult<User> updUser(User user) {
		MyResult<User> result = null;
		try {
			userMapper.updUser(user);
			result=new MyResult<User>(MyEnum.SUCCESS.status,"update success",user);
		} catch (Exception e) {
			result=new MyResult<User>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MyResult<Object> delUser(String userId) {
		MyResult<Object> result = null;
		try {
			userMapper.delUser(userId);
			result=new MyResult<Object>(MyEnum.SUCCESS.status,"delete success");
		} catch (Exception e) {
			result=new MyResult<Object>(MyEnum.FAILD.status,e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
