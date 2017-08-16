package win.sunshine.base.service.api.web;

import win.sunshine.base.service.api.AppStart;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * War包的形式启动会用到
 */
public class InitWebServlet extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//TODO 读取服务器的配置文件
		AppStart.init(true);
		return builder.sources(AppStart.class);
	}
	
}