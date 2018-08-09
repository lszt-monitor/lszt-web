package com.lm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//Filter拦截器
@ServletComponentScan
@MapperScan("web.info.mapper")//表示此服务注册到注册中心
@EnableEurekaClient
public class PcServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(PcServiceApp.class, args);		
	}
}
	