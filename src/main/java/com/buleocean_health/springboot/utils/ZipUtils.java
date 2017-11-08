package com.buleocean_health.springboot.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 使用java对压缩包处理(不建议使用，建议使用zip4j工具)
 * @author huyanqiu
 *
 */
public class ZipUtils {
	
	/**
	 * 解压zip
	 * @param fis  文件流
	 * @param path 目标路径
	 */
	public static void decompressionZip(FileInputStream fis, String parent) {
		long startTime=System.currentTimeMillis();  
		//输入源zip路径  
        ZipInputStream zin = new ZipInputStream(fis, Charset.forName("GBK")); 
        BufferedInputStream bin = new BufferedInputStream(zin);  
        File fout = null;  
        try {  
        	// 实例化对象，指明要进行解压的文件
        	ZipEntry entry = zin.getNextEntry();
        	// 获取下一个ZipEntry 为什么写两个一样的判断？ 使用windows自带压缩文件第一次zin.getNextEntry()取不到所以增加一个
            while(((entry = zin.getNextEntry())!=null && !entry.isDirectory()) || (entry = zin.getNextEntry())!=null && !entry.isDirectory()){  
            	fout = new File(parent,entry.getName());  
                if(!fout.exists()){  
                    (new File(fout.getParent())).mkdirs();  
                }  
                FileOutputStream out=new FileOutputStream(fout);  
                BufferedOutputStream Bout=new BufferedOutputStream(out);  
                int b;  
                while((b=bin.read())!=-1){  
                    Bout.write(b);  
                }  
                Bout.close();  
                out.close();  
            }  
            bin.close();  
            zin.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        long endTime=System.currentTimeMillis();  
        System.out.println("耗费时间： "+(endTime-startTime)+" ms"); 
	}
	
	/** 
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
     * @param sourceFilePath :待压缩的文件路径(D:\\test:注意该路径下不能有文件夹)
     * @param zipFilePath :压缩后存放路径  (D:\\test)
     * @param fileName :压缩后文件的名称 (fileName)
     * @return 
     */  
	public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("isSourceEqualsTarget", sourceFilePath.equals(zipFilePath));
		parameterMap.put("fileName", fileName+".zip");
        boolean flag = false;  
        File sourceFile = new File(sourceFilePath);  
        ZipOutputStream zos = null;  
        if(sourceFile.exists() == false) {  
        	throw new RuntimeException("待压缩的文件目录："+sourceFilePath+"不存在.");
        } else {  
            try {  
                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
                if(zipFile.exists()){  
                	throw new RuntimeException(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");
                } else {  
                	// zip输出流
                	zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));  
                	dirToZipFile(zos, sourceFile, parameterMap, true);
                	flag = true;
                }  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } catch (IOException e) {
            	 e.printStackTrace();  
                 throw new RuntimeException(e); 
			} finally{  
                //关闭流  
                try {  
                    if(null != zos) zos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return flag; 
	}
	
	/**
	 * 将文件夹中的文件保存到zip压缩包中
	 * @param zos zip流
	 * @param sourceFile 源文件
	 * @param isFirstLevel 是否是源路径
	 * @throws IOException 
	 */
	private static void dirToZipFile(ZipOutputStream zos, File sourceFile, Map<String, Object> parameterMap, boolean isFirstLevel) throws IOException {
		// 获得目标路径下的所有文件
		File[] sourceFiles = sourceFile.listFiles();
		if (null==sourceFiles || sourceFiles.length<1) {
			throw new RuntimeException("目标路径下存在名字为："+sourceFile.getName()+".zip打包文件。");
		} else {
			for (int i=0; i<sourceFiles.length; i++) {
				if (sourceFiles[i].isFile()) {
					ZipEntry zipEntry = null;
					// 待压缩的文件路径和压缩后存放路径相同时，排除压缩zip的文件
					if ((boolean)parameterMap.get("isSourceEqualsTarget") && 
							sourceFiles[i].getName().equals(parameterMap.get("fileName"))) continue;
					// zip指定文件夹中文件  isFirstLevel=false
					if (isFirstLevel) {
						// 将文件保存到zip包中
						zipEntry = new ZipEntry(sourceFiles[i].getName());
					} else {
						// 将文件保存到zip包中
						zipEntry = new ZipEntry(sourceFile.getName() + "/" + sourceFiles[i].getName());
					}
					zos.putNextEntry(zipEntry); 
				} else {
					String path = sourceFiles[i].getName() + "/";
					// 创建zip压缩文件的内部文件夹
					zos.putNextEntry(new ZipEntry(path));
					// 空文件夹不需要遍历
					if (new File(sourceFile.getAbsolutePath()+"/"+path).listFiles().length!=0) {
						// 递归
						dirToZipFile(zos, new File(sourceFile.getAbsolutePath() + "/" +path), parameterMap, false);
					}
				}
			}
		}
	}
    
//    public static void main(String[] args){  
//        String sourceFilePath = "D:\\test";  
//        String zipFilePath = "D:\\test";  
//        String fileName = "压缩包";  
//        boolean flag = fileToZip(sourceFilePath, zipFilePath, fileName);  
//        if(flag){  
//            System.out.println("文件打包成功!");  
//        }else{  
//            System.out.println("文件打包失败!");  
//        }  
//    }  
	
}
