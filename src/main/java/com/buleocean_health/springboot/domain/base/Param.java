package com.buleocean_health.springboot.domain.base;

import java.util.List;
import com.buleocean_health.springboot.domain.annotation.RemoveSpace;
import com.buleocean_health.springboot.domain.valid.MyConstraint;

/**
 * 字典
 * @author huyanqiu
 * @date 2018年3月1日下午2:47:14
 * @version 版本号：1.0
 *
 */
public class Param {
	
	// 使用自定义注解
	@MyConstraint(message="自定义注解Valid使用")
	@RemoveSpace
	private String name;
	@RemoveSpace
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
	@Override
	public String toString() {
		return "Param [name=" + name + ", value=" + value + ", items=" + items + "]";
	}
	
}
