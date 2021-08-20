package com.zq.learn.service.impl;

import com.zq.learn.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * 针对fallback与业务代码耦合问题，统一定义fallback调度处理
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
