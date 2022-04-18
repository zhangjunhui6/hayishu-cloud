package org.example.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCode {
    /**
     * PREFIX：订单编号前缀
     */
    public static final String PREFIX = "DD";
    /**
     * code：订单编号后缀
     */
    public static long code;

    /**
     * 生成订单编号
     */
    public static synchronized String getOrderCode(){
        code++;
        String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        long m = Long.parseLong(str) * 10000;
        m += code;
        return PREFIX + m;
    }


}
