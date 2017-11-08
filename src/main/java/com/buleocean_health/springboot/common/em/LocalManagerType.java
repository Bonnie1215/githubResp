package com.buleocean_health.springboot.common.em;

/**
 * 本地线程变量的存储类型
 * @author huyanqiu
 *
 */
public enum LocalManagerType {
	IP(String.class)
	
	;
	
	@SuppressWarnings("rawtypes")
	private Class clz;
	
	LocalManagerType(Class<?> clz) {
		this.clz = clz;
	}
	
	@SuppressWarnings("rawtypes")
	public Class get() {
		return clz;
	}
}
