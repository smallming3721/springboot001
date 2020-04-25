package com.hqyj.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.common.MyResult;
import com.hqyj.modules.test.pojo.User;
import com.hqyj.modules.test.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	
	/**
	 * 127.0.0.1/api/users
	 */
	@RequestMapping("/users")
	public List<User> getUserAll(){
		return userServiceImpl.selUserAll();
	}
	
	/**
	 * 127.0.0.1/api/user?userName=tom&password=123
	 */
	@RequestMapping("/user")
	public User getUserByUserNameAndPassword(User user){
		return userServiceImpl.selUserByUserNameAndPassword(user);
	}
	
	/**
	 * 127.0.0.1/api/user
	 * {"userName":"jack","password":"111","createDate":"2020-04-25"}
	 */
	@PostMapping(value="/user",consumes="application/json")
	public MyResult<User> insUser(@RequestBody User user){
		return userServiceImpl.insUser(user);
	}
	
	/**
	 * 127.0.0.1/api/user
	 * {"userId":"1","userName":"tom1","password":"000","createDate":"2020-04-25"}
	 */
	@PutMapping(value="/user",consumes="application/json")
	public MyResult<User> updUser(@RequestBody User user){
		return userServiceImpl.updUser(user);
	}
	
	/**
	 * 127.0.0.1/api/user/3
	 */
	@DeleteMapping("/user/{userId}")
	public MyResult<Object> delUser(@PathVariable String userId){
		return userServiceImpl.delUser(userId);
	}
}
