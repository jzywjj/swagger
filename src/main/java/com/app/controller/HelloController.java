package com.app.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(tags = "用户信息相关的接口")
@RestController
public class HelloController {
	
	@ApiOperation("获取用户信息")
	@ApiImplicitParams(
		{
			@ApiImplicitParam(name = "pid",value = "用户的pid",dataTypeClass = String.class,paramType = "header")
		}
	)
	@RequestMapping("/api/findAllUser")
	public User findAllUser(@RequestBody User user,String pid) {
		
		return user;
	}
}
