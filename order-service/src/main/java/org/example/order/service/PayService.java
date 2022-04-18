package org.example.order.service;

import com.alipay.api.AlipayApiException;
import org.example.order.pojo.pay.AlipayBean;

public interface PayService {
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}
