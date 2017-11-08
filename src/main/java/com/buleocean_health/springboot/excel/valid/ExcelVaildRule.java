package com.buleocean_health.springboot.excel.valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel表的校验规则
 * @author huyanqiu
 *
 */
public class ExcelVaildRule extends HashMap<Integer, Validator<Object, String>>{

	private static final long serialVersionUID = -655753212740991650L;
	
	private static final String NO_CHECK_ROW = "nocheckrows";
	private static final String NO_CHECK_CELL = "nocheckcells";
	// 需要过滤行、单元格集合
	private Map<Object, List<Integer>> filters = new HashMap<Object, List<Integer>>();
	
	public static ExcelVaildRule createRule() {
		return new ExcelVaildRule();
	}
	
	/**
	 * 添加校验规则
	 * @param index
	 * @param validator
	 * @return
	 */
	public ExcelVaildRule addRule(Integer index, Validator<Object, String> validator) {
		put(index, validator);
		return this;
	}
	
	/**
	 * 添加不需要校验行
	 * @return
	 */
	public ExcelVaildRule setNoCheckRow(Integer...rows) {
		setNoCheckRows(Arrays.asList(rows));
		return this;
	}
	
	/**
	 * 指定sheet中不需要校验行
	 * @param sheet
	 * @param rows
	 * @return
	 */
	public ExcelVaildRule setNoCheckRow(Integer sheet, Integer...rows) {
		setNoCheckRows(sheet, Arrays.asList(rows));
		return this;
	}
	
	/**
	 * 获取不需要校验的行
	 * @return
	 */
	public List<Integer> getNoCheckRows() {
		List<Integer> noCheckRows = filters.get(NO_CHECK_ROW);
		if (noCheckRows==null) {
			noCheckRows = new ArrayList<Integer>();
			noCheckRows.add(0);
		}
		return noCheckRows;
	} 

	/**
	 * 获取sheet页对应的校验行
	 * @param asList
	 */
	public List<Integer> getNoCheckRows(Integer sheet) {
		return filters.get(sheet);
	}
	
	/**
	 * 设置不需要校验的单元格
	 * @param cells
	 * @return
	 */
	public ExcelVaildRule setNoCheckCells(Integer...cells) {
		setNoCheckCells(Arrays.asList(cells));
		return this;
	}
	
	/**
	 * 获取需要校验行
	 * @param asList
	 */
	public List<Integer> getNoCheckCells() {
		return filters.get(NO_CHECK_CELL);
	}
	
	/**
	 * 获取某个校验规则的错误提示信息
	 * @param index
	 * @param value
	 * @return
	 */
	public String valid(Integer index, Object value){
		Validator<Object, String> validator = get(index);
		if (validator==null) {
			return null;
		}
		String msg = validator.apply(value);
		return msg;
	}
	
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	private void setNoCheckRows(List<Integer> asList) {
		filters.put(NO_CHECK_ROW, asList);
	}
	private void setNoCheckRows(Integer sheet, List<Integer> asList) {
		filters.put(sheet, asList);
	}
	private void setNoCheckCells(List<Integer> aList) {
		filters.put(NO_CHECK_CELL, aList);
	}
	
}
