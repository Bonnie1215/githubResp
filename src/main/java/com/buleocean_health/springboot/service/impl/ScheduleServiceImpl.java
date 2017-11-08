package com.buleocean_health.springboot.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.buleocean_health.springboot.service.ScheduleService;

/**
 * 定时任务
 * @author huyanqiu
 *
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{

	// 使用cron表达式
	@Scheduled(cron="0/30 * * * * *")//每隔30秒钟执行一次
	@Override
	public void schedule() {
		//TODO 注意, 如果方法中的程序在周期内(30S)内无法执行完成, 会开启两次, 自行控制
		System.err.println("当前日期：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
	}

}
