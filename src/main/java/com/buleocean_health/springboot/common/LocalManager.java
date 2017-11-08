package com.buleocean_health.springboot.common;

import java.util.HashMap;
import java.util.Map;

import com.buleocean_health.springboot.common.em.LocalManagerType;

/**
 * 本地变量：存放当前线程IP，登录用户信息等
 * @author huyanqiu
 *
 */
public class LocalManager {
	
	private static ThreadLocal<Map<LocalManagerType, Object>> tl = null;
	
	/**
	 * 初始化变量tl
	 */
	static {
		tl = new ThreadLocal<Map<LocalManagerType, Object>>(){
			@Override
			protected Map<LocalManagerType, Object> initialValue() {
				return new HashMap<LocalManagerType, Object>();
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public static <T>T getVal(LocalManagerType key) {
		return (T) getVal(key, key.get().getClass());
	}

	@SuppressWarnings("unchecked")
	private static <T>T getVal(LocalManagerType key, Class<T> t) {
		return (T) tl.get().get(key);
	}
	
	public static void setVal(LocalManagerType key, Object val) {
		tl.get().put(key, val);
	}
	
	public static Object removeVal(String key) {
		return tl.get().remove(key);
	}
	
	public static void remove() {
		tl.remove();
	}
	
	public static Map<LocalManagerType, Object> getData() {
		return tl.get();
	}
}
