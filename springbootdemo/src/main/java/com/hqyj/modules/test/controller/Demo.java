package com.hqyj.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.modules.test.pojo.Users;

//@RestController=@ResponseBody+@Controller
@RestController
@RequestMapping("/api/test")
public class Demo {
	
	/**
	 * http://127.0.0.1/api/test/users
	 * @return
	 */
	@Autowired
	private Users users;
	@RequestMapping("/users")
	public Users getUsers(){
		return users;
	}
	
	
	/**
	 * https://127.0.0.1/api/test/ssl
	 * @return
	 */
	@RequestMapping("/ssl")
	public String ssl(){
		return "ssl test";
	}
	
	/**
	 * http://127.0.0.1/api/test/log
	 * @return
	 */
	private final static Logger LOGGER=LoggerFactory.getLogger(Demo.class);
	@RequestMapping("/log")
	public String log(){
		//level: TRACE<DEBUG<INFO<WARN<ERROR
		LOGGER.trace("this is trace");
		LOGGER.debug("this is debug");
		LOGGER.info("this is info");
		LOGGER.warn("this is warn");
		LOGGER.error("this is error");
		return "log test";
	}
	
	/**
	 * http://127.0.0.1/api/test/demo1
	 * @return
	 */
	@Value("${com.hqyj.name}")
	private String name;
	@Value("${com.hqyj.age}")
	private int age;
	@Value("${com.hqyj.user}")
	private String user;
	@RequestMapping("/demo1")
	@ResponseBody
	public String demo1(){
		
		return "name="+name+"-->age="+age+"-->user="+user;
	}
	/**
	 * http://127.0.0.1/api/test/demo
	 * @return
	 */
	@RequestMapping("/demo")
	public String demo(){
		return "hello spring boot";
	}
}
