package com.buleocean_health.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buleocean_health.springboot.domain.Test;
import com.buleocean_health.springboot.mapper.TestMapper;
import com.buleocean_health.springboot.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestMapper testMapper;
	
	@Override
	public Test selectUserByUsername(String name) {
		return testMapper.selectUserByUsername(name);
	}

}
