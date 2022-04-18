package org.example.order.service.impl;

import com.alipay.api.AlipayApiException;
import org.example.order.pojo.pay.AlipayBean;
import org.example.order.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.example.order.utils.Alipay;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }
}
