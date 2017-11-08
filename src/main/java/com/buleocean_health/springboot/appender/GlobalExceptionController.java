package com.buleocean_health.springboot.appender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buleocean_health.springboot.domain.base.SysResult;

/**
 * 全局异常处理
 * @author huyanqiu
 *
 */
@ControllerAdvice
public class GlobalExceptionController {

	@Value("${sys.debug}")
	private boolean debug;
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public SysResult handleException(Exception e) {
		if (debug) {
			e.printStackTrace();
			return SysResult.error(e.getMessage());
		}
		return SysResult.error();
	}
	
}
