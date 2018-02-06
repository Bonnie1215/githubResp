package com.buleocean_health.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buleocean_health.springboot.domain.Test;
import com.buleocean_health.springboot.domain.annotation.Auth;
import com.buleocean_health.springboot.domain.base.Param;
import com.buleocean_health.springboot.domain.base.SysResult;
import com.buleocean_health.springboot.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/hello")
	public String index() {
		return "hello spring boot";
	}
	
	@RequestMapping(value="/hello1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SysResult selectUserByUsername() {
		Test test = testService.selectUserByUsername("bonnie");
		System.out.println("test:"+test.getName());
		return SysResult.ok("springboot mybatis整合成功："+test.getName());
	}
	
	@RequestMapping("/myConstraintValidator")
	@Auth
	public String myConstraintValidator(@RequestBody @Valid Param param) {
		System.out.println("888888888888888888888888");
		System.out.println("name:"+param.getName());
		System.out.println("value:"+param.getValue());
		return "hello spring boot";
	}
	
}
