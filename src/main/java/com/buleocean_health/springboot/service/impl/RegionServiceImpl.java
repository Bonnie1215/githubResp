package com.buleocean_health.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buleocean_health.springboot.dto.AreasDto;
import com.buleocean_health.springboot.mapper.RegionMapper;
import com.buleocean_health.springboot.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService{
	
	@Autowired
	private RegionMapper regionMapper;

	@Override
	public List<AreasDto> queryList(double parentId) {
		return regionMapper.queryList(parentId);
	}

}
