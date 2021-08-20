package com.zq.learn.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡接口
 */
public interface LoadBalancer
{
    /**
     * 获取具体的微服务实例
     * @param serviceInstances 某个微服务名称下的所有实例列表
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
