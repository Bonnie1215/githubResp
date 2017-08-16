package win.sunshine.base.service.api.controller.expand;

import win.sunshine.base.service.api.domain.SysResult;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 全局访问失败处理类 -- 处理404 */
@Controller
public class MainsiteErrorController implements ErrorController {
	
	//定义404的@RequestMapping
	public static final String ERROR_PATH = "/error"; 
	
	@RequestMapping(value = ERROR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SysResult handleError(){
		return SysResult.error();
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}