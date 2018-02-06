package com.buleocean_health.springboot.interceprot;

import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.buleocean_health.springboot.domain.annotation.Auth;

/**
* 拦截器用于处理在指定方法前使用Auth注解特殊处理，或者在指定方法参数前面使用Valid注解做特殊处理。
* @author huyanqiu
* @date 2018年2月1日 上午10:35:17
* @version 版本号：1.0
*/
@Component
public class AuthHandleInterface implements HandlerInterceptor{
	
	/**
	 * 在请求之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求方法
		HandlerMethod method = (HandlerMethod)handler;
		// 查看方法前面是否有Auth注解
		Auth auth = method.getMethod().getAnnotation(Auth.class);
		Parameter[] parameters = method.getMethod().getParameters();
		if (!StringUtils.isEmpty(parameters)) {
			for (int i=0; i<parameters.length; i++) {
				Parameter param = parameters[i];
				// 查看参数前面时候有Valid注解 
				Valid valid = param.getAnnotation(Valid.class);
				if (valid!=null) {
					// TODO 执行内容 比如去除对象中字符串的前后空格
				}
			}
		}
		
		if (auth!=null) {
			System.out.println("需要鉴别权限,有authz注解");
		} else {
			System.out.println("不需要鉴别权限,没有authz注解");
		}
		return true;
	}

	/**
	 * 在preHandle方法返回true的时候执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在preHandle方法返回true的时候执行，在整个请求之后（在请求渲染对应的视图之后执行）。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
