package com.buleocean_health.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buleocean_health.springboot.dto.AreasDto;
import com.buleocean_health.springboot.mapper.RegionMapper;

/**
 * 获得省市区列表
 * @author huyanqiu
 *
 */
@RestController
@RequestMapping("/region")
public class RegionController {
	
	@Autowired
	private RegionMapper regionMapper;
	
	@RequestMapping("/queryList")
	public List<AreasDto> queryList() {
		List<AreasDto> areasList = regionMapper.queryList(1);
		return areasList;
	}
	
}
