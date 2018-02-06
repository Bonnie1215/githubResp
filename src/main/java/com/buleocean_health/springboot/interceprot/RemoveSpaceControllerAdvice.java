package com.buleocean_health.springboot.interceprot;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.buleocean_health.springboot.domain.annotation.RemoveSpace;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 去除字符串前后空格
 * @author huyanqiu
 * @date 2018年1月31日下午2:57:42
 * @version 版本号：1.0
 */
@ControllerAdvice
public class RemoveSpaceControllerAdvice extends RequestBodyAdviceAdapter{
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Class<? extends Object> clazz = body.getClass();
        List<Field> fields = new ArrayList<>();
        getAllFields(clazz,fields);
        for (Field field : fields) {
        	System.out.println("000000000000000000");
        	// 获取字段中包含RemoveSpace注解的字段 (属性上的注解)
        	RemoveSpace annotation = field.getAnnotation(RemoveSpace.class);
        	// 如果有这个注解则需要去掉前后空格
        	if (annotation!=null) {
        		System.out.println("1111111111111111111");
    		  try {
                  field.setAccessible(true);
                  Object object = field.get(body);
                  if (object instanceof String){
                	  // 去除前后空格
                      String value = object.toString().trim();
                      field.set(body, value);
                  }
              } catch (Exception e) {
              }
        	}
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    /**
     * 获取类中的所有属性(包括继承类中的)
     * @param clazz
     * @param fields
     */
    private void getAllFields(Class<?> clazz, List<Field> fields) {
        if (clazz != Object.class){
            Field[] fs = clazz.getDeclaredFields();
            fields.addAll(Arrays.asList(fs));
            getAllFields(clazz.getSuperclass(),fields);
        }
    }
}
