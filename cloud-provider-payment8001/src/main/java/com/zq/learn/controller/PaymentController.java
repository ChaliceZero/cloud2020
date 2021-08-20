package com.zq.learn.controller;

import com.zq.learn.entities.CommonResult;
import com.zq.learn.entities.Payment;
import com.zq.learn.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务发现配置，提供外部环境本系统的服务信息
     */
    @Resource
    private DiscoveryClient discoveryClient;

    /********************************* RPC接口提供 *****************************************/
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:  " + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID: " + id, null);
        }
    }

    /********************************* 服务发现 *****************************************/
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices(); // 获取微服务名称列表
        for (String element : services) {
            /**
             * Eureka
             * *****element: cloud-payment-service
             * *****element: cloud-order-service
             */
            log.info("*****element: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            /**
             * Eureka
             * CLOUD-PAYMENT-SERVICE	192.168.234.1	8001	http://192.168.234.1:8001
             * CLOUD-PAYMENT-SERVICE	192.168.234.1	8002	http://192.168.234.1:8002
             */
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        /**
         * Eureka
         * {"services":["cloud-payment-service","cloud-order-service"],"order":0}
         */
        return this.discoveryClient; // 返回eureka中注册的微服务名称列表
    }

    /**
     * 自定义负载均衡【Eureka-Ribbon】调用方法
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    /**
     * OpenFeign超时控制测试方法
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    /**
     *
     * @return
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
