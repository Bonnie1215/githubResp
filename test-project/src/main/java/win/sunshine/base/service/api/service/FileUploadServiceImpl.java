package win.sunshine.base.service.api.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import win.sunshine.base.service.api.domain.FileUpload;
import win.sunshine.base.service.api.domain.SysResult;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	private static final String XLS = ".xls";
	private static final String XLSX = ".xlsx";
	
	@Override
	public SysResult upload(MultipartFile file) {
		if (file.isEmpty()) {
			return SysResult.error("参数有误，请重新上传");
		}
		// 获得文件名称
		String fileName = file.getOriginalFilename();
		if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
			return SysResult.error(fileName+" 不是excel文件");
		}
		
		// 表格中数据错误的信息记录
		StringBuffer error = new StringBuffer();
		
		// 检查excel表中的数据格式是否正确
		Workbook workbook = null;
		try {
			InputStream inputStream = file.getInputStream();
			if (fileName.endsWith(XLS)) {
				// 2003
				workbook = new HSSFWorkbook(inputStream);
			} else if (fileName.endsWith(XLSX)){
				// 2007
				workbook = new XSSFWorkbook(inputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List fileUploadList = new ArrayList<>();
		// 遍历表
		if (workbook != null) {
			for (int sheetNum=0; sheetNum<workbook.getNumberOfSheets(); sheetNum++) {
				// 获得当前的sheet页
				Sheet sheet = workbook.getSheetAt(sheetNum);
				if (sheet==null) continue;
				// 获得sheet的第一行
				int firstRowNum = sheet.getFirstRowNum();
				// 获得sheet的最后一行
				int lastRowNum = sheet.getLastRowNum();
				// 除第一行以外的所有数据
				for (int rowNum=firstRowNum+1; rowNum<=lastRowNum; rowNum++) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 错误行
					int errorNum = rowNum+1;
					// 获得当前行
					Row row = sheet.getRow(rowNum);
//					row.getLastCellNum(); // 获得最后一列
					if (row==null) {
						continue;
					}
					if (getCellValue(row.getCell(0))==null) {
						error.append("第"+errorNum+"行第1列'编号'数据有误");
					}
					if (getCellValue(row.getCell(1)).isEmpty()) {
						error.append("第"+errorNum+"行第2列'姓名'数据有误");
					}
					if (getCellValue(row.getCell(2)).isEmpty()) {
						error.append("第"+errorNum+"行第3列'性别'数据有误");
					}
					if (getCellValue(row.getCell(3)).isEmpty()) {
						error.append("第"+errorNum+"行第4列'年龄'数据有误");
					}
					if (getCellValue(row.getCell(4)).isEmpty()) {
						error.append("第"+errorNum+"行第5列'工作状态'数据有误");
					}
					if (getCellValue(row.getCell(5)).isEmpty()) {
						error.append("第"+errorNum+"行第3列'文化程度'数据有误");
					}
					if (getCellValue(row.getCell(6)).isEmpty()) {
						error.append("第"+errorNum+"行第4列'籍贯'数据有误");
					}
					if (getCellValue(row.getCell(7)).isEmpty()) {
						error.append("第"+errorNum+"行第5列'手机号码'数据有误");
					}
					FileUpload fileUpload = new FileUpload(getCellValue(row.getCell(1)), getCellValue(row.getCell(0)), 
							getCellValue(row.getCell(2)), getCellValue(row.getCell(3)), getCellValue(row.getCell(4)), 
							getCellValue(row.getCell(5)), getCellValue(row.getCell(6)));
					fileUploadList.add(fileUpload);
				}
			}
		}
		if (!error.toString().equals("")) {
			// 规则校验失败
			return SysResult.error(error);
		} else {
			// 规则校验成功
			String path = "D:/test"+"/"+UUID.randomUUID().toString().replaceAll("-", "")+XLSX;
			System.out.println("path:"+path);
			File newFile = new File(path);
			try {
				file.transferTo(newFile); // 将文件保存到服务器中
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SysResult.ok();
	}
	
	@SuppressWarnings("deprecation")
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}

	/**
	 * 下載附件
	 * @throws IOException 
	 * 解决附件名称中文乱码:
	 * 1、URLEncoder.encode(file.getFilename(), "UTF-8")
	 * 2、new String(file.getFilename().getBytes("gbk"), "iso-8859-1")
	 */
	@Override
	public ResponseEntity<InputStreamResource> download() throws IOException {
		String path = "C:\\Users\\12240\\Desktop\\规则上传.xls";
		FileSystemResource file = new FileSystemResource(path);  
        HttpHeaders headers = new HttpHeaders();  
        System.out.println("fileName: "+file.getFilename());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
        String fileName = URLEncoder.encode(file.getFilename(), "UTF-8");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));  
        headers.add("Pragma", "no-cache");  
        headers.add("Expires", "0");  
        return ResponseEntity
        		.ok()
        		.headers(headers)
        		.contentLength(file.contentLength())
        		.contentType(MediaType.parseMediaType("application/octet-stream"))
        		.body(new InputStreamResource(file.getInputStream()));  
	}

	@Override
	public SysResult downloadByJava(HttpServletResponse response) throws Exception {
		String filePath = "C:\\Users\\12240\\Desktop\\规则上传.xls";
        File file = new File(filePath);  
        // 清空response   
       response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(file.getName().getBytes("gbk"),"iso-8859-1") + "\"");    
       response.addHeader("Content-Length", "" + file.length());    
       response.setContentType("application/octet-stream;charset=UTF-8");    
       InputStream in = null;  
       OutputStream toClient = null;  
       try{   
            //以流的形式下载文件   
           in = new BufferedInputStream(new FileInputStream(filePath));   
            byte[] buffer = new byte[in.available()];   
            in.read(buffer);   
            in.close();  
              
            toClient = new BufferedOutputStream(response.getOutputStream());   
            toClient.write(buffer);   
            toClient.flush();   
              
        }catch(Exception e){   
            e.printStackTrace();   
        }finally{  
            toClient.close();   
        }  
        return SysResult.ok();  
	}

}
