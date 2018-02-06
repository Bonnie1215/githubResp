package com.buleocean_health.springboot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;


public class JsoupUtil {
	
	public static String a() {
		return "1111";
	}
	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		StringBuffer sb = new StringBuffer();
//		List<String> strList = IOUtils.readLines(new FileInputStream(new File("./src/main/resources/pdf.txt")));
//		strList.forEach(str -> {
//			sb.append(str);
//		});
//		// 配置文件对象
//		Object  configurationObj  = JSON.toJSON(sb);
		Method method = JsoupUtil.class.getMethod("a");
		JsoupUtil newInstance = JsoupUtil.class.newInstance();
		Object object = method.invoke(newInstance);
		System.out.println(object);
	}
}
