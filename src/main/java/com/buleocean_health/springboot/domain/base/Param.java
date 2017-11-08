package com.buleocean_health.springboot.domain.base;

import java.util.List;

/**
 * 字典 
 * @author huyanqiu
 *
 */
public class Param {
	
	private String name;
	private String value;
	private List<ParamItem> items;
	
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
	public List<ParamItem> getItems() {
		return items;
	}
	public void setItems(List<ParamItem> items) {
		this.items = items;
	}
	
}
