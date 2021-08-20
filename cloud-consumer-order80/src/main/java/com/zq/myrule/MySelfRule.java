package com.zq.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon配置，自定义规则类
 */
@Configuration
public class MySelfRule {

    /**
     * 定义为随机
     *
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
