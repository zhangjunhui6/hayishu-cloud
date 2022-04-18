package org.example.order.config;

public class AlipayConfig {
    /**
     * 支付宝网关（固定）
     */
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    /**
     * APP_ID:应用ID，收款账号是APP_ID对应的支付宝账号
     */
    public static String APP_ID = "2016102300742109";
    /**
     * APP_PRIVATE_KEY:开发者应用私钥，RSA2密钥
     */
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCY/pgomXALvdBtQYVDOPruStWEvF3osfw89gZF31Sw/49eYE7sYk+OdLrkkvkszm5B4p9stSK7SgG6O+8NqN/aofHb7+zCiLyXAJufJaouYe5m/9ToE6C7DPEcwv+0xz68zmU5M7Lc0s2qpVyE2p/2WUe2II6Vo0RgV+Wgkp5E1MqST3e/ZAYz50/JFxQg56F8I0kmWj0abbdKs52Jeg+f4IKrhnaH2a0WK1P72iBNuSdmq04VncQnPinmv57ObaJV8vgFdF6zdJm5E8aMo/b4z8pcY6H2RKmLmKInjLT6n18yjGUy/4hC575WNoPzkMTr2xBXYadUCGLqi5xVS+C9AgMBAAECggEAXgmOyNWW0wC2jZEKMglh4vnIR6/oINDVczrdHDFrQuex3ZZgrFa5A5yoCzpzQaEYkBKVXyHu2SNh8HBKPSEhhqannEHaSq/1CvNqs+VYtu2mtVRmLsdxsccApGcO+LTG0RIjkYc/Vw+TLmI5fcdLLwYtV7qnKT6TFoywLYQxF6imKFhW2GjZ6KgWIKI4uFUXrtBAUg9ejy4zlO6B3tgmukcc+qCH0o46sMv5umazh3yjMOR7IiaUBfC88MQTLKv2cksQtLWEPjhLETGRIQaHHG/DkwhkShfdkcCjBPwzrlZigH1bhZT3ulultkAht75YdxalU4aE96uc3Nt6LV7kgQKBgQDghW/qiKGnX1WhLXGklKNGwZaqaa2RHfNb06skYLiXcYpCSC+/inP5vY4th097q1OIfSEQlONGpDDastKupGOUbOVh/17kHy7EXl3VQQ1uFZHl09QUxlBrgrQk8HoqTaab/hKqnlZkUJenFpk46w3ancXiRCLeVpBAKSknd9U8jQKBgQCuceetv2xesZPP+xycyxu+nspL4EC7MnRDoDOU8r/IHSqLklhJvLN3sJQIlVtpZxoXXyN9QRBfkB0KDHqOxb8/H5Vw6S3WrixUBENoPD+vCcLrattVzhiFGdjNqV6FKyw7+Tu2+ko1/KM944QTbRNa1W27C4fxaG+uEi6xdn1g8QKBgFq6MT5yFfjfkme+SGT8V3p5MhwM9kFk+/lIcKkTmKRCEl+0ce8R0MnDqpeFTT4pPmkyP1FHCQx7vu4xQuelsEPDOXI/UqYWX7ykcYCCeB3bA4j+732xob/AInGm54b+t+vjYMGnmDuTQaUYgbQ5b/C+vr8vT3PPQR1cRLAZQYblAoGBAI1kxeSOw8f9J1Hst8Re3J4lFtNpcQ5KVvJ5Z1XTbJ9HBLUcaXNbAByDg3lxliZY600GUD21/w92QBNlCfGvVtRTRU2/sufPRR7UVc6nBvdZciRe+GkUI8u+p3wLTBGkbLYvzbcCKCSaIbaIMwWL46Q8WjTBo+6IN/1fv3kYEYgBAoGBAMvv1sleRVyYMGvISUD/u/8utBHQq8aE9S1iEYMhk3CI4GPuQU+RDEU4fpmPXTazveLPlImrM/g5GIoHkQAE3k1Gmb+CJLpiPU0hq6qqk/0sXkuD5tuoZyJXyu9wWVk4UWI1d2PNwpfqDlykgOS6EirutxTcMPbuRrmSHgYOP0sk";
    /**
     * 参数返回格式，支支持json格式
     */
    public static String FORMAT = "json";
    /**
     * 请求和签名使用的字符编码格式，指出GBK和UTF-8
     */
    public static String CHARSET = "utf-8";
    /**
     * 支付宝公钥
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoGMFR0v1vkoph+kBqDZ5xU+CsKYyALgRJJfmZSXe2z9gy5J0PWkRbg9hQhc6eZRbH0cyI4wAGW3bzIOkVvxvaZ55MUa7Av8l1GvakUdTDE0O2hg9SwfpDYaGBGG5n1TmWVtGb40r9V1c3fyPJHhhowlXHJXTbv6ah5NmyZtXOVxn6CmHmpKLew4bpY+Y5Jiyz6RH1TDRDd0ay8YSxTchb9eVnnQGwgRiU2AeLttDtgJroCieBBNqIogzegeqxMlINjJNkMaplqKAbOjAQJp0DaUVfzBryi04HBFIgAYIxHRsnRxpwi1pEyrCyLV8QtMyKYpDAtqZTCIAFkMSXI9wKwIDAQAB";
    /**
     * 签名方式：签名算法类型
     */
    public static String SIGN_TYPE = "RSA2";
    /**
     * 服务器异步通知页面路径，异步返回商家操作，必须外网可以正常访问，需完整路径，不带参数
     */
    public static String NOTIFY_URL = "";
    /**
     *同步返回页面，即用户支付后看到的页面
     * 服务器IP为：http://112.74.77.170/  本地使用为：http://localhost
     */
    public static String RESULT_URL = "http://cyberia.fun/#/pay/success";
}
