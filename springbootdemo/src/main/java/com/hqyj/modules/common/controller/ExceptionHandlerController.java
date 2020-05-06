package com.hqyj.modules.common.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.modules.common.vo.Result;
import com.hqyj.modules.common.vo.Result.ResultStatus;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(value=AuthorizationException.class)
	@ResponseBody
	public Result<String> ExceptionPage403() {
		return new Result<String>(ResultStatus.FAILED.status, "", "/common/403");
	}

}
