package com.buleocean_health.springboot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buleocean_health.springboot.common.em.HttpRequestMethodType;

/**
 * 只允许HttpRequestMethodType中的类型
 * @author huyanqiu
 *
 */
@WebFilter(filterName="httpRequestMethodFilter", urlPatterns="/*")
public class Filter02_HttpRequestMethodFilter extends BaseFilter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter02_HttpRequestMethodFilter=========");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;
		try {
			HttpRequestMethodType type = HttpRequestMethodType.valueOf(req.getMethod());
			if(type != null){
				chain.doFilter(req, resp);
			}
		} catch (Exception e) {}
		super.doFilter(request, response, chain);
	}
	
}
