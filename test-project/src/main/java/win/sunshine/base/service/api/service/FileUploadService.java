package win.sunshine.base.service.api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import win.sunshine.base.service.api.domain.SysResult;

public interface FileUploadService {

	SysResult upload(MultipartFile file);

	ResponseEntity<InputStreamResource> download() throws IOException;

	SysResult downloadByJava(HttpServletResponse response) throws Exception;

}
