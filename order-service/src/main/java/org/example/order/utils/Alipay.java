package org.example.order.utils;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Component;

import org.example.order.pojo.pay.AlipayBean;
import org.example.order.config.AlipayConfig;

@Component
public class Alipay {

    public String pay(AlipayBean alipayBean) throws AlipayApiException {
        /* 获得初始化的AlipayClient */
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,AlipayConfig.FORMAT,AlipayConfig.CHARSET,AlipayConfig.ALIPAY_PUBLIC_KEY
                ,AlipayConfig.SIGN_TYPE);
        /* 设置请求参数 */
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        /* 设置页面同步通知路径和异步通知页面路径 */
        request.setReturnUrl(AlipayConfig.RESULT_URL);

        request.setBizContent(JSON.toJSONString(alipayBean));

        return alipayClient.pageExecute(request).getBody();
    }
}

