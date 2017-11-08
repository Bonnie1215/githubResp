package com.buleocean_health.springboot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buleocean_health.springboot.common.LocalManager;
import com.buleocean_health.springboot.common.em.LocalManagerType;

/**
 * 获取IP地址的过滤器，继承自己的BasicFilter
 * 所有过滤器以此方式命名: 过滤顺序清晰可见
 * @author huyanqiu
 */
@WebFilter(filterName="recordIPFilter", urlPatterns="/*")
public class Filter01_RecordIPFilter extends BaseFilter{
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter01_RecordIPFilter==============");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		// 记录请求IP地址
		String ip = request.getHeader("x-forwarded-for");
		if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP"); // 1
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 2
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr(); // 3
		}
		// 放入线程变量中
		LocalManager.setVal(LocalManagerType.IP, ip);
		// 放行
		chain.doFilter(request, response);
		// 移除线程变量中的值
		LocalManager.remove();
	}
	
}
