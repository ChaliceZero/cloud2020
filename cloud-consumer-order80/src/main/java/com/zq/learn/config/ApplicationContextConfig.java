package com.zq.learn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    // 配置默认的负载均衡机制，否则调用时使用的微服务名称不会生效，“使用@LoadBalanced注解赋予RestTemplate负载均衡的能力”
    // 默认交替调用多个微服务提供者
    //@LoadBalanced // 先注掉，使用自定义负载均衡策略测试使用
    public RestTemplate getRestTemplate() {
        return new RestTemplate(); // 容器注入
    }
}
//applicationContext.xml <bean id="" class="">