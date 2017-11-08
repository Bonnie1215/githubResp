package com.buleocean_health.springboot.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.alibaba.druid.util.DruidWebUtils;
import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;

/**
 * 基础的Filter，所有的自定义Filter继承此类
 * 默认忽略过滤： *.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*
 * @author huyanqiu
 *
 */
public abstract class BaseFilter implements Filter{

	protected PatternMatcher pathMatcher = new ServletPathMatcher();
	protected String contextPath;
	protected Set<String> excludesPattern = new HashSet<String>(
			Arrays.asList("*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/static/*,/druid/*,/test/*".split("\\s*,\\s*")));
	
	/**
	 * 判断URI是否是需要过滤的请求
	 * @param excludesPattern 需要过滤URI
	 * @param requestURI 当前请求URI
	 * @return boolean  是：true 否：false
	 */
	public boolean isExclusion(Set<String> excludesPattern, String requestURI) {
		if (excludesPattern == null) {
			return false;
		}
		if (contextPath != null && requestURI.startsWith(contextPath)) {
			requestURI = requestURI.substring(contextPath.length());
			if (!requestURI.startsWith("/")) {
				requestURI = "/" + requestURI;
			}
		}
		for (String pattern : excludesPattern) {
			if (pathMatcher.matches(pattern, requestURI)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.contextPath = DruidWebUtils.getContextPath(filterConfig.getServletContext());
		String exclusions = filterConfig.getInitParameter("exclusions");
		if (exclusions != null && exclusions.trim().length() != 0) {
            excludesPattern.addAll(Arrays.asList(exclusions.split("\\s*,\\s*")));
        }
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	}

	@Override
	public void destroy() {
	}

}
