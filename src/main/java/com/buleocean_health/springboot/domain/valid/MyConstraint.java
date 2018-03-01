package com.buleocean_health.springboot.domain.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义注解用于Controller参数@Valid校验
 * @author huyanqiu
 * @date 2018年3月1日下午2:41:46
 * @version 版本号：1.0
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD}) //注解作用域
@Retention(RetentionPolicy.RUNTIME) //注解作用时间
@Constraint(validatedBy =MyConstraintValidator.class ) //执行校验逻辑的类
public @interface MyConstraint {
	
	String message() default "自定义字段注解"; //校验不过时显示的信息
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
