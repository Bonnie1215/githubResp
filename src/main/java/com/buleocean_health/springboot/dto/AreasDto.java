package com.buleocean_health.springboot.dto;

import java.util.List;

/**
 * 省市区
 * @author huyanqiu
 *
 */
public class AreasDto {
	
	private String name;
	private String value;
	private List<AreasDto> children;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<AreasDto> getChildren() {
		return children;
	}
	public void setChildren(List<AreasDto> children) {
		this.children = children;
	}
	
	
}
