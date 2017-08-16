package win.sunshine.base.service.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import win.sunshine.base.service.api.domain.SysResult;
import win.sunshine.base.service.api.service.FileUploadService;

/**
 * 上传附件一系列操作
 * @author huyanqiu
 *
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	/**
	 * 上传附件
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/upload", produces={"application/json;charset=UTF-8"})
	public SysResult upload(@RequestParam(value="file") MultipartFile file) {
		System.out.println("upload===============");
		SysResult sysResult = fileUploadService.upload(file);
		return sysResult;
	}
	
	/**
	 * 下载附件--ResponseEntity方式
	 * @throws IOException 
	 */
	@RequestMapping(value="/download", produces={"application/vnd.ms-excel;charset=UTF-8"})
	public ResponseEntity<InputStreamResource> download() throws IOException {
		return fileUploadService.download();
	}
	
	@RequestMapping(value="/downloadByJava", produces={"application/vnd.ms-excel;charset=UTF-8"})
	public SysResult downloadByJava(HttpServletResponse response) throws Exception {
		return fileUploadService.downloadByJava(response);
	}
	
}
