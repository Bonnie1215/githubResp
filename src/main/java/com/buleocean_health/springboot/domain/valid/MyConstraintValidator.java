package com.buleocean_health.springboot.domain.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验注解@MyConstraint的逻辑执行类 
 * @author huyanqiu
 * @date 2018年3月1日下午2:44:15
 * @version 版本号：1.0
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object>{

	/**
	 * 初始化
	 * @param constraintAnnotation
	 */
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		//初始化的时候
        System.out.println("my validator init");
	}

	/**
	 * 校验逻辑
	 * @param o  //校验的参数
     * @param constraintValidatorContext
     * @return
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//这里写判断逻辑
        System.out.println(value);
        //我这里直接方法false，就是要提示错误，如果返回true就表示验证通过
        return true;
	}

}
