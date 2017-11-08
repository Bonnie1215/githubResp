package com.buleocean_health.springboot.excel.model;

/**
 * excel标题名称以及顺序
 * @author huyanqiu
 *
 */
public class ExcelHeader implements Comparable<ExcelHeader>{

	// excel的标题名称
	private String title;
	// 每一个标题的顺序
	private int order;
	//  说明对应方法名称
	private String methodName;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * 比较大小
	 */
	@Override
	public int compareTo(ExcelHeader o) {
		return this.order>o.order?1:(this.order==o.order?0:-1);
	}
	
	public ExcelHeader() {
		super();
	}
	public ExcelHeader(String title, int order, String methodName) {
		super();
		this.title = title;
		this.order = order;
		this.methodName = methodName;
	}
	
}
