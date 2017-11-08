package com.buleocean_health.springboot.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * 对zip压缩文件处理(建议使用)
 * @author huyanqiu
 *
 */
public class Zip4jUtils {
	
	/**
	 * 将指定目录下的所有文件压缩到当前目录并以日期作为文件名
	 * @param srcPath 指定路径   例如：D:/test/a.zip
	 * @throws IOException 
	 */
	public static void zipFiles(File srcPath) throws IOException {
		zipFiles(srcPath, null);
	}

	/**
	 * 将指定目录下的所有文件压缩到指定目录并以日期作为文件名
	 * @param srcPath  指定路径   D:/test/a.zip
	 * @param object 目标路径 D:/test
	 * @throws IOException 
	 */
	public static void zipFiles(File srcPath, String dest) throws IOException {
		// 创建目标文件
		dest = buildDestFileName(srcPath, dest);
		System.out.println("dest:"+dest);
		File[] files = null;
		if (srcPath.isDirectory()) {
			// 只取文件夹下的所有文件，排除目录
			files = srcPath.listFiles(File::isFile);
		} else {
			files = new File[]{srcPath};
		}
		System.out.println("files:"+files.length);
		zipFiles(files, dest, false);
	}
	
	/**
	 * 将指定目录下的所有文件压缩到指定目录并以日期作为文件名
	 * @param files
	 * @param dest
	 * @param buildDest
	 * @throws IOException 
	 */
	public static void zipFiles(File[] files, String dest, boolean buildDest) throws IOException {
		// 创建目标文件
		if(buildDest) {
			dest = buildDestFileName(null, dest);
		}
		try {
			ZipParameters zipParam = new ZipParameters();
			zipParam.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			zipParam.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			ZipFile zipfile = new ZipFile(dest);
			zipfile.addFiles(new ArrayList<>(Arrays.asList(files)), zipParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将指定目录下的zip文件解压
	 * @param srcPath 例如：D:/test/a.zip
	 * @throws ZipException 
	 */
	public static void zipToDir(String srcPath) throws ZipException {
		zipToDir(srcPath, null);
	}

	/**
	 * 将指定目录下的zip文件解压
	 * @param srcPath 压缩文件全路径  例如：D:/test/a.zip
	 * @param dest 解压后存放地址 例如：D:/test/
	 * @throws ZipException 
	 */
	public static void zipToDir(String srcPath, String dest) throws ZipException {
		ZipFile zipFile = new ZipFile(srcPath);
		dest = buildDecompressionPath(srcPath, dest);
		System.out.println("dest:"+dest);
		// 设置编码，否则中文乱码
		zipFile.setFileNameCharset("GBK");
		zipFile.extractAll(dest);
	}

	/**
	 * 创建zip解压后存放路径
	 * @param srcPath 压缩文件全路径  例如：D:/test/a.zip
	 * @param dest  解压后存放地址 例如：D:/test/a or null
	 * @return
	 */
	private static String buildDecompressionPath(String srcPath, String dest) {
		if (srcPath==null && dest==null) {
			throw new RuntimeException("请指定解压路径");
		}
		File file = new File(srcPath);
		// 以毫微秒做基础计数, 返回唯一有序增长ID
		String day = IdUtils.getRandomIdByDateTime();
		// 没有指定解压后路径，使用zip文件路径+当前时间
		if (dest==null) {
			dest = file.getPath();
			if(file.isFile()) {
				dest = file.getParent()+File.separator+day;
			}
		} else {
			dest += (File.separator+day);
		}
		return dest;
	}
	
	/**
	 * 创建目标文件
	 * @param srcPath
	 * @param dest
	 * @throws IOException 
	 */
	private static String buildDestFileName(File srcPath, String dest) throws IOException {
		if (srcPath==null && dest==null) {
			throw new RuntimeException("请指定压缩路径");
		}
		// 当前时间 yyyy-MM-dd
		String day = TimeUtils.getTimeToDay();
		// 如果没有指定目标路径，以当前路径作为目标路劲
		if (dest==null) {
			dest = srcPath.getPath();
			if (!srcPath.isDirectory()) {
				dest = srcPath.getParent();
			}
			dest = dest + File.separator+day+File.separator+day+".zip";
		} else {
			dest = dest+File.separator+day+".zip";
		}
		Path path = Paths.get(dest);
		// 检查源文件夹是否存在
		if (Files.notExists(path)) {
			// 创建文件夹
			Files.createDirectories(path);
		}
		// 安静模式删除目录，操作过程中会抛出异常
		FileUtils.deleteQuietly(new File(dest));
		return dest;
	}
	
	public static void main(String[] args) throws IOException, ZipException {
//		File srcPath = new File("D:/logs/drugPicture/sogou/国药准字H11021771");
//		zipFiles(srcPath);
		String path = "D:/logs/drugPicture/sogou/国药准字H11021771/2017-11-06/2017-11-06.zip";
		zipToDir(path);
	}
	
}
