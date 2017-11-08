package com.buleocean_health.springboot.appender;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buleocean_health.springboot.domain.base.SysResult;

/**
 * 全局404处理
 * @author huyanqiu
 *
 */
@Controller
public class GlobalErrorController implements ErrorController {

	// 定义404的@RequestMapping
	public static final String ERROR_PATH = "/error";
	
	@RequestMapping(value=ERROR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SysResult handleError() {
		return SysResult.error();
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
}
