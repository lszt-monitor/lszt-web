package com.lm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//自动配置注解
@SpringBootApplication
// 启动定时注解
@EnableScheduling
// 表示此服务注册到注册中心
@EnableEurekaClient
// 开启异步调用功能 线程池
@EnableAsync
@MapperScan("com.lm.mapper")
public class SwitchConsumerApp {
	public static void main(String[] args){
		/**
		 *
		 * main 方法进行启动我们的应用程序
		 *
		 */
		SpringApplication.run(SwitchConsumerApp.class, args);
	}
}
