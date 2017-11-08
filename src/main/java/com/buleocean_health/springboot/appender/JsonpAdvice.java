package com.buleocean_health.springboot.appender;

import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * jsonp跨域
 * @author huyanqiu
 *
 */
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice{

	public JsonpAdvice() {
		super("callback", "jsonp");
	}
	
}
