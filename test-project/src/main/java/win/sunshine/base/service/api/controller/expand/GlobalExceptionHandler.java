package win.sunshine.base.service.api.controller.expand;

import win.sunshine.base.service.api.domain.SysResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ControllerAdvice：将对于控制器的全局配置放置在同一位置
 * @ExceptionHandler：用于全局处理控制器里面的异常
 * @InitBinder：用来设置webDataBinder(用来自动绑定前台请求参数到Model中)
 * @ModelAttribute：本来的作用是绑定值到Model里，此处是让全局的@RequestMapping都能获得此处设置的键值对
 */

/** 全局异常catch处理类--  处理500 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class) // 用于全局处理控制器里的异常
	@ResponseBody
	public SysResult handlerException(Exception ex){
		System.out.println("异常类: "+ex.getClass().getName()+", 异常原因:  "+ex.getMessage()+", 异常NO.1: "+(ex.getStackTrace().length>0?ex.getStackTrace()[0]:""));
		ex.printStackTrace();
		return SysResult.error();
	}
	
}