package com.zq.learn.service.impl;

import com.zq.learn.dao.PaymentDao;
import com.zq.learn.entities.Payment;
import com.zq.learn.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2020-02-18 10:40
 */
@Service
public class PaymentServiceImpl implements PaymentService
{
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment)
    {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id)
    {
        return paymentDao.getPaymentById(id);
    }
}
