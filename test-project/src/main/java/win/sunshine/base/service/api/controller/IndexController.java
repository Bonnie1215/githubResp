package win.sunshine.base.service.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/upload")
	public String index(){
		return "upload"; // 查找upload.html页面
	}
	
}
