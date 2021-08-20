package com.zq.learn.service;

import com.zq.learn.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @auther zzyy
 * @create 2020-02-18 10:40
 */
public interface PaymentService
{
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
