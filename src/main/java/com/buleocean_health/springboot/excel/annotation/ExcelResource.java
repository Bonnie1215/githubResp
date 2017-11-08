package com.buleocean_health.springboot.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用在对象的get方法上加入的annotation，通过该annotation说明某个属性所对应的标题
 * @author huyanqiu
 *
 */
@Target(ElementType.METHOD) // 作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时发挥作用
public @interface ExcelResource {
	
	// 在excel的顺序
	public int order() default 99999;
	// 属性的标题名称
	public String title() default "";
	
}
