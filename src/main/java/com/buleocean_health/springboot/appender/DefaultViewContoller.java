package com.buleocean_health.springboot.appender;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统一处理http://lcoalhost:8888/springboot返回的页面
 * @author huyanqiu
 *
 */
@Controller
public class DefaultViewContoller {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
}
