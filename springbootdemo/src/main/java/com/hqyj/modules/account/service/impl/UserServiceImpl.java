package com.hqyj.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.common.Result;
import com.hqyj.common.Result.ResultStatus;
import com.hqyj.common.SearchVo;
import com.hqyj.modules.account.mapper.UserMapper;
import com.hqyj.modules.account.mapper.UserRoleMapper;
import com.hqyj.modules.account.pojo.Role;
import com.hqyj.modules.account.pojo.User;
import com.hqyj.modules.account.service.UserService;
import com.hqyj.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	@Transactional
	public Result<User> insertUser(User user) {
		Result<User> result = new Result<User>(ResultStatus.SUCCESS.status, "");
		
		User userTemp = userMapper.getUserByUserName(user.getUserName());
		if (userTemp != null) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage("User name is repeat.");
			return result;
		}
		
		user.setCreateDate(new Date());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		userMapper.insertUser(user);
		
		List<Role> roles = user.getRoles();
		if (roles != null) {
			userRoleMapper.deletUserRoleByUserId(user.getUserId());
			for (Role role : roles) {
				userRoleMapper.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		
		try {
//			Subject subject = SecurityUtils.getSubject();
//			UsernamePasswordToken usernamePasswordToken = 
//					new UsernamePasswordToken(user.getUserName(), user.getPassword());
//			
//			subject.login(usernamePasswordToken);
//			subject.checkRoles();
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public User getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

	@Override
	public Result<User> login(User user) {
		Result<User> result = new Result<User>(ResultStatus.SUCCESS.status, "");
		
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken usernamePasswordToken = 
					new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getPassword()));
			usernamePasswordToken.setRememberMe(user.isRememberMe());
			
			subject.login(usernamePasswordToken);
			subject.checkRoles();
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public void logout() {
//		Subject subject = SecurityUtils.getSubject();
//		subject.logout();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo(searchVo);
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(userMapper.getUsersBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public User getUserById(int userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	@Transactional
	public Result<User> updateUser(User user) {
		
		User userTemp = userMapper.getUserByUserName(user.getUserName());
		if (userTemp != null && user.getUserId() != userTemp.getUserId()) {
			return new Result<User>(ResultStatus.FAILED.status, "User name is repeat.");
		}
		
		userMapper.updateUser(user);
		List<Role> roles = user.getRoles();
		if (!roles.isEmpty()) {
			userRoleMapper.deletUserRoleByUserId(user.getUserId());
			for (Role role : roles) {
				userRoleMapper.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		
		return new Result<User>(ResultStatus.SUCCESS.status, "");
	}

	@Override
	@Transactional
	public Result<User> deleteUser(int userId) {
		userMapper.deleteUser(userId);
		return new Result<User>(ResultStatus.SUCCESS.status, "");
	}

}
