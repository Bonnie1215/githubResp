package com.buleocean_health.springboot.excel.model;

import java.util.List;

/**
 * 读取excel的结果
 * @author huyanqiu
 *
 */
public class ExcelResult {
	
	private Object tipMsg;
	private List<?> datas;
	
	public Object getTipMsg() {
		return tipMsg;
	}
	public void setTipMsg(Object tipMsg) {
		this.tipMsg = tipMsg;
	}
	public List<?> getDatas() {
		return datas;
	}
	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
	
}
