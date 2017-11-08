package com.buleocean_health.springboot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 对于文件的相关操作
 * @author huyanqiu
 *
 */
public class FilesUtils {
	
	private final static String CLASSPARH = "classpath:";
	
	/**
	 * 验证一个文件或文件夹是否存在
	 * @param filePath 绝对路径
	 * @return true:存在  false:不存在
	 */
	public static boolean checkFileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
	
	/**
	 * 获取输入流
	 * @throws Exception 
	 */
	public static InputStream readFileInput(String path) throws Exception {
		InputStream in = null;
		try {
			if (path.startsWith(CLASSPARH)) {
				// 相对路劲
				in = FilesUtils.class.getClassLoader().getResourceAsStream(path);
			} else {
				// 绝对路径
				in = new FileInputStream(new File(path));
			}
		} catch (FileNotFoundException e) {
			throw new Exception("未找到该文件");
		}
		return in;
	}
	
	/**
	 * 加载配置文件
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public static Properties loadProperty(String path) throws Exception {
		InputStream in = null;
		Properties prop = new Properties();
		try {
			in = readFileInput(path);
			prop.load(in);
		} catch (Exception e) {
			throw new Exception("未找到该文件");
		} finally {
			if (in!=null) {
				in.close();
			}
		}
		return prop;
	}
	
}
