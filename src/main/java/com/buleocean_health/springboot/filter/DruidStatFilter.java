package com.buleocean_health.springboot.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Druid拦截器
 */
@WebFilter(
		filterName="druidWebStatFilter", urlPatterns="/*",
		initParams={
				/** 忽略资源URL */
				@WebInitParam(name="exclusions", value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/static/*"),
				/** 缺省sessionStatMaxCount是1000个。你可以按需要进行配置 */
				@WebInitParam(name="sessionStatMaxCount", value="1000"),
				/** 关闭session统计功能 */
				@WebInitParam(name="sessionStatEnable", value="false"),
				/** 能够监控单个url调用的sql列表 */
				@WebInitParam(name="profileEnable", value="true"),
				/** 配置principalCookieName，使得druid知道当前的user是谁  ===xxx.user修改为你user信息保存在cookie中的cookieName*/
				@WebInitParam(name="principalCookieName", value="USER_COOKIE"),
				/** 配置principalSessionName，使得druid能够知道当前的session的用户是谁 ===xxx.user修改为你user信息保存在session中的sessionName*/
				@WebInitParam(name="principalSessionName", value="USER_SESSION"),
		}
)
public class DruidStatFilter extends WebStatFilter{

}
