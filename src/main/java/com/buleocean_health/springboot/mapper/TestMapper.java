package com.buleocean_health.springboot.mapper;

import org.apache.ibatis.annotations.Select;

import com.buleocean_health.springboot.domain.Test;
import com.buleocean_health.springboot.utils.MyMapper;

public interface TestMapper extends MyMapper<Test> {

	@Select("select * from test where name=#{name}")
	Test selectUserByUsername(String name);
}