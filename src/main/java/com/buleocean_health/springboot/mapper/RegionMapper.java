package com.buleocean_health.springboot.mapper;

import java.util.List;

import com.buleocean_health.springboot.domain.Region;
import com.buleocean_health.springboot.dto.AreasDto;
import com.buleocean_health.springboot.utils.MyMapper;

public interface RegionMapper extends MyMapper<Region> {

	List<AreasDto> queryList(double parentId);
}