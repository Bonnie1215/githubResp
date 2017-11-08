package com.buleocean_health.springboot.excel.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.buleocean_health.springboot.excel.annotation.ExcelResource;
import com.buleocean_health.springboot.excel.model.ExcelHeader;

/**
 * 该类实现了将一组对象转换为Excel表格，并且可以从Excel表格中读取到一组List对象中
 * 该类利用了BeanUtils框架中的反射完成
 * 使用该类的前提，在相应的实体对象上通过ExcelReources来完成相应的注解
 * 
 * @author huyanqiu
 *
 */
public class ExcelUtil {

	private final String XLS = "xls";
	private final String XLSX = "xlsx";
	private final String EXT = ".lh";
	private static ExcelUtil eu = new ExcelUtil();
	
	public ExcelUtil() {}
	
	public static ExcelUtil getInstance() {
		return eu;
	}
	
	/**
	 * 获取excel对象
	 * @param in 文件流
	 * @param fileName 文件名称
	 * @return Workbook
	 * @throws IOException 
	 */
	public Workbook getWorkbook(InputStream in, String fileName) throws IOException {
		// 创建Workbook工作薄对象，表示整个excel
		if (fileName.endsWith(EXT)) {
			fileName = fileName.substring(0, fileName.indexOf(EXT));
		}
		if (fileName.endsWith(XLS)) {
			return new HSSFWorkbook(in);
		} else if (fileName.endsWith(XLSX)) {
			return new XSSFWorkbook(in);
		}
		throw new RuntimeException("文件格式错误");
	}
	
	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	public Object getCellValue(Cell c) {
		String o = null;
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                o = ""; break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(c.getBooleanCellValue()).trim(); break;
            case Cell.CELL_TYPE_FORMULA:
                o = String.valueOf(c.getCellFormula()).trim(); break;
            case Cell.CELL_TYPE_NUMERIC:
                o = String.valueOf(c.getNumericCellValue()).trim(); break; 
            case Cell.CELL_TYPE_STRING:
                o = c.getStringCellValue().trim(); break;
            default:
                o = null;
                break;
        }
        return o;
	}
	
	/**
	 * 根据标题获取对应的方法名称
	 * @param eh
	 * @return
	 */
	public String getMethodName(ExcelHeader eh) {
		String mn = eh.getMethodName().substring(3);
		mn = mn.substring(0, 1).toLowerCase()+mn.substring(1);
		return mn;
	}
	
	/**
	 * 获取clz类中使用注解ExcelResource属性集合
	 * @param clz
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	public List<ExcelHeader> getHeaderList(Class clz) {
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
		// 获取clz中的所有方法
		Method[] methods = clz.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			// 获取get方法上有注解ExcelResource
			if (methodName.startsWith("get") && method.isAnnotationPresent(ExcelResource.class)) {
				ExcelResource resource = method.getAnnotation(ExcelResource.class);
				headers.add(new ExcelHeader(resource.title(), resource.order(), methodName));
			}
				
		}
		return headers;
	}
	
}
