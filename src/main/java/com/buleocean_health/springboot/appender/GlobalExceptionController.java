package com.buleocean_health.springboot.appender;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public SysResult methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		System.out.println(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
		// 获取自定义注解中messae字段          ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		Set<String> errorSet = new HashSet<String>();
		for (FieldError error : fieldErrors) {
			errorSet.add(error.getDefaultMessage());
		}
		return SysResult.error("请填写字段："+errorSet);
	}
	
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
