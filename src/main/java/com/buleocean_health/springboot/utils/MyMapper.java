package com.buleocean_health.springboot.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 * 注意： 不要把MyMapper放到 basePackage中，也就是不能同其他Mapper一样被扫描到
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}