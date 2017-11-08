package com.buleocean_health.springboot.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.buleocean_health.springboot.AppStart;

/**
 * war形式启动需要用到
 * @author huyanqiu
 *
 */
public class InitWebServlet extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO 读取服务配置文件
		AppStart.init(true);
		return super.configure(builder);
	}

}
