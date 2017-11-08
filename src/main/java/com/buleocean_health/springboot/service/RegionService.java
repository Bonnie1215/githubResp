package com.buleocean_health.springboot.service;

import java.util.List;

import com.buleocean_health.springboot.dto.AreasDto;

public interface RegionService {
	public List<AreasDto> queryList(double parentId);
}
