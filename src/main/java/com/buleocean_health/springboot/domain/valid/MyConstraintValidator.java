package com.buleocean_health.springboot.domain.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object>{

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		//初始化的时候
        System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//这里写判断逻辑
        System.out.println(value);
        //我这里直接方法false，就是要提示错误，如果返回true就表示验证通过
        return true;
	}

}
