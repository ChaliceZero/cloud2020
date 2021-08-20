package com.zq.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
// 表示本模块就是eureka的服务注册中心，管理注册的节点
@EnableEurekaServer
public class EurekaMain7001
{
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
