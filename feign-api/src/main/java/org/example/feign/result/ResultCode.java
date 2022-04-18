package org.example.feign.result;

/**
 * ResultCode
 *
 * @author Zjh
 * @date 2020/5/24 23:16
 **/
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 没有身份认证
     */
    UNAUTHORIZED(401),
    /**
     * 请求资源不存在
     */
    NOT_FOUND(404),
    /**
     * 内部服务器错误
     */
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ResultCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
